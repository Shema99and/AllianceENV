package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.List;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;


/**
 * This class is used to get 
 * USERS under the business area
 * @author Zifo 
 */
public final class GetUsersUnderBusinessAreaHelper {
	/**
	 * method to get the Users
	 * @param entityId
	 * @return
	 * @throws ApiException 
	 */
	public static List<String> getUsers(final String entityId) throws 
//	KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, URISyntaxException, IOException, 
	ApiException{
		EWBService ewbService = new EWBService();
//		final List<String> userList = ewbService.getBusinessAreaUserList(entityId);
		return ewbService.getBusinessAreaUserList(entityId);
	}
	private GetUsersUnderBusinessAreaHelper() {
		
	}
}
