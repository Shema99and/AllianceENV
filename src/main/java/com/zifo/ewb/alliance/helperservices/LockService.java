package com.zifo.ewb.alliance.helperservices;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JsonConstantsUtils;
import com.zifo.ewb.alliance.utils.ServiceBase;

import lombok.NoArgsConstructor;

/**
 * The Lock Service provides access to the entity locking features. It allows an
 * entity to be locked/unlocked, to check if an entity is locked ,get details of
 * an existing lock
 * 
 * @author Zifo
 *
 */
@NoArgsConstructor
public class LockService extends ServiceBase {

	/**
	 * Logger
	 */
	public static final Logger logger = LoggerFactory.getLogger(LockService.class.getName());

	/**
	 * Gets information on a lock, e.g. entity id, entity name, entity locked at,
	 * entity path. The full list of retrieved information is defined in
	 * SAPIEntityLockDetails
	 * 
	 * 
	 * 
	 * @param termId
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String getActivitiesCount(final String entityId) throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.ENTITYLOCKSERVICE + entityId + "/find";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Checks whether or not the entity is currently locked. The actual status of
	 * the lock is defined in SAPIEntityLockQueryResponse and it can be UNLOCKED,
	 * RESOURCE_LOCKED, or RESOURCE_LOCKED_BY_USER.
	 * 
	 * 
	 * @param entityId
	 * @param includeChildren
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 */
	public String isLocked(final String entityId, final Boolean includeChildren) throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.ENTITYLOCKSERVICE + entityId + "/islocked";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("includeChildren", String.valueOf(includeChildren)).build();
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
	}

	/**
	 * Acquires the lock on an entity. Locking gives exclusive access to a resource
	 * and it may be necessary in order to perform actions on the entity, e.g.
	 * updating and committing
	 * 
	 * @param entityId
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 */
	public String lockRecord(final String entityId) throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.ENTITYLOCKSERVICE + entityId + "/lock";
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executePutRequest(getUriFactory().buildURI(url), null);
	}

	/**
	 * Releases the lock on an entity
	 * 
	 * @param entityId
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 */
	public String unlockRecord(final String entityId) throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.ENTITYLOCKSERVICE + entityId + "/lock";
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executeDeleteRequest(getUriFactory().buildURI(url));
	}

	public String getUserName(final String user) throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		String userName = StringUtils.EMPTY;
		final String url = getBaseServiceURL() + "/security/administration/users";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("userName", user.trim()).build();
		final String response = getRESTExecutor()
				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
		if (!response.equals(StringUtils.EMPTY)) {
			final JSONObject jsonobject = new JSONObject(response);
			if (jsonobject.getJSONArray("userlist").length() > 0) {
				if (!jsonobject.getJSONArray("userlist").getJSONObject(0).getBoolean("disabled")) {
					userName = jsonobject.getJSONArray("userlist").getJSONObject(0).get("userFullName").toString();
				}
			}
		}
		return userName;
	}
}