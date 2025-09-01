/**
 * 
 */
package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;

/**
 * This class is used to create new group and add members in it and if the group
 * is already exist it only add the members in it
 * 
 * @author Zifo
 *
 */
public final class GroupCreatorHelper {
	private GroupCreatorHelper() {
	}
	/**
	 * Field logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupCreatorHelper.class.getName());

	/**
	 * This method is used to create group and add members in it
	 * 
	 * @throws ApiException
	 */
	public static boolean UpdateGroup(final List<String> groupName, final String endUserGroup,
			final String readUserGroup, final List<String> removedUser) throws ApiException {
		boolean check = false;
		EWBService ewbService = new EWBService();
		if (groupName.contains(endUserGroup)) {
			for (final String member : removedUser) {
				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("Member Exists in both Alliance and Catalog " + member);
					LOGGER.info("Member Exists in both Alliance and Catalog {}", member);
				}
				// SecurityService.deleteAdminFromGroup(superUserGroup, member);
				int index = member.indexOf("-");
				ewbService.deleteMemberFromGroup(endUserGroup, member.substring(index + 1));
				check = true;

			}
		}

		if (groupName.contains(readUserGroup)) {
			for (final String member : removedUser) {
				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("Member Exists in both Alliance and Catalog " + member);
					LOGGER.info("Member Exists in both Alliance and Catalog {}", member);
				}
				// SecurityService.deleteAdminFromGroup(superUserGroup, member);
				int index = member.indexOf("-");
				ewbService.deleteMemberFromGroup(readUserGroup, member.substring(index + 1));
				check = true;
			}
		}
		return check;
	}
}
