package com.zifo.ewb.alliance.allianceprocess;

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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.allianceprocesshelper.CheckBusinessAreaExistenceHelper;
import com.zifo.ewb.alliance.allianceprocesshelper.GetUsersUnderBusinessAreaHelper;
import com.zifo.ewb.alliance.allianceprocesshelper.JsonBodiesHelper;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.mailcontent.MailAPI;
import com.zifo.ewb.alliance.mailcontent.MailSender;
import com.zifo.ewb.alliance.services.EntityService;
import com.zifo.ewb.alliance.services.SecurityService;


/**
 * This class is used to find if the new user is added to the existing alliance
 * 
 * @author Zifo
 *
 */
public final class AddNewMembersHelper {
	private AddNewMembersHelper() {
	}
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
				SecurityService securityService = new SecurityService();
//				final String admins = ConfigResources.getDefaultAdmins();
//				final List<String> adminList = Arrays.asList(admins.split(","));
//				final List<String> adminEmails = securityService.getMailId(adminList);
				final String endGroup = "G_GSK_Alliance-" + allianceGroupName + "_EndUser_Edit";
				final String readGroup = "G_GSK_Alliance-" + allianceGroupName + "_Read";
				final List<String> userList = GetUsersUnderBusinessAreaHelper.getUsers(businessEntityId);
				final Map<String, String> userFolderNames = new LinkedHashMap<>();
				final Map<String, String> ReaddedUsers = new LinkedHashMap<>();
				List<String> endgroupMembers = securityService.getGroupMembers(endGroup);
				List<String> readGroupMembers = securityService.getGroupMembers(readGroup);
				for (final Entry<String, String> user : newMemberList.entrySet()) {
					if (userList.contains(user.getValue())) {
						if (LOGGER.isInfoEnabled()) {
//							LOGGER.info("User folder already exist for user " + user);
							LOGGER.info("User folder already exists for user {}", user);
						}
					} else {
						EntityService entityService = new EntityService();
						entityService.createChildRequest(businessEntityId,
								JsonBodiesHelper.getUserCreationBodies(user.getValue()));
						userFolderNames.put(user.getValue(), user.getKey());

						if (groupName.contains(endGroup)) {
							securityService.addGroupMember(endGroup, user.getKey());
						}
						if (groupName.contains(readGroup)) {
							securityService.addGroupMember(readGroup, user.getKey());
						}
					}

					if (!(endgroupMembers.contains(user.getValue()) || readGroupMembers.contains(user.getValue()))) {
						ReaddedUsers.put(user.getValue(), user.getKey());
						if (!endgroupMembers.contains(user.getValue())) {
							securityService.addGroupMember(endGroup, user.getKey());
						}

						if (!readGroupMembers.contains(user.getValue())) {
							securityService.addGroupMember(readGroup, user.getKey());
						}
					}
				}
				// if (userFolderNames.isEmpty()) {
				if (AddNewMembersHelper.addedUserList != null || AddNewMembersHelper.removedUserList != null) {
					mailGeneration(allianceGroupName, AddNewMembersHelper.removedUserList,
							AddNewMembersHelper.addedUserList, AddNewMembersHelper.activeUserList);
				}

//				} else {
//					mailGeneration(allianceGroupName, AddNewMembersHelper.removedUserList,
//							AddNewMembersHelper.addedUserList, AddNewMembersHelper.activeUserList);
//				}

			}
		}
	}

	public static void mailGeneration(String allianceGroupName, List<String> removedUserList,
			List<String> addedUserList, List<String> existingActiveUserList) throws 
//	KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, URISyntaxException {
		IOException {
		String det1 = StringUtils.EMPTY;
		String det2 = StringUtils.EMPTY;
		String det3 = StringUtils.EMPTY;
		final String details1 = "<h3>Removed User List:- </h3>\r\n"
				+ "<p>This list will contain all users who are removed from the alliance.<p>\r\n" + "<ul>\r\n";

		for (int i = 0; i < removedUserList.size(); i++) {
			final String details2 = "  <li> " + removedUserList.get(i) + " </li>\r\n";
			det1 = det1 + details2;

		}

		final String details3 = "</ul> " + "<h3>Added User List:- </h3>\r\n"
				+ "<p>This List will contain all users who are added newly in the alliance.<p>\r\n" + "<ul>\r\n";
		for (int i = 0; i < addedUserList.size(); i++) {
			final String details2 = "  <li> " + addedUserList.get(i) + " </li>\r\n";
			det2 = det2 + details2;
		}
		final String details4 = "</ul> " + "<h3>Existing Active User List:- </h3>\r\n"
				+ "<p>This List will contain all existing users who are currently active.<p>\r\n" + "<ul>\r\n";
		for (int i = 0; i < existingActiveUserList.size(); i++) {
			final String details2 = "  <li> " + existingActiveUserList.get(i) + " </li>\r\n";
			det3 = det3 + details2;
		}
		final String details5 = "</ul> ";
		final String details = details1 + det1 + details3 + det2 + details4 + det3 + details5;

//		final String mailContent = MailAPI.getMailbody(details, userFolderNames);
		final String mailContent = MailAPI.getNewMailbody(details);

//		MailSender.sendMailToAdmins(mailContent);
		MailSender.sendMailAlliance(mailContent, allianceGroupName);
	}

}
