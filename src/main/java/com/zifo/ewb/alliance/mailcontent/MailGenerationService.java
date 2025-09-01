package com.zifo.ewb.alliance.mailcontent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MailGenerationService {
	private MailGenerationService() {
	}
	
	public static void mailGeneration(String allianceGroupName, List<String> removedUserList,
			List<String> addedUserList, List<String> existingActiveUserList) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {

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
