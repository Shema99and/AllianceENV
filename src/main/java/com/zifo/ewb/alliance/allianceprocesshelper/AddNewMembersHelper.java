package com.zifo.ewb.alliance.allianceprocesshelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.controller.AllianceCatalog;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.mailcontent.MailGenerationService;
import com.zifo.ewb.alliance.serviceh.EWBService;

/**
 * This class is used to find if the new user is added to the existing alliance
 * 
 * @author Zifo
 *
 */
public final class AddNewMembersHelper {

	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AddNewMembersHelper.class.getName());

	public static final List<String> removedUserList = new ArrayList<>();

	public static final List<String> addedUserList = new ArrayList<>();

	public static final List<String> activeUserList = new ArrayList<>();

	/**
	 * method to add new members
	 * 
	 * @param newMemberList @param allianceGroupName
	 * @param groupName
	 * @return
	 * @throws ApiException
	 */
	public static void addNewUsers(final Map<String, String> newMemberList, final String allianceGroupName,
			final List<String> groupName) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException, ApiException {
		EWBService ewbService = new EWBService();
		if (newMemberList.isEmpty()) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("New Member List is Empty");
			}
		} else {
			final String businessAreaName = "GSK_Alliance-" + allianceGroupName;
			final String businessEntityId = CheckBusinessAreaExistenceHelper.checkBusinessArea(businessAreaName);
			if (businessEntityId.isEmpty()) {
				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("Business Area " + businessAreaName + " Not Found ");
					LOGGER.info("Business Area {} Not Found", businessAreaName);
				}
			} else {
//				SecurityService securityService = new SecurityService();
//				final String admins = ConfigResources.getDefaultAdmins();
//				final List<String> adminList = Arrays.asList(admins.split(","));
//				final List<String> adminEmails = securityService.getMailId(adminList);
				final String endGroup = "G_GSK_Alliance-" + allianceGroupName + "_EndUser_Edit";
				final String readGroup = "G_GSK_Alliance-" + allianceGroupName + "_Read";
				final List<String> userList = GetUsersUnderBusinessAreaHelper.getUsers(businessEntityId);
				final Map<String, String> userFolderNames = new LinkedHashMap<>();
				final Map<String, String> ReaddedUsers = new LinkedHashMap<>();
				List<String> endgroupMembers = ewbService.getGroupMembers(endGroup);
				List<String> readGroupMembers = ewbService.getGroupMembers(readGroup);
//				EntityService entityService = new EntityService();
				for (final Entry<String, String> user : newMemberList.entrySet()) {
					if (userList.contains(user.getValue())) {
						if (LOGGER.isInfoEnabled()) {
//							LOGGER.info("User folder already exist for user " + user);
							LOGGER.info("User folder already exists for user {}", user);
							}
					} else {
						ewbService.createUserEntity(businessEntityId,
								JsonBodiesHelper.getUserCreationBodies(user.getValue()));
						userFolderNames.put(user.getValue(), user.getKey());

						if (groupName.contains(endGroup)) {
							ewbService.addMemberToGroup(endGroup, user.getKey());
						}
						if (groupName.contains(readGroup)) {
							ewbService.addMemberToGroup(readGroup, user.getKey());
						}
					}

					if (!(endgroupMembers.contains(user.getValue()) || readGroupMembers.contains(user.getValue()))) {
						ReaddedUsers.put(user.getValue(), user.getKey());
						if (!endgroupMembers.contains(user.getValue())) {
							ewbService.addMemberToGroup(endGroup, user.getKey());
						}

						if (!readGroupMembers.contains(user.getValue())) {
							ewbService.addMemberToGroup(readGroup, user.getKey());
						}
					}
				}
				// if (userFolderNames.isEmpty()) {
				if (AddNewMembersHelper.addedUserList != null || AddNewMembersHelper.removedUserList != null) {
					AllianceCatalog.mailSent = true;
					MailGenerationService.mailGeneration(allianceGroupName, AddNewMembersHelper.removedUserList,
							AddNewMembersHelper.addedUserList, AddNewMembersHelper.activeUserList);
				}

//				} else {
//					mailGeneration(allianceGroupName, AddNewMembersHelper.removedUserList,
//							AddNewMembersHelper.addedUserList, AddNewMembersHelper.activeUserList);
//				}

			}
		}
	}
}
