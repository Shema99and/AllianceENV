package com.zifo.ewb.alliance.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.xml.bind.JAXBException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JsonConstantsUtils;
import com.zifo.ewb.alliance.utils.ServiceBase;

import lombok.NoArgsConstructor;

/**
 * The Entity Version Service is responsible for managing versions of an entity,
 * including restoration and retrieval
 */
@NoArgsConstructor
public class EntityVersionService extends ServiceBase {
	/**
	 * Logger
	 */
	public static final Logger logger = LoggerFactory.getLogger(EntityVersionService.class.getName());

	/**
	 * Gets the latest version for a given entity
	 * 
	 * @param entityId
	 * @return
	 * @throws JAXBException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String getLatestVersion(final String entityId) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String versionInfo = getVersionDetails(entityId);
		return new JSONObject(versionInfo).getJSONArray("version").getJSONObject(0).getString("versionId");

	}

	/**
	 * Returns a list of versions for a given entity
	 * 
	 * @param entityId
	 * @return List of versions, or null if there are any problems
	 * @throws URISyntaxException
	 * @throws JAXBException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String getVersionDetails(final String entityId) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + "/entities/" + entityId + "/versions";
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executeGetRequest(getUriFactory().buildURI(url));

	}

	/**
	 * Verifies that record or record documents can be restored.
	 * 
	 * @param entityId
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String isRestorable(final String entityId, final String entityVersionId, final String childEntityId)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + "/entities/" + entityId + "/versions/restorable";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));

	}
}
