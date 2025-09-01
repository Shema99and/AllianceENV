package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gsk.api.datarestrictionpojo.Datum;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;

@Service
public class GetAllianceandIDBSUsers {
	private GetAllianceandIDBSUsers() {
	}

	public static boolean disabledUser = true;

	public static Map<String, List<String>> getUsers(Datum allianceData) throws 
//	KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException, 
			ApiException {
		Map<String, List<String>> userDetails = new LinkedHashMap<>();
//		SecurityService securityService = new SecurityService();
		EWBService ewbService = new EWBService();
		List<String> users = new ArrayList<>();
		users.addAll(allianceData.getTeamMembers());
		users.addAll(allianceData.getApprovers());
		List<String> uniqueUsers = new ArrayList<>();
		List<String> allianceUsers = new ArrayList<>();
		List<String> idbsUsers = new ArrayList<>();
		for (String user : users) {
			if (!uniqueUsers.contains(user)) {
				uniqueUsers.add(user);
			}
		}
		for (String user : uniqueUsers) {
			if (ewbService.getUserFullName(user).isEmpty() || GetAllianceandIDBSUsers.disabledUser == true) {
				allianceUsers.add(user);
			} else {
				idbsUsers.add(user);
			}
		}
		userDetails.put("uniqueUserList", uniqueUsers);
		userDetails.put("allianceUserList", allianceUsers);
		userDetails.put("idbsUserList", idbsUsers);
		return userDetails;
	}
	
	public static Map<String, List<String>> getActiveUsers(com.gsk.api.datarestrictionpojo.Datum allianceData)
			throws 
//			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException,
			ApiException {
		Map<String, List<String>> userDetails = new LinkedHashMap<>();
		EWBService ewbService = new EWBService();
		List<String> users = new ArrayList<>();
		users.addAll(allianceData.getTeamMembers());
		// users.addAll(allianceData.getApprovers());
		List<String> uniqueUsers = new ArrayList<>();
		List<String> allianceUsers = new ArrayList<>();
		List<String> idbsUsers = new ArrayList<>();
		for (String user : users) {
			if (!uniqueUsers.contains(user)) {
				uniqueUsers.add(user);
			}
		}
		for (String user : uniqueUsers) {

			if (ewbService.getActiveUserFullName(user).isEmpty()) {
				allianceUsers.add(user);
			} else {
				idbsUsers.add(user);
			}
		}
		userDetails.put("uniqueUserList", uniqueUsers);
		userDetails.put("allianceUserList", allianceUsers);
		userDetails.put("idbsUserList", idbsUsers);
		return userDetails;
	}
}
