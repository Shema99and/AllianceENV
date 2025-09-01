package com.zifo.ewb.alliance.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zifo.ewb.alliance.allianceprocesshelper.GetAllianceandIDBSUsers;
import com.zifo.ewb.alliance.entityservicepojo.Entity;
import com.zifo.ewb.alliance.entityservicepojo.EntityService;
import com.zifo.ewb.alliance.entityversionservicepojo.EntityVersionServiceResponse;
import com.zifo.ewb.alliance.entityversionservicepojo.Version;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.restclient.EWBRestClient;
import com.zifo.ewb.alliance.services.Groups;

@Service
public class EWBHelper {

	@Autowired
	EWBRestClient ewbRestClient;

	public List<String> getGroupName(String groupNameResponse) {
		final List<String> groupNames = new ArrayList<>();
		final JSONObject jsonObject = new JSONObject(groupNameResponse);
		final JSONArray group = jsonObject.getJSONArray("group");
		for (int i = 0; i < group.length(); i++) {
			groupNames.add(group.getJSONObject(i).getString("name"));
		}
		return groupNames;
	}

	public String getUserFullNameFromResponse(String response) {
		String userName = StringUtils.EMPTY;
		JSONObject jsonobject = new JSONObject(response);
		if (jsonobject.getJSONArray("userlist").length() > 0) {
			userName = jsonobject.getJSONArray("userlist").getJSONObject(0).get("userFullName").toString();
			GetAllianceandIDBSUsers.disabledUser = jsonobject.getJSONArray("userlist").getJSONObject(0)
					.getBoolean("disabled");
		}
		return userName;
	}

	public String getActiveUserFullName(String response) {
		String userName = StringUtils.EMPTY;
		if (!response.equals(StringUtils.EMPTY)) {
			final JSONObject jsonobject = new JSONObject(response);
			if (jsonobject.getJSONArray("userlist").length() > 0) {
				if (!jsonobject.getJSONArray("userlist").getJSONObject(0).getBoolean("disabled")) {
					userName = jsonobject.getJSONArray("userlist").getJSONObject(0).get("userFullName").toString();
				}
			}
		}
		return userName;
	}

	public String getBusinessAreaEntityIdFromResponse(String response, String businessAreaName) {
		String entityID = StringUtils.EMPTY;
		final JSONObject jsonObject = new JSONObject(response);
		final JSONArray entity = jsonObject.getJSONArray("entity");

		for (int i = 0; i < entity.length(); i++) {
			if (businessAreaName.equals(entity.getJSONObject(i).getString("entityName"))) {
				entityID = entity.getJSONObject(i).getString("entityId");
				break;
			}
		}
		return entityID;
	}

	public List<String> getBusinessAreaUserList(String response) {
		final List<String> userList = new ArrayList<>();
		final JSONObject jsonObject = new JSONObject(response);
		final JSONArray entity = jsonObject.getJSONArray("entity");

		for (int i = 0; i < entity.length(); i++) {
			if ("USER".equals(entity.getJSONObject(i).getString("entityTypeName"))) {
				userList.add(entity.getJSONObject(i).getString("entityName"));
			}
		}
		return userList;
	}

	public List<String> getGroupMember(String response, String groupName) throws 
//			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, URISyntaxException, 
			IOException, ApiException {

		ObjectMapper mapper = new ObjectMapper();
		List<String> member = new ArrayList<>();
		try {
			Groups group = mapper.readValue(response, Groups.class);
			List<String> admins = group.getAdmins().getName();
			member = group.getMembers().getName();
			member.addAll(admins);
		} catch (Exception ex) {
			String description = "Alliance Group for " + groupName;
			String requestBody = "{\r\n" + "  \"name\" : \"" + groupName + "\",\r\n" + "  \"description\" : \""
					+ description + "\"\r\n" + "}";
			ewbRestClient.createGroup(requestBody);
		}
		return member;
	}

	public String getSpreadsheetIdFromResponse(String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		EntityService entitytree = mapper.readValue(response, EntityService.class);
		String spreadsheetId = StringUtils.EMPTY;
		for (Entity singleEntity : entitytree.getEntity()) {
			if (singleEntity.getEntityTypeName().equals("IDBS_SPREADSHEET")) {
				spreadsheetId = singleEntity.getEntityId();
			}
		}
		return spreadsheetId;
	}
	
	public String getLatestVersionId(String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		EntityVersionServiceResponse entityversion = mapper.readValue(response, EntityVersionServiceResponse.class);
		int versionNumber = 1;
		String latestVersionId = StringUtils.EMPTY;
		for (Version singleversion : entityversion.getVersion()) {
			if (versionNumber < singleversion.getVersionNumber()) {
				versionNumber = singleversion.getVersionNumber();
				latestVersionId = singleversion.getVersionId();
			} else if (singleversion.getVersionNumber() == 1) {
				latestVersionId = singleversion.getVersionId();
			}
		}
		return latestVersionId;
	}
}
