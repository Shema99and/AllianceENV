package com.zifo.ewb.alliance.serviceh;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.helper.EWBHelper;
import com.zifo.ewb.alliance.restclient.EWBRestClient;

@Service
public class EWBService {

	@Autowired
	public EWBRestClient ewbRestClient;
	@Autowired
	public EWBHelper ewbHelper;

	public List<String> getGroupName() throws ApiException {
		String groupNameResponse = ewbRestClient.getGroupName();
//		List<String> groupName = ewbHelper.getGroupName(groupNameResponse);
		return ewbHelper.getGroupName(groupNameResponse);
	}

	public String getUserFullName(String user) throws ApiException {
		String userFullNameResponse = ewbRestClient.getUserFullName(user);
//		String userFullName = ewbHelper.getUserFullNameFromResponse(userFullNameResponse);
		return ewbHelper.getUserFullNameFromResponse(userFullNameResponse);
	}
	
	public String getActiveUserFullName(String user) throws ApiException {
		String activeUserFullNameResponse = ewbRestClient.getUserFullName(user);
//		String activeUserFullName = ewbHelper.getActiveUserFullName(activeUserFullNameResponse);
		return ewbHelper.getActiveUserFullName(activeUserFullNameResponse);
	}

	public String getBusinessAreaUnderRoot(String businessAreaName) throws ApiException {
		String businessAreaUnderRootResponse = ewbRestClient.getBuisnessAreaUnderRoot();
//		String businessAreaUnderRootEntityId = ewbHelper
//				.getBusinessAreaEntityIdFromResponse(businessAreaUnderRootResponse, businessAreaName);
		return ewbHelper
				.getBusinessAreaEntityIdFromResponse(businessAreaUnderRootResponse, businessAreaName);
	}

	public List<String> getBusinessAreaUserList(String entityId) throws ApiException {
		String childEntitiesResponse = ewbRestClient.getChildEntities(entityId);
//		List<String> userList = ewbHelper.getBusinessAreaUserList(childEntitiesResponse);
		return  ewbHelper.getBusinessAreaUserList(childEntitiesResponse);
	}

	public List<String> getGroupMembers(String groupName) throws ApiException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, URISyntaxException, IOException {
		String groupMemberResponse = ewbRestClient.getGroupMembers(groupName);
//		List<String> groupMember = ewbHelper.getGroupMember(groupMemberResponse, groupName);
		return ewbHelper.getGroupMember(groupMemberResponse, groupName);

	}

	public String createUserEntity(String entityId, String requestBody) throws ApiException {
//		String userEntityResponse = ewbRestClient.createUserFolder(entityId, requestBody);
		return ewbRestClient.createUserFolder(entityId, requestBody);
	}

	public String addMemberToGroup(String group, String user) throws ApiException {
		String addMemberResponse = ewbRestClient.addMemberToGroup(group, user);
		if (addMemberResponse.contains("No user with name")
				|| addMemberResponse.contains("Resource not found for given ID")) {
			addMemberResponse = "Invalid username found";
		}
		return addMemberResponse;
	}

	public String deleteMemberFromGroup(String group, String user) throws ApiException {
//		String deleteMemberResponse = ewbRestClient.removeMemberFromGroup(group, user);
		return ewbRestClient.removeMemberFromGroup(group, user);
	}

	public String createTuple(String catalogId, String requestBody) throws ApiException {
		String response = StringUtils.EMPTY;
		String creatTupleResponse = ewbRestClient.createTuple(catalogId, requestBody);
		JSONObject jsonObject = new JSONObject(creatTupleResponse);
		if (StringUtils.isEmpty(jsonObject.getString("id"))) {
			response = "Tuple Creation Failed";
		} else {
			response = "Tuple Created successfully";
		}
		return response;
	}
	
	public String deleteTuple(String catalogId, String tupleGuId) throws ApiException {
//		String deleteTupleResponse = ewbRestClient.deleteTuple(catalogId, tupleGuId);
		return ewbRestClient.deleteTuple(catalogId, tupleGuId);
	}
	
	public String deleteGroup(String groupName) throws ApiException {
//		String deleteGroupResponse = ewbRestClient.deleteGroup(groupName);
		return ewbRestClient.deleteGroup(groupName);
	}
	
	public String retrieveSpreadsheetId(String experimentEntityId) throws ApiException, JsonProcessingException {
		String spreadsheetEntityIdResponse = ewbRestClient.getChildEntities(experimentEntityId);
//		String spreadsheetEntityId = ewbHelper.getSpreadsheetIdFromResponse(spreadsheetEntityIdResponse);
		return ewbHelper.getSpreadsheetIdFromResponse(spreadsheetEntityIdResponse);
	}
	
	public String retrieveLatestVersionId(String entityId) throws ApiException, JsonProcessingException {
		String versionResponse = ewbRestClient.getversionDetails(entityId);
//		String latestVersionId= ewbHelper.getLatestVersionId(versionResponse);
		return ewbHelper.getLatestVersionId(versionResponse);
	}
	
	public String lockRecord(String experimentEntityId) throws ApiException {
//		String lockRecord = ewbRestClient.lockRecord(experimentEntityId);
		return ewbRestClient.lockRecord(experimentEntityId);
	}
	
	public String loadSpreadsheet(String versionId, Boolean editMode) throws ApiException {
//		String modelId = ewbRestClient.loadSpreadsheet(versionId, editMode);
		return ewbRestClient.loadSpreadsheet(versionId, editMode);
	}
	public String loadedSpreadsheetDetails(String modelId, String requestBody) throws ApiException {
//		String spreadsheetDetailsResponse = ewbRestClient.loadedSpreadsheetDetails(modelId, requestBody);
		return ewbRestClient.loadedSpreadsheetDetails(modelId, requestBody);
	}
	
	public String saveSpreadsheet(String modelId) throws ApiException {
//		String saveSpreadsheetResponse = ewbRestClient.saveSpreadsheet(modelId);
		return ewbRestClient.saveSpreadsheet(modelId);
	}
	
	public String unloadSpreadsheet(String modelId) throws ApiException {
//		String unloadSpreadsheetResponse = ewbRestClient.unloadSpreadhseet(modelId);
		return ewbRestClient.unloadSpreadhseet(modelId);
	}
	
	public String entityDraftSave(String entityId, String requestBody) throws ApiException {
//		String draftSaveResponse = ewbRestClient.entityDraftSave(entityId, requestBody);
		return ewbRestClient.entityDraftSave(entityId, requestBody);
	}
	
	public String unlockRecord(String entityId) throws ApiException {
//		String unlockRecordResponse = ewbRestClient.unlockRecord(entityId);
		return ewbRestClient.unlockRecord(entityId);
	}

}
