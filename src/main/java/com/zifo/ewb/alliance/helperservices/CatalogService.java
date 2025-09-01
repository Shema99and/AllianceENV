package com.zifo.ewb.alliance.helperservices;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JsonConstantsUtils;
import com.zifo.ewb.alliance.utils.ServiceBase;

import lombok.NoArgsConstructor;

/**
 * The Catalog Service provides a way for external applications to interact with
 * the E-WorkBook Catalog; allowing them to query and make updates.
 * 
 * @author Zifo
 *
 */
@NoArgsConstructor
public class CatalogService extends ServiceBase {
	/**
	 * Logger
	 */
	public static final Logger logger = LoggerFactory.getLogger(CatalogService.class.getName());

	/**
	 * This method will give all the tuple details for the given catalogId
	 */
	public String callCatalogDetails(final String catalogId, final String basicAuth) throws URISyntaxException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		final String url = AppConstantsUtils.SERVICEBASE + "/catalog/" + catalogId + "/tuples";
		final ImmutableMap<String, String> queryParams = ImmutableMap.<String, String>builder().build();

//		final String response = getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
//				JsonConstantsUtils.CONTENT_TYPE_JSON)
//				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
		return  getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
	}

	/**
	 * @param entityId
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getCatalogRootElements(final String entityId, final String inputBody) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE;
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Creates a root level element.
	 * 
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String createRootElement(final String inputBody) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE;
		final StringEntity entity = new StringEntity(inputBody);
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildURI(url), entity);

	}

	/**
	 * Creates a child element.
	 * 
	 * @param dictionaryguid
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String createDictChild(final String dictionaryguid, final String inputBody) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + dictionaryguid
				+ "/children";
		StringEntity entity = new StringEntity(inputBody);
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildURI(url), entity);

	}

	/**
	 * Gets all children of the Element identified by its GUID.
	 * 
	 * @param dictionaryguid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getChildEntities(final String dictionaryguid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + dictionaryguid
				+ "/children";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Gets a element given its GUID.
	 * 
	 * @param guid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getGuidElement(final String guid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + guid;
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));

	}

	/**
	 * Updates a catalog element identified by its GUID.
	 * 
	 * @param guid
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String updateCatalogElement(final String guid, final String inputBody) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + guid;
		final StringEntity entity = new StringEntity(inputBody);
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildURI(url), entity);

	}

	/**
	 * Deletes a element (Dictionary or Term) given its GUID.
	 * 
	 * @param guid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String deleteElement(final String guid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + guid;
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeDeleteRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Deletes all children of the Element identified by its GUID.
	 * 
	 * @param guid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String deleteAllChildren(final String guid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + guid + "/children";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeDeleteRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Get the full Term Hierarchy that this Term is derived from
	 * 
	 * @param guid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getFullHierarchy(final String guid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + guid + "/hierarchy";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Creates a tuple in a term.
	 * 
	 * @param termguid
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String createTuple(final String termguid, final String inputBody) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + termguid
				+ "/tuples";
		final StringEntity entity = new StringEntity(inputBody);
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildURI(url), entity);

	}

	/**
	 * Gets all the tuples in the term.
	 * 
	 * @param termguid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getAllTuples(final String termguid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + termguid
				+ "/tuples";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Deletes all the tuples in the term.
	 * 
	 * @param termguid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String deleteAllTuples(final String termguid) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + termguid
				+ "/tuples";
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeDeleteRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Gets a tuple in the term.
	 * 
	 * @param termguid
	 * @param tupleguid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String GetATuple(final String termguid, final String tupleguid) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + termguid
				+ "/tuples/" + tupleguid;
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Updates a tuple in the term.
	 * 
	 * @param termguid
	 * @param tupleguid
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String createATuple(final String termguid, final String tupleguid, final String inputBody)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + termguid
				+ "/tuples/" + tupleguid;
		final StringEntity entity = new StringEntity(inputBody);
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildURI(url), entity);
	}

	/**
	 * Deletes a tuple in the term.
	 * 
	 * @param termguid
	 * @param tupleguid
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String deletATuple(final String termguid, final String tupleguid) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/" + termguid
				+ "/tuples/" + tupleguid;
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeDeleteRequest(getUriFactory().buildURI(url));
	}

	/**
	 * Retrieves (and consumes) the specified number of auto-id values from the
	 * sequence identified by the format string.
	 * 
	 * @param autoidformat
	 * @param count
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String retrieveAutoIds(final String autoidformat, final String count) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/autoids/next";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("autoidformat", autoidformat).put("count", count).build();
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildUriWithQueryParams(url, queryParams), null);
	}

	/**
	 * Finds terms or dictionaries based on a set of filter criteria. No permissions
	 * are necessary. The filter criteria are specified in terms of
	 * 
	 * a catalog element name - this is a string with wild cards specified by a '%'
	 * e.g. 'weight%', a set of predicates governing the term path e.g.
	 * '/experiment/data*, a set of flags describing catalog element characteristics
	 * e.g. only abstract terms or only dictionaries, constraints on the amount of
	 * data returned
	 * 
	 * @param inputBody
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String findElement(final String inputBody) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/elements";
		StringEntity entity = new StringEntity(inputBody);
		return getRESTExecutor(JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executePostRequest(getUriFactory().buildURI(url), entity);
	}

	/**
	 * Converts a path to its corresponding element's GUID.
	 * 
	 * @param path
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String getGuid(final String path) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CATALOGSERVICE + "/id";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().put("path", path)
				.build();
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
				.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));
	}

	/**
	 * this method will create a new tuple in catalog
	 */
	public String createTuple(final String identity, final String jsonformat, final String entityAuth)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException {
		final String url = getBaseServiceURL() + "/catalog/" + identity + "/tuples";
		final HttpEntity entity = new StringEntity(jsonformat);
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executePostRequest(getUriFactory().buildURI(url), entity);
	}

	/**
	 * this method will delete a specific tuple
	 */
	public void deleteTuples(final String guid, final String basicAuth, final String catalogID)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException {
		final String url = getBaseServiceURL() + "/catalog/" + catalogID + "/tuples/" + guid;
		getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON).executeDeleteRequest(getUriFactory().buildURI(url));
	}

}
