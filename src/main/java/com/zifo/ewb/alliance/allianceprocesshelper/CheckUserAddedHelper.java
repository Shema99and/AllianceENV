package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;

/**
 * This class will check if new user is added in the alliance and return them
 * 
 * @author Zifo
 */
public final class CheckUserAddedHelper {
	private CheckUserAddedHelper() {
	}

	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserAddedHelper.class.getName());

	/**
	 * method ti find the newly added users
	 * 
	 * @param idbsUsers @param catalogUsers
	 * @return
	 * @throws ApiException 
	 */
	public static Map<String, String> checkUsers(final List<String> idbsUsers, final List<String> catIDBSUsers)
			throws ApiException {
//			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
//			IOException, URISyntaxException
		final Map<String, String> newUsers = new LinkedHashMap<>();
		EWBService ewbService = new EWBService();
		for (final String idbsuser : idbsUsers) {
			if (catIDBSUsers.contains(idbsuser)) {
				continue;
			} else {
				final String validUser = ewbService.getUserFullName(idbsuser);
				if (validUser.isEmpty()) {
					if (LOGGER.isInfoEnabled()) {
//						LOGGER.info("The User " + idbsuser + " does not have IDBS account");
						LOGGER.info("The User {} does not have IDBS account", idbsuser); 
					}
				} else {
					newUsers.put(idbsuser, validUser);
					AddNewMembersHelper.addedUserList.add(validUser + "-" + idbsuser);
				}
			}
		}

		for (String catUser : catIDBSUsers) {
			if (!idbsUsers.contains(catUser)) {
				AddNewMembersHelper.removedUserList.add(ewbService.getUserFullName(catUser) + "-" + catUser);
			} else {
				AddNewMembersHelper.activeUserList.add(ewbService.getUserFullName(catUser) + "-" + catUser);
			}
		}
		return newUsers;
	}

//	private CheckUserAddedHelper() {
//
//	}
}
