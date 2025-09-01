package com.zifo.ewb.alliance.allianceprocesshelper;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;

/**
 * this class is used to check 
 * whether the given business area is present or not
 * @author Zifo
 *
 */
public final class CheckBusinessAreaExistenceHelper {
	private CheckBusinessAreaExistenceHelper() {
	}
	
	/**
	 * method to check the given business area existence
	 * @param businessAreaName
	 * @return
	 * @throws ApiException 
	 */
	public static String  checkBusinessArea(final String businessAreaName) throws 
//	KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException, 
	ApiException {
	    EWBService ewbService = new EWBService();
//		final String businAreaEntityId= ewbService.getBusinessAreaUnderRoot(businessAreaName);
		return ewbService.getBusinessAreaUnderRoot(businessAreaName);
	}
}

