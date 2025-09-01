package com.zifo.ewb.alliance.allianceprocesshelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsk.api.datarestrictionpojo.InputPojo;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.catalogpojo.CatalogDetailPojo;

/**
 * Process the each alliance details
 * 
 * @author Zifo
 */
@Service
public final class ProcessAllianceDetailsHelper {
	
	@Autowired
	CheckAllianceExistanceHelper check;
	/**
	 * Field logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessAllianceDetailsHelper.class.getName());

	/**
	 * This method send the each details of alliance to process it
	 * 
	 * @param catalogDetail @param allianceDetails
	 * @param catalogId     @param groupName
	 * @param terminatedAlliance 
	 * @throws ApiException 
	 */
	public void processDetails(final CatalogDetailPojo catalogDetail, final InputPojo allianceDetails,
			final String catalogId, final List<String> groupName, Map<String, String> terminatedAlliance) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, JAXBException, IOException, URISyntaxException, ApiException {
		for (final com.gsk.api.datarestrictionpojo.Datum data : allianceDetails.getData()) {
			if (LOGGER.isInfoEnabled()) {
//				LOGGER.info("Process Alliance Name :" + data.getName());
				LOGGER.info("Process Alliance Name: {}", data.getName());
			}
			check.checkExistance(data, catalogDetail, catalogId, groupName,terminatedAlliance);
		}
	}

}
