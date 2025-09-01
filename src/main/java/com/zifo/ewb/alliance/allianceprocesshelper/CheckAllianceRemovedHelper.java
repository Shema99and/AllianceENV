package com.zifo.ewb.alliance.allianceprocesshelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.services.CatalogService;
import com.zifo.ewb.alliance.services.SecurityService;
import com.zifo.ewb.alliance.utils.ServiceBase;



/**
 * This class is used to check whether the alliance
 * is removed from the Alliance API Details and remove those alliance
 * @author Zifo
 *
 */
public final  class CheckAllianceRemovedHelper {
	
	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckAllianceRemovedHelper.class.getName());
	/**
	 * This method is used to remove the end user group
	 * of non existing Alliance 
	 */
	public  void checkAlliance(final Map<String, String> catAllianceNames, final List<String> allAllianceName, final List<String> groupName, final String catalogId) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		
		SecurityService securityService=new SecurityService();
		for(final Map.Entry<String,String> entry:catAllianceNames.entrySet()) {
			if(!allAllianceName.contains(entry.getKey()) && LOGGER.isInfoEnabled()) {
				final CatalogService service = new CatalogService();
//				LOGGER.info("Removing Alliance "+entry.getKey());
				LOGGER.info("Removing Alliance {}", entry.getKey());
				service.deleteTuples(entry.getValue(), ServiceBase.getEntityAuth(), catalogId);
				final String endUserGroupName = "G_GSK_Alliance-"+entry.getKey()+"_EndUser_Edit";
				LOGGER.info("End User Group Name "+endUserGroupName);
				if(groupName.contains(endUserGroupName)) {
				LOGGER.info("Performing Deleting operation");
				securityService.deleteGroup(endUserGroupName);
				}
			}
		}
	}
	private CheckAllianceRemovedHelper() {
		
	}
}

