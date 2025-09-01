package com.zifo.ewb.alliance.services;

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
 * This service exposes methods for managing Spreadsheet data
 * 
 * @author Zifo
 *
 */
@NoArgsConstructor
public class SpreadsheetService extends ServiceBase {
	/**
	 * Logger
	 */
	public static final Logger logger = LoggerFactory.getLogger(SpreadsheetService.class.getName());

	/**
	 * Loading a spreadsheet
	 * 
	 * @param versionId
	 * @param editMode
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String loadSpreadsheet(final String versionId, final Boolean editMode) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + "/spreadsheet";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("versionId", versionId).put("editMode", Boolean.toString(editMode)).build();
		return getRESTExecutor("Basic " + EntityService.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executePutRequest(getUriFactory().buildUriWithQueryParamsForPool(url, queryParams), null);
	}

	/**
	 * Saving a spreadsheet
	 * 
	 * @param modelId
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String saveSpreadsheet(final String modelId) throws IOException, URISyntaxException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + "/spreadsheet/save";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("modelId", modelId).build();
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executePostRequest(getUriFactory().buildUriWithQueryParamsForPool(url, queryParams), null);
	}

	/**
	 * Unloading a spreadsheet
	 * 
	 * @param modelId
	 * @return
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String unloadSpreadsheet(final String modelId) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
		final String url = AppConstantsUtils.SERVICEBASE + "/spreadsheet";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("modelId", modelId).build();
		return getRESTExecutor().executeDeleteRequest(getUriFactory().buildUriWithQueryParamsForPool(url, queryParams));
	}

	/**
	 * To get spreadsheet data using ModelId
	 * 
	 * @param modelId
	 * @param inputJson
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String loadedSpreadsheet(String modelId, String inputJson) throws IOException, URISyntaxException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + "/spreadsheet/data";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("modelId", modelId).build();
		final HttpEntity entity = new StringEntity(inputJson, "UTF-8");
		return getRESTExecutor("Basic " + ServiceBase.getEntityAuth(), JsonConstantsUtils.ACCEPT,
				JsonConstantsUtils.CONTENT_TYPE_JSON)
						.executePostRequest(getUriFactory().buildUriWithQueryParamsForPool(url, queryParams), entity);
	}

	/**
	 * To get spreadsheet data using Version Id
	 * 
	 * @param versionId
	 * @param inputJson
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String spreadsheet(String versionId, String inputJson, String accessToken)
			throws IOException, URISyntaxException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException {
		final String url = AppConstantsUtils.SERVICEBASE + "/spreadsheet/data";
		final ImmutableMap<String, String> queryParams = new ImmutableMap.Builder<String, String>()
				.put("versionId", versionId).build();
		final HttpEntity entity = new StringEntity(inputJson, "UTF-8");
		return getRESTExecutor("Bearer " + accessToken, ServiceBase.getAcceptJSON(), ServiceBase.getAcceptJSON())
				.executePostRequest(getUriFactory().buildUriWithQueryParamsForPool(url, queryParams), entity);
	}

	public String createJSONtoReadTableData(String tableName) {
		return "{\r\n" + "    \"batch-request\": [\r\n" + "        {\r\n" + "            \"version\": \"1.0\"\r\n"
				+ "        },\r\n" + "        [\r\n" + "            {\r\n"
				+ "                \"api-id\": \"table.data\",\r\n" + "                \"api-version\": \"1.0\"\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"data\": {\r\n"
				+ "                    \"queries\": [\r\n" + "                        {\r\n"
				+ "                            \"table\": \"" + tableName + "\",\r\n"
				+ "                            \"range\": \"\"\r\n" + "                        }\r\n"
				+ "                    ]\r\n" + "                }\r\n" + "            }\r\n" + "        ]\r\n"
				+ "    ]\r\n" + "}";
	}

	public void callDraftSave(String exptEntityId) throws URISyntaxException, KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		final String url = AppConstantsUtils.SERVICEBASE + AppConstantsUtils.CACHEENTITIES + exptEntityId
				+ AppConstantsUtils.COMMIT;
		final ImmutableMap<String, String> queryParams = ImmutableMap.<String, String>builder().build();
		String body = "{" + "\"" + "author" + "\"" + ':' + "{" + "\"" + "reason" + "\"" + ':' + "\"" + "..." + "\""
				+ ',' + "\"" + "additionalComment" + "\"" + ':' + "\"" + "..." + "\"" + "}," + "\"" + "witness" + "\""
				+ ':' + "{" + "\"" + "reason" + "\"" + ':' + "\"" + "..." + "\"" + ',' + "\"" + "additionalComment"
				+ "\"" + ':' + "\"" + "..." + "\"" + "}," + "\"" + "entityId" + "\"" + ':' + "\"" + exptEntityId + "\""
				+ ',' + "\"" + "entityVersionType" + "\"" + ':' + "\"" + "DRAFT" + "\"" + "}";
		final HttpEntity entity = new StringEntity(body);
		getRESTExecutor().executePostRequest(getUriFactory().buildUriWithQueryParamsForPool(url, queryParams), entity);

	}
}
