package com.zifo.ewb.alliance.webserviceutils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.utils.ServiceBase;

/**
 * Utility class for common REST actions
 */
public class RESTExecutor {

	/**
	 * It logs the logs related to this class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTExecutor.class.getName());

	/**
	 * It is the authorization header
	 */
	private final transient String authHeader;

	/**
	 * It is the contentType Header of the API call
	 */
	private final transient String contentTyHdFormat;

	/**
	 * It is the acceptType Header of the API call
	 */
	private final transient String acceptHdFormat;
	public static String statusCode;

	/**
	 * Create RESTExecutor that uses specific credentials
	 * 
	 * @param authHeaderString  Base64 encoded credentials
	 * @param contentTyHdFormat HTTP Content-Type format
	 * @param acceptHdFormat    HTTP Accept format
	 */
	public RESTExecutor(final String authHeaderString, final String acceptHdFormat, final String contentTyHdFormat) {
		this.authHeader = authHeaderString;
		this.acceptHdFormat = acceptHdFormat;
		this.contentTyHdFormat = contentTyHdFormat;
	}

	/**
	 * Executor for HTTP GET Requests
	 * 
	 * @param uri the request endpoint
	 * @return response body as string
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String executeGetRequest(final URI uri) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		final HttpGet request = new HttpGet(uri);

		return executeRequest(request);
	}

	/**
	 * Constructs and sends the REST request
	 * 
	 * @param request the HTTP request
	 * @return the response string
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	private String executeRequest(final HttpRequestBase request) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {

		request.addHeader("Accept", acceptHdFormat);
		request.addHeader("Content-Type", contentTyHdFormat);
		request.addHeader("Authorization", authHeader);
		request.addHeader("X-Web-Client-Author-Credentials", authHeader);

		String responseString = null;
		String cacerts = ServiceBase.getCacertsLocation();

		try (CloseableHttpResponse httpResponse = CustomHttpClientFactoryHelper.getHttpClient(cacerts)
				.execute(request)) {
			if (httpResponse.getEntity() == null || httpResponse.getStatusLine().getStatusCode() == 400) {
				responseString = StringUtils.EMPTY;
			} else {
				responseString = EntityUtils.toString(httpResponse.getEntity());
			}
			statusCode = String.valueOf(httpResponse.getStatusLine().getStatusCode());
			LOGGER.debug(statusCode);
			return responseString;
		}
	}

//		final CloseableHttpClient httpClient = CustomHttpClientFactoryHelper.getHttpClient(cacerts);
//		final CloseableHttpResponse httpResponse = httpClient.execute(request);
//		final HttpEntity httpEntity = httpResponse.getEntity();
//		final int statusCode = httpResponse.getStatusLine().getStatusCode();
//		try {
//			if (httpEntity == null || statusCode == 400) {
//				responseString = StringUtils.EMPTY;
//			} else {
//				responseString = EntityUtils.toString(httpEntity);
//				LOGGER.info(responseString);
//			}
//
//			return responseString;

//// commented
//		final CloseableHttpResponse httpResponse = CustomHttpClientFactoryHelper.getHttpClient(cacerts).execute(request);
//		if (httpResponse.getEntity() == null
//				|| httpResponse.getStatusLine().getStatusCode() == 400) {
//			responseString = StringUtils.EMPTY;
//		} else {
//			responseString = EntityUtils.toString(httpResponse.getEntity());
//		}
//		statusCode=String.valueOf(httpResponse.getStatusLine().getStatusCode());
//		LOGGER.debug(statusCode);
//		return responseString;
//		} 

//	finally {
//			httpClient.close();
//			httpResponse.close();
//		}
//	}

	/**
	 * Constructs and sends the REST request
	 * 
	 * @param request the HTTP request
	 * @return the response string
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	private String executeModelRequest(final HttpRequestBase request) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {

		request.addHeader("Accept", acceptHdFormat);
		request.addHeader("Content-Type", contentTyHdFormat);
		request.addHeader("Authorization", authHeader);
		request.addHeader("X-Web-Client-Author-Credentials", authHeader);

		String responseString = null;

		final CloseableHttpClient httpClient = CustomHttpClientFactoryHelper.getHttpClient(responseString);
		final CloseableHttpResponse httpResponse = httpClient.execute(request);

		final HttpEntity httpEntity = httpResponse.getEntity();

		final int statusCode = httpResponse.getStatusLine().getStatusCode();
		try {
			if (httpEntity == null || statusCode == 400) {
				responseString = StringUtils.EMPTY;
			} else {
				responseString = EntityUtils.toString(httpEntity);
			}
			return responseString;
		} finally {
			httpClient.close();
			httpResponse.close();
		}
	}

	/**
	 * Constructs and sends the REST request
	 * 
	 * @param request the HTTP request
	 * @return the response string
	 * @throws CertificateException,NoSuchAlgorithmException
	 * @throws KeyStoreException,KeyManagementException
	 */
	public String executeTokenRequest(final HttpRequestBase request)
			throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {

		String responseString = StringUtils.EMPTY;
		try {
			final CloseableHttpResponse httpResponse = CustomHttpClientFactoryHelper.getHttpClient(responseString)
					.execute(request);
			if (LOGGER.isDebugEnabled()) {
//				LOGGER.debug("Status Code ==>" + httpResponse.getStatusLine().getStatusCode());
				LOGGER.debug("Status Code ==>{}", httpResponse.getStatusLine().getStatusCode());
			}
			if (httpResponse.getEntity() != null) {
				responseString = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (IOException ioException) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Problem executing request:" + responseString + "\n" + ioException.getMessage(),
						ioException);
			}
		}

		return responseString;
	}

	private String executeDeleteModelRequest(final HttpRequestBase request) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		request.addHeader("Accept", "text/plain");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("Authorization", authHeader);
		request.addHeader("X-Web-Client-Author-Credentials", authHeader);

		String responseString = null;
		String cacerts = ServiceBase.getCacertsLocation();
		final CloseableHttpClient httpClient = CustomHttpClientFactoryHelper.getHttpClient(cacerts);
		final CloseableHttpResponse httpResponse = httpClient.execute(request);
		final HttpEntity httpEntity = httpResponse.getEntity();
		final int statusCode = httpResponse.getStatusLine().getStatusCode();
		try {
			if (httpEntity == null || statusCode == 400) {
				responseString = StringUtils.EMPTY;
			} else {
				responseString = EntityUtils.toString(httpEntity);
			}

			return responseString;

		} finally {
			httpClient.close();
			httpResponse.close();
		}
	}

	/**
	 * Executor for HTTP GET Requests
	 * 
	 * @param uri the request endpoint
	 * @return response body as string
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public HttpEntity executeGetRequestOfBinaryData(final URI uri) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		final HttpGet request = new HttpGet(uri);

		return executeRequestBinaryContent(request);
	}

	/**
	 * Constructs and sends the REST request
	 * 
	 * @param request the HTTP request
	 * @return the response string
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	private HttpEntity executeRequestBinaryContent(final HttpRequestBase request) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		request.addHeader("Accept", acceptHdFormat);
		request.addHeader("Content-Type", contentTyHdFormat);
		request.addHeader("Authorization", authHeader);
		request.addHeader("X-Web-Client-Author-Credentials", authHeader);

		final CloseableHttpClient httpClient = CustomHttpClientFactoryHelper.getHttpClient(acceptHdFormat);
		final CloseableHttpResponse httpResponse = httpClient.execute(request);
		final int statusCode = httpResponse.getStatusLine().getStatusCode();

		HttpEntity httpEntity = null;
		try {
			if (statusCode == AppConstantsUtils.HTTP_200_STATUS) {
				httpEntity = httpResponse.getEntity();
			} else {
				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("error response from webservice for URI: " + request.getURI());
					LOGGER.info("error response from webservice for URI: {}", request.getURI());
				}
			}

			return httpEntity;
		} finally {
			httpClient.close();
			httpResponse.close();
		}
	}

	/**
	 * Executor for HTTP POST Requests
	 * 
	 * @param uri    the request endpoint
	 * @param entity the request body
	 * @return response body as string
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public String executePostRequest(final URI uri, final HttpEntity entity) throws
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("POST ==>" + uri.toASCIIString());
			LOGGER.info("POST ==>{}", uri.toASCIIString());
		}
		final HttpPost request = new HttpPost(uri);

		if (entity != null) {
			request.setEntity(entity);
		}

		return executeRequest(request);

	}

	public String executePostModelRequest(final URI uri, final HttpEntity entity) throws
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("POST ==>" + uri.toASCIIString());
			LOGGER.info("POST ==>{}", uri.toASCIIString());
		}
		final HttpPost request = new HttpPost(uri);

		if (entity != null) {
			request.setEntity(entity);
		}

		return executeModelRequest(request);

	}

	/**
	 * Executor for HTTP PUT Requests
	 * 
	 * @param uri    the request endpoint
	 * @param entity the request body
	 * @return response body as string
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws URISyntaxException
	 */
	public String executePutRequest(final URI uri, final HttpEntity entity) throws KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("PUT ==>" + uri.toASCIIString());
			LOGGER.info("PUT ==>{}", uri.toASCIIString());
		}
		final HttpPut request = new HttpPut(uri);

		if (entity != null) {
			request.setEntity(entity);
		}

		return executeRequest(request);
	}

	/**
	 * Executor for HTTP DELETE Requests
	 * 
	 * @param uri the request endpoint
	 * @return response body as string
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws URISyntaxException
	 */
	public String executeDeleteRequest(final URI uri) throws KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("DELETE ==>" + uri.toASCIIString());
			LOGGER.info("DELETE ==>{}", uri.toASCIIString());
		}
		final HttpDelete request = new HttpDelete(uri);

		return executeDeleteModelRequest(request);
	}

}
