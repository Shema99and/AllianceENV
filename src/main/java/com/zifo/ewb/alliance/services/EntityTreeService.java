package com.zifo.ewb.alliance.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JsonConstantsUtils;
import com.zifo.ewb.alliance.utils.ServiceBase;

import lombok.NoArgsConstructor;

/**
 * The Entity Tree Service is responsible for managing entity tree related
 * requests.
 * 
 * @author Zifo
 *
 */
@NoArgsConstructor
public class EntityTreeService extends ServiceBase {
	/**
	 * Logger
	 */
	public static final Logger logger = LoggerFactory.getLogger(EntityTreeService.class.getName());

	/**
	 * 
	 * Returns the list of root entities. By default, user root entities are: 'Root'
	 * and 'My Work'. Optional parameters can be specified to limit the scope of the
	 * results - see main interface documentation above for an explanation of how
	 * these are used. Note that excludeChild is unlikely to be of much use here and
	 * can safely be ignored. If you need to retrieve a list of potential template
	 * entities to present to the user see:
	 * RestRecordServiceApi#getTemplateTreeRoots(String, String, String[], String[])
	 * 
	 * @param includeType
	 * @param excludeChild
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String RootChidEntities(final String includeType, final String excludeChild) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + "/entitytree";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("includeType", includeType).put("excludeChild", excludeChild).build();
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
	}

	/**
	 * Returns the list of child entities for a given entity. Optional parameters
	 * can be specified to limit the scope of the results - see main interface
	 * documentation above for an explanation of how these are used. Note that
	 * excludeChild is unlikely to be of much use here and can safely be ignored. If
	 * you need to retrieve a list of potential template entities to present to the
	 * user see: RestRecordServiceApi#getTemplateTreeChildren(String, String,
	 * String, String[], String[])
	 * 
	 * 
	 * @param entityId
	 * @param includeType
	 * @param excludeChild
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getChildEntites(final String entityId) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + "/entitytree/" + entityId;
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
	}
}
