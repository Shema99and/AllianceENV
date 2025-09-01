package com.zifo.ewb.alliance.helper;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsk.api.datarestrictionpojo.InputPojo;
import com.zifo.ewb.alliance.allianceprocesshelper.GetAllianceandIDBSUsers;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;
import com.zifo.ewb.catalogpojo.Tuple;

public class ApiToSpreadsheetJson {
	public String apitoSpreadsheetJSON(String response, String catalogId)   // need to put in helper class
			throws IOException, KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, URISyntaxException, ApiException {
		EWBService ewbService = new EWBService();
		ObjectMapper mapper = new ObjectMapper();
		final InputPojo allianceDetails = mapper.readValue(response, InputPojo.class);
		int count = 0;
		List<Map<String, String>> allData = new ArrayList<>();
//		String catalogId = catalogService.getGuid("/Test/Alliance");
		for (com.gsk.api.datarestrictionpojo.Datum allianceData : allianceDetails.getData()) {
			if (!allianceData.getStatus().equalsIgnoreCase("Terminated")) {
				final Tuple input = new Tuple();
				input.setId(catalogId);
				input.setDefaultTuple(false);
				input.setDeletable(true);
				input.setEditable(true);
				input.setEnabled(true);
				List<com.zifo.ewb.catalogpojo.Datum> dataList = new ArrayList<>();
				com.zifo.ewb.catalogpojo.Datum catalogData = new com.zifo.ewb.catalogpojo.Datum();
				catalogData.setName("Alliance Name");
				catalogData.setValue(allianceData.getName());
				catalogData.setDisplayValue(allianceData.getName());
				dataList.add(catalogData);
				input.setData(dataList);
				ewbService.createTuple(catalogId, mapper.writeValueAsString(input));
				count = count + 1;
				Map<String, String> data = new HashMap<>();
				Map<String, List<String>> users = GetAllianceandIDBSUsers.getActiveUsers(allianceData);
				List<String> allianceUsers = users.get("allianceUserList");
				List<String> idbsUsers = users.get("idbsUserList");
				data.put("IDBS Members", String.join(",", idbsUsers));
				data.put("Alliance Members", String.join(",", allianceUsers));
				data.put("Physical Compound Restriction",
						allianceData.getPhysicalCompoundRestriction().getRestriction());
				data.put("Experiment Restriction", allianceData.getExperimentRestriction().getRestriction());
				data.put("Alliance Name", allianceData.getName());
				data.put("Index", Integer.toString(count));
				allData.add(data);
			}
		}
		Map<String, String> columnTypes = new HashMap<>();
		columnTypes.put("IDBS Members", "string");
		columnTypes.put("Alliance Members", "string");
		columnTypes.put("Physical Compound Restriction", "string");
		columnTypes.put("Experiment Restriction", "string");
		columnTypes.put("Alliance Name", "string");
		columnTypes.put("Index", "string");
		return GetTableUpdateJson.getTableUpdateJSON(allData, "Alliance", columnTypes);
	}

}
