package com.zifo.ewb.alliance.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsk.api.datarestrictionpojo.InputPojo;
import com.zifo.ewb.alliance.allianceprocesshelper.GetAllianceandIDBSUsers;
import com.zifo.ewb.alliance.constants.JSONConstants;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.AllianceService;
import com.zifo.ewb.alliance.serviceh.EWBService;
import com.zifo.ewb.alliance.services.CatalogService;
import com.zifo.ewb.alliance.services.LockService;
import com.zifo.ewb.alliance.services.SpreadsheetService;
import com.zifo.ewb.alliance.utils.ConfigResources;
import com.zifo.ewb.alliance.utils.GetRowCountIncreaseJson;
import com.zifo.ewb.alliance.utils.ServiceBase;
import com.zifo.ewb.catalogpojo.CatalogDetailPojo;
import com.zifo.ewb.catalogpojo.Tuple;
import com.zifo.ewb.spreadsheetpojo.Datum;
import com.zifo.ewb.alliance.helper.ApiToSpreadsheetJson;
import com.zifo.ewb.alliance.helper.MapSpreadsheetToCatalogData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;

@EnableScheduling
@RestController
@NoArgsConstructor
//@EnableSwagger2
@RequestMapping("/EWBAlliance/v1")
@Tag(name = "Export API", description = "Alliance API for updating the Alliance Details in EWB")
public class Alliance {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(Alliance.class.getName());
	public static String TLS_Version = StringUtils.EMPTY;

	/**
	 * @param requestDTO
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */

//	@ApiOperation(value = "Endpoint for updating and getting the  Alliance Env variable with new Alliance Data", position = 1)

	@Operation(description = "Endpoint for updating and getting the  Alliance Env variable with new Alliance Data")

//	@Scheduled(fixedRate = 86400000)
	@GetMapping(path = "/trigger/AllianceEnv")
	

	public int getActiveAlliancesCount(String response) throws IOException { 
		ObjectMapper mapper = new ObjectMapper();
		int count = 0;
		final InputPojo allianceDetails = mapper.readValue(response, InputPojo.class);
		for (com.gsk.api.datarestrictionpojo.Datum data : allianceDetails.getData()) {
			if (!data.getStatus().equalsIgnoreCase("Terminated")) {
				count = count + 1;
			}
		}
		return count;

	}
	
	public String clearTableData() {    // need to put in utility class
		return "{\r\n" + "    \"batch-request\": [\r\n" + "        {\r\n" + "            \"version\": \"1.0\"\r\n"
				+ "        },\r\n" + "        [\r\n" + "            {\r\n"
				+ "                \"api-id\": \"table.data.clear\",\r\n"
				+ "                \"api-version\": \"1.0\"\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"data\": {\r\n" + "                    \"ranges\": [\r\n"
				+ "                        {\r\n" + "                            \"table\": \"Alliance\"\r\n"
				+ "                        }\r\n" + "                    ]\r\n" + "                }\r\n"
				+ "            }\r\n" + "        ]\r\n" + "    ]\r\n" + "}\r\n" + "\r\n" + "";
	}

	public String getTableUpdateJSON(final List<Map<String, String>> exceptionlist, final String tableName,   // need to put in helper class
			final Map<String, String> columnType) {

		final String start = "{\r\n" + "	\"batch-request\": [{\r\n" + "			\"version\": \"1.0\"\r\n"
				+ "		},\r\n" + "		[{\r\n" + "			\"api-version\": \"1.0\",\r\n"
				+ "			\"api-id\": \"table.data.set\"\r\n" + "		}, {\r\n" + "			\"data\": {\r\n"
				+ "				\"tables\": [{\r\n" + "						\"name\": \"" + tableName + "\"\r\n"
				+ "					},\r\n" + "					[";

		String mid = StringUtils.EMPTY;
		for (final Map<String, String> exceptiondata : exceptionlist) {
			for (final Map.Entry<String, String> entry : exceptiondata.entrySet()) {
				final String keyvalue = entry.getKey();
				if (mid.length() > 0) {
					mid = mid + "\"" + keyvalue + "\": {\r\n" + "\"" + columnType.get(keyvalue) + "\": ";
					if (columnType.get(keyvalue).equals(JSONConstants.NUMBER)) {
						mid = mid + entry.getValue() + "},\r\n";
					} else {
						mid = mid + "\"" + entry.getValue() + "\"\r\n" + "},\r\n";
					}
				} else {
					mid = mid + "{\r\n" + "						\"" + keyvalue + "\": {\r\n"
							+ "							\"" + columnType.get(keyvalue) + "\":";
					if (columnType.get(keyvalue).equals(JSONConstants.NUMBER)) {
						mid = mid + entry.getValue() + "},\r\n";
					} else {
						mid = mid + "\"" + entry.getValue() + "\"\r\n" + "},\r\n";
					}
				}
			}
			if (mid.length() > 0) {
				mid = mid.substring(0, mid.lastIndexOf(','));
			}
			mid = mid + "					},{";
		}
		if (mid.length() > 0) {
			mid = mid.substring(0, mid.lastIndexOf(','));
		}

		final String end = "]\r\n" + "]\r\n" + "}\r\n" + "}]\r\n" + "]\r\n" + "}";

		return start + mid + end;
	}

//	public String updateAllianceVariable() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
//			CertificateException, IOException, URISyntaxException {
//		EWBService ewbService = new EWBService();
//		 AllianceService allianceService = new AllianceService();
//		CatalogService catalogService = new CatalogService();
//
//		ServiceBase.loadProperties();
//		LOGGER.info("Alliance Environment variable updating started");
//		TLS_Version = ConfigResources.getTLSVersion();
////		EntityTreeService entity = new EntityTreeService();
////		EntityVersionService versions = new EntityVersionService();
//		SpreadsheetService spreadsheet = new SpreadsheetService();
//		LockService lockservice = new LockService();
//		String modelId = StringUtils.EMPTY;
//
////		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String spreadsheetId = ewbService.retrieveSpreadsheetId(ServiceBase.getEntityId());
//			String loadSpreadsheet = ewbService.retrieveLatestVersionId(spreadsheetId);
//			modelId = ewbService.loadSpreadsheet(loadSpreadsheet, Boolean.FALSE);
//			String spreadsheetData = ewbService.loadedSpreadsheetDetails(modelId,
//					spreadsheet.createJSONtoReadTableData("Alliance"));
//			MapSpreadsheetToCatalogData spreadsheetCatalogHelper = new MapSpreadsheetToCatalogData();
//			String allianceData = spreadsheetCatalogHelper.mapSpreadsheetToCatalogData(spreadsheetData);
//			if (allianceData.isEmpty()) {
//				try {
//					ewbService.unloadSpreadsheet(modelId);
//					String response = allianceService.getAllianceDetails(0, 200000);
//					String catalogId = catalogService.getGuid("/Test/Alliance");
//					ApiToSpreadsheetJson apiSpreadsheetHelper = new ApiToSpreadsheetJson();
//					String inputJSON = apiSpreadsheetHelper.apitoSpreadsheetJSON(response,catalogId);
//					lockservice.lockRecord(ServiceBase.getEntityId());
//					modelId = spreadsheet.loadSpreadsheet(loadSpreadsheet, Boolean.TRUE);
//					LOGGER.info(modelId);
//					spreadsheet.loadedSpreadsheet(modelId,
//							GetRowCountIncreaseJson.getRowCountIncreaseJson("Alliance", "Index", 1, getActiveAlliancesCount(response)));
//					spreadsheet.loadedSpreadsheet(modelId, inputJSON);
//					spreadsheet.saveSpreadsheet(modelId);
//				} catch (Exception exc1) {
//					LOGGER.error(exc1.getMessage(), exc1);
//				} finally {
//					try {
//						spreadsheet.unloadSpreadsheet(modelId);
//						spreadsheet.callDraftSave(ServiceBase.getEntityId());
//					} catch (Exception exception) {
//						LOGGER.error(exception.getMessage(), exception);
//					} finally {
//						lockservice.unlockRecord(ServiceBase.getEntityId());
//						String readloadSpreadsheet = ewbService.retrieveLatestVersionId(spreadsheetId);
//						String readSpreadsheetData = spreadsheet.loadedSpreadsheet(
//								spreadsheet.loadSpreadsheet(readloadSpreadsheet, Boolean.FALSE),
//								spreadsheet.createJSONtoReadTableData("Alliance"));
//						String allianceDataRead = spreadsheetCatalogHelper.mapSpreadsheetToCatalogData(readSpreadsheetData);
//						System.setProperty("Alliance", allianceDataRead);
//						LOGGER.info(System.getProperty("Alliance"));
//					}
//				}
//			} else {
//				System.setProperty("Alliance", allianceData);
//				String props = System.getProperty("Alliance");
//				LOGGER.info(props);
//			}
//		} catch (Exception ex) {
//			LOGGER.error(ex.getMessage(), ex);
//			LOGGER.info("Error while updating the Alliance Environment variable");
//		} finally {
//			spreadsheet.unloadSpreadsheet(modelId);
//			LOGGER.info("Alliance Environment variable updating ended");
//		}
//		return System.getProperty("Alliance");
//	}


//
//	public String apitoSpreadsheetJSON(String response, String catalogId)   // need to put in helper class
//			throws IOException, KeyManagementException, KeyStoreException,
//			NoSuchAlgorithmException, CertificateException, URISyntaxException, ApiException {
//		EWBService ewbService = new EWBService();
//		ObjectMapper mapper = new ObjectMapper();
//		final InputPojo allianceDetails = mapper.readValue(response, InputPojo.class);
//		int count = 0;
//		List<Map<String, String>> allData = new ArrayList<>();
////		String catalogId = catalogService.getGuid("/Test/Alliance");
//		for (com.gsk.api.datarestrictionpojo.Datum allianceData : allianceDetails.getData()) {
//			if (!allianceData.getStatus().equalsIgnoreCase("Terminated")) {
//				final Tuple input = new Tuple();
//				input.setId(catalogId);
//				input.setDefaultTuple(false);
//				input.setDeletable(true);
//				input.setEditable(true);
//				input.setEnabled(true);
//				List<com.zifo.ewb.catalogpojo.Datum> dataList = new ArrayList<>();
//				com.zifo.ewb.catalogpojo.Datum catalogData = new com.zifo.ewb.catalogpojo.Datum();
//				catalogData.setName("Alliance Name");
//				catalogData.setValue(allianceData.getName());
//				catalogData.setDisplayValue(allianceData.getName());
//				dataList.add(catalogData);
//				input.setData(dataList);
//				ewbService.createTuple(catalogId, mapper.writeValueAsString(input));
//				count = count + 1;
//				Map<String, String> data = new HashMap<>();
//				Map<String, List<String>> users = GetAllianceandIDBSUsers.getActiveUsers(allianceData);
//				List<String> allianceUsers = users.get("allianceUserList");
//				List<String> idbsUsers = users.get("idbsUserList");
//				data.put("IDBS Members", String.join(",", idbsUsers));
//				data.put("Alliance Members", String.join(",", allianceUsers));
//				data.put("Physical Compound Restriction",
//						allianceData.getPhysicalCompoundRestriction().getRestriction());
//				data.put("Experiment Restriction", allianceData.getExperimentRestriction().getRestriction());
//				data.put("Alliance Name", allianceData.getName());
//				data.put("Index", Integer.toString(count));
//				allData.add(data);
//			}
//		}
//		Map<String, String> columnTypes = new HashMap<>();
//		columnTypes.put("IDBS Members", "string");
//		columnTypes.put("Alliance Members", "string");
//		columnTypes.put("Physical Compound Restriction", "string");
//		columnTypes.put("Experiment Restriction", "string");
//		columnTypes.put("Alliance Name", "string");
//		columnTypes.put("Index", "string");
//		return getTableUpdateJSON(allData, "Alliance", columnTypes);
//	}

//	public String retrieveId(String guid) {
//		String catalogId = StringUtils.EMPTY;
//		if (!guid.isEmpty()) {
//			JSONObject response = new JSONObject(guid);
//			catalogId = response.getString("id");
//		}
//		return catalogId;
//	}

//	public Map<String, List<String>> getActiveUsers(com.gsk.api.dataRestrictionPojo.Datum allianceData)
//			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
//			IOException, URISyntaxException {
//		Map<String, List<String>> userDetails = new LinkedHashMap<>();
//		LockService lockservice = new LockService();
//		List<String> users = new ArrayList<String>();
//		users.addAll(allianceData.getTeamMembers());
//		// users.addAll(allianceData.getApprovers());
//		List<String> uniqueUsers = new ArrayList<String>();
//		List<String> allianceUsers = new ArrayList<String>();
//		List<String> idbsUsers = new ArrayList<String>();
//		for (String user : users) {
//			if (!uniqueUsers.contains(user)) {
//				uniqueUsers.add(user);
//			}
//		}
//		for (String user : uniqueUsers) {
//
//			if (lockservice.getUserName(user).isEmpty()) {
//				allianceUsers.add(user);
//			} else {
//				idbsUsers.add(user);
//			}
//		}
//		userDetails.put("uniqueUserList", uniqueUsers);
//		userDetails.put("allianceUserList", allianceUsers);
//		userDetails.put("idbsUserList", idbsUsers);
//		return userDetails;
//	}
	
	
//	public String retrieveSpreadsheetId(String response) throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		EntityService entitytree = mapper.readValue(response, EntityService.class);
//		String spreadsheetId = StringUtils.EMPTY;
//		for (Entity singleEntity : entitytree.getEntity()) {
//			if (singleEntity.getEntityTypeName().equals("IDBS_SPREADSHEET")) {
//				spreadsheetId = singleEntity.getEntityId();
//			}
//		}
//		return spreadsheetId;
//	}

//	public String retrieveLatestVersionId(String response)
//			throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		EntityVersionServiceResponse entityversion = mapper.readValue(response, EntityVersionServiceResponse.class);
//		int versionNumber = 1;
//		String latestVersionId = StringUtils.EMPTY;
//		for (Version singleversion : entityversion.getVersion()) {
//			if (versionNumber < singleversion.getVersionNumber()) {
//				versionNumber = singleversion.getVersionNumber();
//				latestVersionId = singleversion.getVersionId();
//			} else if (singleversion.getVersionNumber() == 1) {
//				latestVersionId = singleversion.getVersionId();
//			}
//		}
//		return latestVersionId;
//	}

//	public String mapSpreadsheetToCatalogData(String spreadSheetData)  // need to put in helper class
//			throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		CatalogDetailPojo catalogDetail = new CatalogDetailPojo();
//		com.zifo.ewb.spreadsheetpojo.TableData data = mapper.readValue(spreadSheetData,
//				com.zifo.ewb.spreadsheetpojo.TableData.class);
//		List<Tuple> tuples = new ArrayList<>();
//		for (Datum singlerow : data.getBatchResponse().getApiResponses().get(0).getTables().get(0).getRanges().get(0)
//				.getData()) {
//			if (!singlerow.getAllianceName().getString().isEmpty()) {
//				final Tuple input = new Tuple();
//				input.setId(StringUtils.EMPTY);
//				input.setDefaultTuple(false);
//				input.setDeletable(true);
//				input.setEditable(true);
//				input.setEnabled(true);
//				List<com.zifo.ewb.catalogpojo.Datum> dataList = new ArrayList<>();
//				com.zifo.ewb.catalogpojo.Datum catalogData = new com.zifo.ewb.catalogpojo.Datum();
//				if (!singlerow.getAllianceMembers().getString().isEmpty()) {
//					catalogData.setName("Alliance Members");
//					catalogData.setValue(singlerow.getAllianceMembers().getString());
//					catalogData.setDisplayValue(singlerow.getAllianceMembers().getString());
//				} else {
//					catalogData.setName("Alliance Members");
//					catalogData.setValue(StringUtils.EMPTY);
//					catalogData.setDisplayValue(StringUtils.EMPTY);
//				}
//				com.zifo.ewb.catalogpojo.Datum catalogData1 = new com.zifo.ewb.catalogpojo.Datum();
//				if (!singlerow.getAllianceName().getString().isEmpty()) {
//					catalogData1.setName("Alliance Name");
//					catalogData1.setValue(singlerow.getAllianceName().getString());
//					catalogData1.setDisplayValue(singlerow.getAllianceName().getString());
//				}
//				com.zifo.ewb.catalogpojo.Datum catalogData2 = new com.zifo.ewb.catalogpojo.Datum();
//				if (!singlerow.getIDBSMembers().getString().isEmpty()) {
//					catalogData2.setName("IDBS Members");
//					catalogData2.setValue(singlerow.getIDBSMembers().getString());
//					catalogData2.setDisplayValue(singlerow.getIDBSMembers().getString());
//				} else {
//					catalogData2.setName("IDBS Members");
//					catalogData2.setValue(StringUtils.EMPTY);
//					catalogData2.setDisplayValue(StringUtils.EMPTY);
//				}
//				com.zifo.ewb.catalogpojo.Datum catalogData3 = new com.zifo.ewb.catalogpojo.Datum();
//				if (!singlerow.getExperimentRestriction().getString().isEmpty()) {
//					catalogData3.setName("Experiment Restriction");
//					catalogData3.setValue(singlerow.getExperimentRestriction().getString());
//					catalogData3.setDisplayValue(singlerow.getExperimentRestriction().getString());
//				} else {
//					catalogData3.setName("Experiment Restriction");
//					catalogData3.setValue(StringUtils.EMPTY);
//					catalogData3.setDisplayValue(StringUtils.EMPTY);
//				}
//				com.zifo.ewb.catalogpojo.Datum catalogData4 = new com.zifo.ewb.catalogpojo.Datum();
//				if (!singlerow.getExperimentRestriction().getString().isEmpty()) {
//					catalogData4.setName("Physical CompoundRestriction");
//					catalogData4.setValue(singlerow.getPhysicalCompoundRestriction().getString());
//					catalogData4.setDisplayValue(singlerow.getPhysicalCompoundRestriction().getString());
//				} else {
//					catalogData4.setName("Physical CompoundRestriction");
//					catalogData4.setValue(StringUtils.EMPTY);
//					catalogData4.setDisplayValue(StringUtils.EMPTY);
//				}
//				dataList.add(catalogData);
//				dataList.add(catalogData1);
//				dataList.add(catalogData2);
//				dataList.add(catalogData3);
//				dataList.add(catalogData4);
//				input.setData(dataList);
//				tuples.add(input);
//			}
//		}
//		catalogDetail.setTuple(tuples);
//		catalogDetail.setPath(StringUtils.EMPTY);
//		String response = StringUtils.EMPTY;
//		if (data.getBatchResponse().getApiResponses().get(0).getTables().get(0).getRanges().get(0).getData()
//				.size() > 1) {
//			response = mapper.writeValueAsString(catalogDetail);
//		}
//		return response;
//	}

//	@SuppressWarnings("deprecation")   // remove this after updating the httpclient dependency - gsk web client
//	public String getAllianceDetails(final int offset, final int limit) throws IOException {
//
//		final HttpClient client = new DefaultHttpClient();
//		final String url = ServiceBase.getAllianceUrl() + "?offset=" + offset + "&limit=" + limit;
//		LOGGER.info(url);
//		final String encodedurl = url.replaceAll(" ", "%20");
//		final HttpGet request = new HttpGet(encodedurl);
//		request.setHeader("Authorization", "Basic " + ServiceBase.getAllianceAuth());
//		final HttpResponse response = client.execute(request);
//		return EntityUtils.toString(response.getEntity());
//	}



//	public String getRowCountIncreaseJson(final String tableName, final String nonDD, final int position,   // need to put in utility class
//			final int newRowCount) {
//		return "{\r\n" + " \"batch-request\": [\r\n" + " {\r\n" + " \"version\": \"1.0\",\r\n" + " \"options\": {\r\n"
//				+ " \"force\": true\r\n" + " }\r\n" + " },\r\n" + " [\r\n" + " {\r\n"
//				+ " \"api-id\": \"table.structure.insert\",\r\n" + " \"api-version\": \"1.0\"\r\n" + " },\r\n"
//				+ " {\r\n" + " \"data\": {\r\n" + " \"tables\": {\r\n" + " \"" + tableName + "\": {\r\n" + " \"" + nonDD
//				+ "\": {\r\n" + " \"itemCount\": " + newRowCount + ",\r\n" + " \"insertAt\": " + position + "\r\n"
//				+ " }\r\n" + " }\r\n" + " }\r\n" + " }\r\n" + " }\r\n" + " ]\r\n" + " ]\r\n" + "}";
//	}

//	public String getRowCount() {                         // need to put in utility class
//		return "{\r\n" + "    \"batch-request\": [\r\n" + "        { \"version\": \"1.0\" },\r\n" + "        [\r\n"
//				+ "            {\r\n" + "                \"api-id\": \"table.structure\",\r\n"
//				+ "                \"api-version\": \"1.0\"\r\n" + "            },\r\n" + "            {\r\n"
//				+ "                \"data\": {\r\n" + "                    \"tables\": [\"Alliance\"],\r\n"
//				+ "                    \"options\": {\r\n"
//				+ "                        \"itemNames\": \"dataDimension\"\r\n" + "                    }\r\n"
//				+ "                }\r\n" + "            }\r\n" + "]]}";
//	}
}