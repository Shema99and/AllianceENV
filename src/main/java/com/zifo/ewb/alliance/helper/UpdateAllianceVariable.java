package com.zifo.ewb.alliance.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.StringUtils;

import com.zifo.ewb.alliance.controller.Alliance;
import com.zifo.ewb.alliance.serviceh.AllianceService;
import com.zifo.ewb.alliance.serviceh.EWBService;
import com.zifo.ewb.alliance.services.CatalogService;
import com.zifo.ewb.alliance.services.LockService;
import com.zifo.ewb.alliance.services.SpreadsheetService;
import com.zifo.ewb.alliance.utils.ConfigResources;
import com.zifo.ewb.alliance.utils.GetRowCountIncreaseJson;
import com.zifo.ewb.alliance.utils.ServiceBase;

public class UpdateAllianceVariable {
	private static final Logger LOGGER = LogManager.getLogger(Alliance.class.getName());

	public String updateAllianceVariable() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException {

		EWBService ewbService = new EWBService();
		AllianceService allianceService = new AllianceService();
		CatalogService catalogService = new CatalogService();

		ServiceBase.loadProperties();
		LOGGER.info("Alliance Environment variable updating started");
		Alliance.TLS_Version = ConfigResources.getTLSVersion();
//EntityTreeService entity = new EntityTreeService();
//EntityVersionService versions = new EntityVersionService();
		SpreadsheetService spreadsheet = new SpreadsheetService();
		LockService lockservice = new LockService();
		String modelId = StringUtils.EMPTY;

//ObjectMapper mapper = new ObjectMapper();
		try {
			String spreadsheetId = ewbService.retrieveSpreadsheetId(ServiceBase.getEntityId());
			String loadSpreadsheet = ewbService.retrieveLatestVersionId(spreadsheetId);
			modelId = ewbService.loadSpreadsheet(loadSpreadsheet, Boolean.FALSE);
			String spreadsheetData = ewbService.loadedSpreadsheetDetails(modelId,
					spreadsheet.createJSONtoReadTableData("Alliance"));
			MapSpreadsheetToCatalogData spreadsheetCatalogHelper = new MapSpreadsheetToCatalogData();
			String allianceData = spreadsheetCatalogHelper.mapSpreadsheetToCatalogData(spreadsheetData);
			if (allianceData.isEmpty()) {
				try {
					ewbService.unloadSpreadsheet(modelId);
					String response = allianceService.getAllianceDetails(0, 200000);
					String catalogId = catalogService.getGuid("/Test/Alliance");
					ApiToSpreadsheetJson apiSpreadsheetHelper = new ApiToSpreadsheetJson();
					String inputJSON = apiSpreadsheetHelper.apitoSpreadsheetJSON(response, catalogId);
					lockservice.lockRecord(ServiceBase.getEntityId());
					modelId = spreadsheet.loadSpreadsheet(loadSpreadsheet, Boolean.TRUE);
					LOGGER.info(modelId);
					Alliance alliance = new Alliance();
					spreadsheet.loadedSpreadsheet(modelId, GetRowCountIncreaseJson.getRowCountIncreaseJson("Alliance",
							"Index", 1, alliance.getActiveAlliancesCount(response)));
					spreadsheet.loadedSpreadsheet(modelId, inputJSON);
					spreadsheet.saveSpreadsheet(modelId);
				} catch (Exception exc1) {
					LOGGER.error(exc1.getMessage(), exc1);
				} finally {
					try {
						spreadsheet.unloadSpreadsheet(modelId);
						spreadsheet.callDraftSave(ServiceBase.getEntityId());
					} catch (Exception exception) {
						LOGGER.error(exception.getMessage(), exception);
					} finally {
						lockservice.unlockRecord(ServiceBase.getEntityId());
						String readloadSpreadsheet = ewbService.retrieveLatestVersionId(spreadsheetId);
						String readSpreadsheetData = spreadsheet.loadedSpreadsheet(
								spreadsheet.loadSpreadsheet(readloadSpreadsheet, Boolean.FALSE),
								spreadsheet.createJSONtoReadTableData("Alliance"));
						String allianceDataRead = spreadsheetCatalogHelper
								.mapSpreadsheetToCatalogData(readSpreadsheetData);
						System.setProperty("Alliance", allianceDataRead);
						LOGGER.info(System.getProperty("Alliance"));
					}
				}
			} else {
				System.setProperty("Alliance", allianceData);
				String props = System.getProperty("Alliance");
				LOGGER.info(props);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			LOGGER.info("Error while updating the Alliance Environment variable");
		} finally {
			spreadsheet.unloadSpreadsheet(modelId);
			LOGGER.info("Alliance Environment variable updating ended");
		}
		return System.getProperty("Alliance");
	}

}
