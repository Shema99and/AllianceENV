package com.zifo.ewb.alliance.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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

/**
 * This class is used to maintain API's to get entity related operations
 * 
 * @author Zifo
 */
public class EntityService extends ServiceBase {
	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityService.class.getName());

	/**
	 * method to get all the business Area Under the root
	 * 
	 * @return
	 */
	public String getBusinessAreaUnderRoot() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, URISyntaxException {

		final String url = getBaseServiceURL() + "/entitytree/1";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put(AppConstantsUtils.INCLUDE_TYPE, AppConstantsUtils.BUSINESS_AREA).build();
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));

	}

	/**
	 * Method to create child entity
	 * 
	 * @param entityId
	 * @param inputJson
	 * @return
	 */
	public String createChildRequest(final String entityId, final String inputJson) throws URISyntaxException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		String response;
		final String url = getBaseServiceURL() + "/entities/" + entityId + "/children";

		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
		final HttpEntity entity = new StringEntity(inputJson, StandardCharsets.UTF_8);

		response = getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executePostRequest(getUriFactory().buildUriWithQueryParams(url, queryParams), entity);

		return response;
	}

	/**
	 * method to get the child entity details for the given entity id
	 * 
	 * @param entityId
	 * @return
	 */
	public String getChildEntity(final String entityId) throws URISyntaxException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		final String url = getBaseServiceURL() + "/entitytree/" + entityId;
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executeGetRequest(getUriFactory().buildUriWithQueryParams(url, queryParams));

	}

	/**
	 * method to get the child entity details for the given entity id
	 * 
	 * @param entityId
	 * @return
	 */
	public String lockExperiment(final String entityId) throws URISyntaxException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		final String url = getBaseServiceURL() + "/locks/entities/" + entityId + "/lock";
		HttpEntity entity = new StringEntity("");
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>().build();
		return getRESTExecutor(ServiceBase.getAuthorization(), ServiceBase.getAcceptJSON(), ServiceBase.getAcceptJSON())
				.executePutRequest(getUriFactory().buildUriWithQueryParams(url, queryParams), entity);

	}

	public String getData(final String modelId) throws URISyntaxException, KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		final String url = getBaseServiceURL() + "/spreadsheet/data";
		HttpEntity entity = new StringEntity(getJson("Table1"));
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("modelId", modelId).put("editMode", "true").build();
		return getRESTExecutor(ServiceBase.getAuthorization(), ServiceBase.getAcceptJSON(), ServiceBase.getAcceptJSON())
				.executePostRequest(getUriFactory().buildUriWithQueryParams(url, queryParams), entity);

	}

	private static String getJson(final String tableName) {

		return "{\r\n" + "    \"batch-request\":[\r\n" + "        {\"version\" :\"1.0\"},\r\n" + "        [\r\n"
				+ "            {\r\n" + "                \"api-id\":\"table.data\",\r\n"
				+ "                \"api-version\":\"1.0\"\r\n" + "                \r\n" + "            },\r\n"
				+ "            {\r\n" + "                \"data\":{\r\n" + "                    \"queries\":[\r\n"
				+ "                        {\r\n" + "                    \"table\":\"" + tableName + "\",\r\n"
				+ "                    \"range\":\"" + "" + "\"\r\n" + "                }\r\n" + "                ]\r\n"
				+ "        }}  ]\r\n" + "        ]\r\n" + "        \r\n" + "    \r\n" + "}";
	}

}
