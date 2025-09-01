package com.zifo.ewb.alliance.allianceprocess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.serviceh.EWBService;

/**
 * This class is used to check whether the alliance is removed from the Alliance
 * API Details and remove those alliance
 * 
 * @author Zifo
 *
 */
public final class CheckAllianceRemovedHelper {
	private CheckAllianceRemovedHelper() {
	}

	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckAllianceRemovedHelper.class.getName());

	/**
	 * This method is used to remove the end user group of non existing Alliance
	 * 
	 * @param allDetails
	 * @return
	 * @throws ApiException 
	 */
	public static List<String> checkAlliance(final Map<String, String> catAllianceNames,
			final List<String> allAllianceName, final List<String> groupName, final String catalogId,
			final Map<String, String> terminatedAlliance) throws 
//	KeyManagementException, KeyStoreException,
//			NoSuchAlgorithmException, CertificateException, JAXBException, IOException, URISyntaxException,
			ApiException {
//		SecurityService sservice = new SecurityService();
		
		List<String> removedAlliance = new ArrayList<>();
		for (final Map.Entry<String, String> entry : catAllianceNames.entrySet()) {
			if (!allAllianceName.contains(entry.getKey()) && LOGGER.isInfoEnabled()) {
//				final CatalogService service = new CatalogService();
				EWBService ewbService = new EWBService();
//				LOGGER.info("Removing Alliance " + entry.getKey());
				LOGGER.info("Removing Alliance {}", entry.getKey());
				removedAlliance.add(entry.getKey());
				ewbService.deleteTuple(catalogId,entry.getValue());
				final String endUserGroupName = "G_GSK_Alliance-" + entry.getKey() + "_EndUser_Edit";
//				LOGGER.info("End User Group Name " + endUserGroupName);
				LOGGER.info("End User Group Name {}", endUserGroupName);
				if (groupName.contains(endUserGroupName)) {
					LOGGER.info("Performing Deleting operation");
					ewbService.deleteGroup(endUserGroupName);
				}
			}
		}
		for (final Map.Entry<String, String> entry : terminatedAlliance.entrySet()) {
			EWBService ewbService = new EWBService();
//			LOGGER.info("Removing Catalog Entry " + entry.getKey());
			LOGGER.info("Removing Catalog Entry {}", entry.getKey());
			ewbService.deleteTuple(catalogId,entry.getValue());
			removedAlliance.add(entry.getKey());
		}
		return removedAlliance;
	}
}
