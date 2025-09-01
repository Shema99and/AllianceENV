package com.zifo.ewb.alliance.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zifo.ewb.alliance.serviceimplementation.AllianceCatalogServiceImplementation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;

@EnableScheduling
@RestController
@NoArgsConstructor
@RequestMapping("/EWBAlliance/v1")
@OpenAPIDefinition(
    info = @Info(title = "Alliance API", version = "v1")
)
//@Api(tags = "Export API", description = "Alliance API for updating the Alliance Details in EWB", position = 2)
@Tag(name = "Export API", description = "Alliance API for updating the Alliance Details in EWB")
public class AllianceCatalog {
	
	@Autowired
	public AllianceCatalogServiceImplementation allianceCatalogSeriveImplementation;

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AllianceCatalog.class.getName());

	public static boolean mailSent = false;

//	@ApiOperation(value = "Scheduled for updating the Alliance BA Details in IDBS when every time it runs or triggered manually and initialize the 'Alliance' Env variable", position = 1)

	@Operation(summary = "Scheduled update of Alliance BA Details in IDBS", description = "Runs automatically or manually to update Alliance BA Details and initialize the 'Alliance' environment variable")
//	@Scheduled(fixedRate = 86400000)
	@GetMapping(path = "/trigger/AllianceEnvScheduler")
	public void startProcess() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException {
		LOGGER.info("Alliance Environment Scheduler started");
		
		allianceCatalogSeriveImplementation.updateAllianceDettail();
//		mailSent = false;
//		ServiceBase.loadProperties();
//		Alliance alliance = new Alliance();
//		Alliance.TLS_Version = ConfigResources.getTLSVersion();
//		final CatalogService cService = new CatalogService();
//		EntityTreeService entity = new EntityTreeService();
//		EntityVersionService versions = new EntityVersionService();
//		SecurityService securityService = new SecurityService();
//		LockService lockservice = new LockService();
//		SpreadsheetService spreadsheet = new SpreadsheetService();
//		try {
////			String catalogDetails = System.getProperty("Alliance");
//			String catalogDetails = "{\"path\":\"\",\"tuple\":[{\"data\":[{\"name\":\"Alliance Members\",\"value\":\"\",\"displayValue\":\"\"},{\"name\":\"Alliance Name\",\"value\":\"Biota (Relenza)\",\"displayValue\":\"Biota (Relenza)\"},{\"name\":\"IDBS Members\",\"value\":\"\",\"displayValue\":\"\"},{\"name\":\"Experiment Restriction\",\"value\":\"NOT RESTRICTED\",\"displayValue\":\"NOT RESTRICTED\"},{\"name\":\"Physical CompoundRestriction\",\"value\":\"NOT RESTRICTED\",\"displayValue\":\"NOT RESTRICTED\"}],\"id\":\"\",\"enabled\":true,\"defaultTuple\":false,\"deletable\":true,\"editable\":true}]}";
//			if (catalogDetails == null) {
//				Alliance all = new Alliance();
//				catalogDetails = all.updateAllianceVariable();
//			}
//			final Map<String, String> terminatedAlliance = new HashMap<String, String>();
//			CatalogService catalog = new CatalogService();
//			String catalogId = alliance.retrieveId(catalog.getGuid("/Test/Alliance"));
//			ObjectMapper mapper = new ObjectMapper();
//			CatalogDetailPojo catalogDetail = mapper.readValue(catalogDetails, CatalogDetailPojo.class);
////			String response = alliance.getAllianceDetails(0, 200000);
//			String response = "{\"data\":[{\"agreementContractId\":16,\"agreement\":{\"id\":\"URN:LSID:gsk.com/rd:item.DEX:105231\",\"name\":\"Biota (Relenza)\",\"abbreviation\":\"Biota (Relenza)\",\"shortName\":\"Biota (Relenza)\",\"longName\":\"Biota (Relenza)\"},\"projects\":[],\"teamMembers\":[],\"approvers\":[\"bhamir00\",\"nealr000\",\"alt30449\",\"smithk09\"],\"agreementParties\":[{\"id\":\"URN:LSID:gsk.com/rd:item.DEX:59808\",\"name\":\"Biota Holdings Limited\",\"shortName\":\"Biota Holdings\",\"longName\":\"Biota Holdings Limited\"}],\"contacts\":[{\"id\":\"bhamir00\",\"role\":\"Agreement Manager\"},{\"id\":\"nealr000\",\"role\":\"Agreement Manager\"},{\"id\":\"alt30449\",\"role\":\"Other\"},{\"id\":\"smithk09\",\"role\":\"Other\"}],\"experimentRestriction\":{\"elnbFlag\":false,\"elnbDataFlag\":false,\"id\":\"URN:LSID:gsk.com/rd:item.DEX:42348\",\"restriction\":\"NOT RESTRICTED\",\"code\":101},\"physicalCompoundRestriction\":{\"restrictionTag\":null,\"id\":\"URN:LSID:gsk.com/rd:item.DEX:4584\",\"restriction\":\"NOT RESTRICTED\",\"code\":1},\"multipleRestrictionsFlag\":false,\"multipleRestrictonInstructions\":null,\"creationDate\":\"2008-09-26T11:23:01\",\"modificationDate\":\"2012-10-25T11:17:58\",\"effectiveDate\":null,\"endDate\":null,\"version\":1,\"status\":\"Active\",\"isActive\":true,\"securityGroupName\":\"Biota (Relenza)_DRP_4752_Member\",\"agreementType\":null,\"agreementTypeLsId\":null,\"id\":\"URN:LSID:gsk.com/rd:item.DEX:4752\",\"name\":\"Biota (Relenza)\",\"abbreviation\":\"Biota (Relenza)\",\"shortName\":\"Biota (Relenza)\",\"longName\":\"Biota (Relenza)\"}],\"paging\":{\"total\":1224,\"count\":704,\"offset\":0,\"limit\":0}}";
//			final List<String> groupName = securityService.getGroups();
//			final InputPojo allianceDetails = mapper.readValue(response, InputPojo.class);
//			final List<String> allianceName = AllianceNameHelper.getAllianceNames(allianceDetails);
//			ProcessAllianceDetailsHelper processDetails = new ProcessAllianceDetailsHelper();
//			processDetails.processDetails(catalogDetail, allianceDetails, catalogId, groupName, terminatedAlliance); // creation
//																														// of
//																														// New
//																														// user
//																														// when
//																														// added
//																														// to
//																														// alliance
//			final String updatedcatDetails = cService.callCatalogDetails(catalogId, ServiceBase.getEntityAuth());
//			final CatalogDetailPojo updatedcatDetail = mapper.readValue(updatedcatDetails, CatalogDetailPojo.class);
//			final Map<String, String> catAllianceNames = CatlogAllianceNamesHelper.getAllianceNames(updatedcatDetail);
//			CheckAllianceRemovedHelper.checkAlliance(catAllianceNames, allianceName, groupName, catalogId,
//					terminatedAlliance);
//			String inputJSON = alliance.apitoSpreadsheetJSON(response);
//			String spreadsheetId = alliance.retrieveSpreadsheetId(entity.getChildEntites(ServiceBase.getEntityId()));
//			String modelId = StringUtils.EMPTY;
//			try {
//				String loadSpreadsheet = alliance.retrieveLatestVersionId(versions.getVersionDetails(spreadsheetId));
//				lockservice.lockRecord(ServiceBase.getEntityId());
//				modelId = spreadsheet.loadSpreadsheet(loadSpreadsheet, Boolean.TRUE);
//				LOGGER.info(modelId);
//				spreadsheet.loadedSpreadsheet(modelId, alliance.clearTableData());
//				String rowCountJSON = spreadsheet.loadedSpreadsheet(modelId, alliance.getRowCount());
//				JsonNode jsonObject = mapper.readTree(rowCountJSON);
//				int rowcount = Integer.valueOf(
//						(jsonObject.get("batch-response").get("api-responses").get(0).get("tables").get("Alliance")
//								.get("dimensions").get(1).get("itemCount").toString().replace("\"", "")));
//				if (rowcount < alliance.getActiveAlliancesCount(response)) {
//					spreadsheet.loadedSpreadsheet(modelId, alliance.getRowCountIncreaseJson("Alliance", "Index",
//							rowcount, alliance.getActiveAlliancesCount(response)));
//				}
//				spreadsheet.loadedSpreadsheet(modelId, inputJSON);
//				spreadsheet.saveSpreadsheet(modelId);
//			} catch (Exception ex1) {
//				LOGGER.info(ex1.getMessage(), ex1);
//			} finally {
//				spreadsheet.unloadSpreadsheet(modelId);
//				spreadsheet.callDraftSave(ServiceBase.getEntityId());
//				lockservice.unlockRecord(ServiceBase.getEntityId());
//
//				String readloadSpreadsheet = alliance
//						.retrieveLatestVersionId(versions.getVersionDetails(spreadsheetId));
//				String readSpreadsheetData = spreadsheet.loadedSpreadsheet(
//						spreadsheet.loadSpreadsheet(readloadSpreadsheet, Boolean.FALSE),
//						spreadsheet.createJSONtoReadTableData("Alliance"));
//				String allianceDataRead = alliance.mapSpreadsheetToCatalogData(readSpreadsheetData);
//				System.setProperty("Alliance", allianceDataRead);
//				LOGGER.info(System.getProperty("Alliance"));
//				LOGGER.info(allianceDataRead);
//			}
//
//		} catch (Exception ex) {
//			LOGGER.info(ex.getMessage(), ex);
//			LOGGER.info("Error occured when Alliance Environment Scheduler running ");
//		} finally {
//			if (!mailSent) {
//				String details = "<h3>No new user folders were created in the " + ServiceBase.getEnv() + " Environment"
//						+ "</h3>";
//				String mailcontent = MailAPI.getNewMailbody(details);
//				MailSender.sendMail(mailcontent);
//			}
//			LOGGER.info("Alliance Environment Scheduler Ended");
//		}

	}

//	public CatalogDetailPojo allianceCatalogMapping(InputPojo details) throws KeyManagementException, KeyStoreException,
//			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
//		Alliance alliance = new Alliance();
//		CatalogDetailPojo catalogDetail = new CatalogDetailPojo();
//		List<Tuple> tuples = new ArrayList<Tuple>();
//		for (Datum data : details.getData()) {
//			if (!data.getStatus().equalsIgnoreCase("Terminated")) {
//				final Tuple input = new Tuple();
//				input.setId(StringUtils.EMPTY);
//				input.setDefaultTuple(false);
//				input.setDeletable(true);
//				input.setEditable(true);
//				input.setEnabled(true);
//				Map<String, List<String>> users = alliance.getUsers(data);
//				List<String> allianceUsers = users.get("allianceUserList");
//				List<String> idbsUsers = users.get("idbsUserList");
//				List<com.zifo.ewb.catalogPojo.Datum> dataList = new ArrayList<com.zifo.ewb.catalogPojo.Datum>();
//				com.zifo.ewb.catalogPojo.Datum catalogData = new com.zifo.ewb.catalogPojo.Datum();
//				catalogData.setName("Alliance Members");
//				catalogData.setValue(String.join(",", allianceUsers));
//				catalogData.setDisplayValue(String.join(",", allianceUsers));
//				com.zifo.ewb.catalogPojo.Datum catalogData1 = new com.zifo.ewb.catalogPojo.Datum();
//				catalogData1.setName("Alliance Name");
//				catalogData1.setValue(data.getName());
//				catalogData1.setDisplayValue(data.getName());
//				com.zifo.ewb.catalogPojo.Datum catalogData2 = new com.zifo.ewb.catalogPojo.Datum();
//				catalogData2.setName("IDBS Members");
//				catalogData2.setValue(String.join(",", idbsUsers));
//				catalogData2.setDisplayValue(String.join(",", idbsUsers));
//				com.zifo.ewb.catalogPojo.Datum catalogData3 = new com.zifo.ewb.catalogPojo.Datum();
//				catalogData3.setName("Experiment Restriction");
//				catalogData3.setValue(data.getPhysicalCompoundRestriction().getRestriction());
//				catalogData3.setDisplayValue(data.getPhysicalCompoundRestriction().getRestriction());
//				com.zifo.ewb.catalogPojo.Datum catalogData4 = new com.zifo.ewb.catalogPojo.Datum();
//				catalogData4.setName("Physical CompoundRestriction");
//				catalogData4.setValue(data.getExperimentRestriction().getRestriction());
//				catalogData4.setDisplayValue(data.getExperimentRestriction().getRestriction());
//
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
//		return catalogDetail;
//	}

//	public List<String> getAllianceNames(final CatalogDetailPojo allianceDetails) {
//		final List<String> allianceNames = new ArrayList<>();
//
//		for (final com.zifo.ewb.catalogPojo.Datum details : ((Tuple) allianceDetails.getTuple().get(0)).getData()) {
//			allianceNames.add(details.getName());
//		}
//		return allianceNames;
//	}
}
