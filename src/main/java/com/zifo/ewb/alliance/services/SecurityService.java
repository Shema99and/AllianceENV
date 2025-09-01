package com.zifo.ewb.alliance.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.zifo.ewb.alliance.allianceprocesshelper.GetAllianceandIDBSUsers;
import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JsonConstantsUtils;
import com.zifo.ewb.alliance.utils.ServiceBase;

/**
 * This class is used to do API operations based on administrations and security
 * 
 * @author Zifo
 *
 */
public class SecurityService extends ServiceBase {
	/**
	 * Field LOGGER
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class.getName());
	/**
	 * Field admin
	 */
	public static final String SEADGR = "/security/administration/groups/";

	/**
	 * This method is used to get list of groups present in administration
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 */
	public List<String> getGroups() throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final List<String> groupNames = new ArrayList<>();
		final String url = getBaseServiceURL() + SEADGR;
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
		final String apiOutput = getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("Get Group URL :" + url);
//			LOGGER.info("Get Group Response : " + apiOutput);
			LOGGER.info("Get Group URL: {}", url);
			LOGGER.info("Get Group Response: {}", apiOutput);
			final JSONObject jsonObject = new JSONObject(apiOutput);
			final JSONArray group = jsonObject.getJSONArray("group");
			for (int i = 0; i < group.length(); i++) {
				groupNames.add(group.getJSONObject(i).getString("name"));
			}
//			LOGGER.info("Group Name List is : " + groupNames);
			LOGGER.info("Group Name List is: {}", groupNames);
		}
		return groupNames;
	}

	public static List<String> getGroupMembers(String groupName) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		String URL = getBaseServiceURL() + "/security/administration/groups/" + groupName;
		final String apiOutput = getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executeGetRequest(getUriFactory().buildURI(URL));
		ObjectMapper mapper = new ObjectMapper();
		List<String> member = new ArrayList<>();
		try {
			Groups group = mapper.readValue(apiOutput, Groups.class);
			List<String> admins = group.getAdmins().getName();
			member = group.getMembers().getName();
			member.addAll(admins);
		} catch (Exception ex) {
			SecurityService.createGroup(groupName, "Alliance Group for " + groupName);
		}
		return member;

	}

	/**
	 * this method will delete an admin from a group
	 * 
	 * @param groupName
	 * @param admin
	 */
	public void deleteAdminFromGroup(final String groupName, final String admin) {

		try {
			final String url = getBaseServiceURL() + SEADGR + groupName + "/admin/" + admin;
			final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
			final String apiOutput = getRESTExecutor(ServiceBase.getAuthorization(), ServiceBase.getAcceptJSON(),
					ServiceBase.getAcceptJSON())
							.executeDeleteRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
			if (LOGGER.isInfoEnabled()) {
//				LOGGER.info("Delete Admin From Group URL : " + url);
//				LOGGER.info("Admin removed response : " + apiOutput);
				LOGGER.info("Delete Admin From Group URL: {}", url);
				LOGGER.info("Admin removed response: {}", apiOutput);
			}
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException
				| IOException | URISyntaxException e) {
			LOGGER.info(e.getMessage());
		}

	}

	public List<String> getMailId(final List<String> userNameList) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {

//		String mailId = StringUtils.EMPTY;
		List<String> mailIds = new ArrayList<>();
		for (String userName : userNameList) {
			final String url = getBaseServiceURL() + AppConstantsUtils.SECURITY_USERS;
			if (LOGGER.isDebugEnabled()) {
//				LOGGER.debug("URL " + url);
				LOGGER.debug("URL: {}", url);
			}
			final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
					.put(AppConstantsUtils.USER_NAME, userName.trim()).build();
			String response = getRESTExecutor(ServiceBase.getAuthorization(), ServiceBase.getAcceptJSON(),
					ServiceBase.getAcceptJSON())
							.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
			final JSONObject jsonobject = new JSONObject(response);
			if (jsonobject.getJSONArray(AppConstantsUtils.USER_LIST).length() > 0) {
				String mailId = jsonobject.getJSONArray(AppConstantsUtils.USER_LIST).getJSONObject(0).getJSONObject("emails")
						.getJSONArray("email").getString(0);
				mailIds.add(mailId);
			}
		}
		return mailIds;
	}

	/**
	 * This method will delete an member from an existing group
	 * 
	 * @param groupName
	 * @param member
	 */
	public static void deleteMemberFromGroup(final String groupName, final String member) {

		try {
			final String url = getBaseServiceURL() + SEADGR + groupName + "/member/" + member;
			final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
			final String apiOutput = getRESTExecutor(ServiceBase.getAuthorization(), ServiceBase.getAcceptJSON(),
					ServiceBase.getAcceptJSON())
							.executeDeleteRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
			if (LOGGER.isInfoEnabled() || LOGGER.isDebugEnabled()) {
//				LOGGER.debug("Delete Member From Group URL : " + url);
//				LOGGER.info("Member removed response : " + apiOutput);
				LOGGER.debug("Delete Member From Group URL: {}", url);
				LOGGER.info("Member removed response: {}", apiOutput);
			}
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException
				| IOException | URISyntaxException e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * this method will delete an existing group
	 * 
	 * @param groupName
	 */
	public void deleteGroup(final String groupName) {

		try {
			final String url = getBaseServiceURL() + SEADGR + groupName;
			final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
			final String apiOutput = getRESTExecutor(ServiceBase.getAuthorization(), ServiceBase.getAcceptJSON(),
					ServiceBase.getAcceptJSON())
							.executeDeleteRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
			if (LOGGER.isInfoEnabled() || LOGGER.isDebugEnabled()) {
//				LOGGER.debug("Delete Group URL : " + url);
//				LOGGER.info("Member removed response : " + apiOutput);
				LOGGER.debug("Delete Group URL: {}", url);
				LOGGER.info("Member removed response: {}", apiOutput);
			}
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException
				| IOException | URISyntaxException e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * method to get fullName of the IDBS Users
	 * 
	 * @param user
	 * @return
	 */
	public String getUserName(final String user) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		String userName = StringUtils.EMPTY;
		GetAllianceandIDBSUsers.disabledUser = true;
		final String url = getBaseServiceURL() + "/security/administration/users";
		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("URL " + url);
			LOGGER.info("URL: {}", url);
		}
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("userName", user.trim()).build();
		final String response = getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
		final JSONObject jsonobject = new JSONObject(response);
		if (jsonobject.getJSONArray("userlist").length() > 0) {
			userName = jsonobject.getJSONArray("userlist").getJSONObject(0).get("userFullName").toString();
			GetAllianceandIDBSUsers.disabledUser = jsonobject.getJSONArray("userlist").getJSONObject(0)
					.getBoolean("disabled");
		}
		return userName;
	}

	/**
	 * This method is used to add new members to the group
	 * 
	 * @param grpName @param member
	 * @return
	 */
	public String addGroupMember(final String grpName, final String member) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {

		final String url = getBaseServiceURL() + SEADGR + grpName + "/member/" + member;
		String response = getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executePutRequest(getUriFactory().buildURI(url), null);
		if (response.contains("No user with name") || response.contains("Resource not found for given ID")) {
			response = "Invalid username found";
		}
		return response;
	}

	public static String createGroup(final String grpName, final String descrip) throws URISyntaxException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		final String url = getBaseServiceURL() + SEADGR;
		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("Group Create Url " + url);
			LOGGER.info("Group Create URL: {}", url);
		}
		final String body = "{\r\n" + "  \"name\" : \"" + grpName + "\",\r\n" + "  \"description\" : \"" + descrip
				+ "\"\r\n" + "}";

		final HttpEntity entity = new StringEntity(body);
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executePostRequest(getUriFactory().buildURI(url), entity);

	}
}