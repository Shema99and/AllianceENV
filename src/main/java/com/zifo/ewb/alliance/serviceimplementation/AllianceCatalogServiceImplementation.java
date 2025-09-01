package com.zifo.ewb.alliance.serviceimplementation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsk.api.datarestrictionpojo.InputPojo;
import com.zifo.ewb.alliance.allianceprocess.CheckAllianceRemovedHelper;
import com.zifo.ewb.alliance.allianceprocesshelper.CatlogAllianceNamesHelper;
import com.zifo.ewb.alliance.allianceprocesshelper.ProcessAllianceDetailsHelper;
import com.zifo.ewb.alliance.controller.Alliance;
import com.zifo.ewb.alliance.helper.AllianceNameHelper;
import com.zifo.ewb.alliance.helper.ApiToSpreadsheetJson;
import com.zifo.ewb.alliance.helper.MapSpreadsheetToCatalogData;
import com.zifo.ewb.alliance.helper.UpdateAllianceVariable;
import com.zifo.ewb.alliance.mailcontent.MailAPI;
import com.zifo.ewb.alliance.mailcontent.MailSender;
import com.zifo.ewb.alliance.serviceh.AllianceService;
import com.zifo.ewb.alliance.serviceh.CatalogService;
import com.zifo.ewb.alliance.serviceh.EWBService;
import com.zifo.ewb.alliance.services.EntityTreeService;
import com.zifo.ewb.alliance.services.EntityVersionService;
import com.zifo.ewb.alliance.services.LockService;
import com.zifo.ewb.alliance.services.SpreadsheetService;
import com.zifo.ewb.alliance.utils.ConfigResources;
import com.zifo.ewb.alliance.utils.EWBJsonUtils;
import com.zifo.ewb.alliance.utils.GetRowCountIncreaseJson;
import com.zifo.ewb.alliance.utils.GetRowCountJson;
import com.zifo.ewb.alliance.utils.ServiceBase;
import com.zifo.ewb.catalogpojo.CatalogDetailPojo;

@Service
public class AllianceCatalogServiceImplementation implements IAllianceCatalogServiceImplementation {
	
	@Autowired
	public Alliance alliance;
	@Autowired
	public CatalogService catalogService;
	@Autowired
	public AllianceService allianceService;
	@Autowired
	public EWBService ewbService;
	@Autowired
	ProcessAllianceDetailsHelper processDetails;
	
	//refactor
	@Autowired
	EntityTreeService entity;
	@Autowired
	EntityVersionService versions;
	@Autowired
	LockService lockservice;
	@Autowired
	SpreadsheetService spreadsheet;
	
	public static boolean mailSent = false;
	
	public void updateAllianceDettail() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final Map<String, String> terminatedAlliance = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		String modelId = StringUtils.EMPTY;
		mailSent = false;
		
		ServiceBase.loadProperties();
		Alliance.TLS_Version = ConfigResources.getTLSVersion();
		
		try {
			
			String catalogDetails = System.getProperty("Alliance"); // need to separate the layer
			if (catalogDetails == null) {
				UpdateAllianceVariable updateAllianceVariable = new UpdateAllianceVariable();
				catalogDetails = updateAllianceVariable.updateAllianceVariable(); //need to separate the method from Alliance class
			}
			
			String catalogId = catalogService.getCatalogId("/Test/Alliance");
			CatalogDetailPojo catalogDetail = mapper.readValue(catalogDetails, CatalogDetailPojo.class);
			
			String allianceDetailsrespone = allianceService.getAllianceDetails(0, 200000);
			InputPojo allianceDetails = mapper.readValue(allianceDetailsrespone, InputPojo.class);
			
			List<String> groupName = ewbService.getGroupName();
			
			// Adding, removing  & updating the Members and Updating the new alliance name to the catalog
			processDetails.processDetails(catalogDetail, allianceDetails, catalogId, groupName, terminatedAlliance);
			
			List<String> allianceName = AllianceNameHelper.getAllianceNames(allianceDetails);
			
			//  Removing the Alliance [end-group] group & Alliance value from the catalog 
			String UpdatedCatDetails = catalogService.getCatalogDetails(catalogId);
			CatalogDetailPojo updatedcatDetail = mapper.readValue(UpdatedCatDetails, CatalogDetailPojo.class);
			Map<String, String> catAllianceNames = CatlogAllianceNamesHelper.getAllianceNames(updatedcatDetail);
			CheckAllianceRemovedHelper.checkAlliance(catAllianceNames, allianceName, groupName, catalogId,
					terminatedAlliance);
			
			//update the ENV value
			ApiToSpreadsheetJson apiSpreadsheetHelper = new ApiToSpreadsheetJson();
			String allianceCatalogJson = apiSpreadsheetHelper.apitoSpreadsheetJSON(allianceDetailsrespone,catalogId); // need to separate the method from alliance class 
			String spreadsheetId = ewbService.retrieveSpreadsheetId(ServiceBase.getEntityId()); // need to change the service base loading type.
			
			try {
				
				String loadSpreadsheet = ewbService.retrieveLatestVersionId(spreadsheetId);
				ewbService.lockRecord(ServiceBase.getEntityId());    // need to change the service base
				modelId = ewbService.loadSpreadsheet(loadSpreadsheet, Boolean.TRUE);
				String rowCountJSON = ewbService.loadedSpreadsheetDetails(modelId, GetRowCountJson.getRowCount()); //alliance.getRowCount());     // need tochange the input JSON to Utils Calss
				JsonNode jsonObject = mapper.readTree(rowCountJSON);
				int rowcount = Integer.valueOf(
						(jsonObject.get("batch-response").get("api-responses").get(0).get("tables").get("Alliance")
								.get("dimensions").get(1).get("itemCount").toString().replace("\"", "")));
				if (rowcount < alliance.getActiveAlliancesCount(allianceDetailsrespone)) {
					spreadsheet.loadedSpreadsheet(modelId, GetRowCountIncreaseJson.getRowCountIncreaseJson("Alliance", "Index",
							rowcount, alliance.getActiveAlliancesCount(allianceDetailsrespone)));
				}
				ewbService.loadedSpreadsheetDetails(modelId, allianceCatalogJson);
				ewbService.saveSpreadsheet(modelId);
				
			}catch (Exception e) {
				//handle exception
			}
			finally {
				ewbService.unloadSpreadsheet(modelId);
				ewbService.entityDraftSave(ServiceBase.getEntityId(),EWBJsonUtils.draftSaveBody(ServiceBase.getEntityId())); // need to change the service base
				ewbService.unlockRecord(ServiceBase.getEntityId());    	// need to change the service base
				
				String readloadSpreadsheet = ewbService.retrieveLatestVersionId(spreadsheetId);
				String readSpreadsheetData = ewbService.loadedSpreadsheetDetails(
						ewbService.loadSpreadsheet(readloadSpreadsheet, Boolean.FALSE),
						spreadsheet.createJSONtoReadTableData("Alliance"));
				MapSpreadsheetToCatalogData spreadsheetCatalogHelper = new MapSpreadsheetToCatalogData();
				String allianceDataRead = spreadsheetCatalogHelper.mapSpreadsheetToCatalogData(readSpreadsheetData); // need to separate the method from Alliance
				System.setProperty("Alliance", allianceDataRead); // need to separate the layer
			}
			
		}catch(Exception e){
			// handle Exception
		}finally {
			if (!mailSent) {
				String details = "<h3>No new user folders were created in the " + ServiceBase.getEnv() + " Environment"
						+ "</h3>";
				String mailcontent = MailAPI.getNewMailbody(details);
				MailSender.sendMail(mailcontent);
			}
		}
		
	}
}