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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsk.api.datarestrictionpojo.Datum;
import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.catalogpojo.CatalogDetailPojo;
import com.zifo.ewb.catalogpojo.Tuple;

/**
 * This class is used to check whether the alliance is present or not in the
 * catalog
 * 
 * @author Zifo
 *
 */
public final class CheckAllianceExistanceHelper {

	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckAllianceExistanceHelper.class.getName());

	/**
	 * if the alliance is exist in the catalog it would send the alliance details to
	 * compare the alliance details with catalog details
	 * 
	 * @param terminatedAlliance
	 * @throws ApiException 
	 */
	public String checkExistance(final Datum allianceData, final CatalogDetailPojo catalogDetail,
			final String catalogId, final List<String> groupName, Map<String, String> terminatedAlliance)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			JAXBException, IOException, URISyntaxException, ApiException {
		String response = StringUtils.EMPTY;
		Map<String, List<String>> userDetails = GetAllianceandIDBSUsers.getUsers(allianceData);
		outer: for (final Tuple tuple : catalogDetail.getTuple()) {
			AddNewMembersHelper.activeUserList.clear();
			AddNewMembersHelper.addedUserList.clear();
			AddNewMembersHelper.removedUserList.clear();
			response = StringUtils.EMPTY;
			for (final com.zifo.ewb.catalogpojo.Datum catalogData : tuple.getData()) {
				if ("Alliance Name".equals(catalogData.getName())) {
					// if (allianceData.getAgreement().getName().equals(catalogData.getValue())) {
					if (allianceData.getName().equals(catalogData.getValue())) {
						if (!allianceData.getStatus().equals("Terminated")) {
//							response = CompareAllianceDetails.compareDetails(allianceData, tuple, catalogId, groupName,
//									userDetails);
							response = "Alliance Exists";
							break outer;
						} else {
							terminatedAlliance.put(allianceData.getName(), tuple.getId());
//							List<String> delUserList = userDetails.get("idbsUserList");
							response = "Alliance Exists";
							break outer;
						}
					}
				}
			}
		}
		if (response.isEmpty()) {
			response = CreateNewTupleHelper.createTuple(allianceData, catalogId, userDetails);
		}
		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("Tuple Response " + response);
			LOGGER.info("Tuple Response: {}", response);
		}
		return response;
	}

}
