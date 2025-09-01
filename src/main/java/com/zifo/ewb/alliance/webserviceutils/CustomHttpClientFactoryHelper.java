package com.zifo.ewb.alliance.webserviceutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.constants.ServiceConstants;
import com.zifo.ewb.alliance.controller.Alliance;

/**
 * HttpClient class with custom SSL
 */
public final class CustomHttpClientFactoryHelper {

	/**
	 * It logs the logs related to this class
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomHttpClientFactoryHelper.class.getName());

//	private static final String[] CRYPTOGRAPH_PROTO = new String[] { "TLSv1" };

	/**
	 * Private constructor to prevent object creation for utility class.
	 */
	private CustomHttpClientFactoryHelper() {
	}

	/**
	 * Method to get relative path of trust store file
	 * 
	 * @param relToClsLoadPath
	 * @return
	 * @throws UnsupportedEncodingException
	 */
//	public static String getTrustStAbsPath(final String relToClsLoadPath) throws UnsupportedEncodingException {
//
//		final String absolutePath = relToClsLoadPath;
//		return URLDecoder.decode(absolutePath, System.getProperty("file.encoding"));
//	}

	public static String getTrustStoreAbsolutePath(final String relToCLPath) throws UnsupportedEncodingException {
		final String absolutePath = Thread.currentThread().getContextClassLoader().getResource(relToCLPath).getPath();
		return URLDecoder.decode(absolutePath, System.getProperty(ServiceConstants.FILE_ENCODING));
	}

	/**
	 * Create secure connection with a custom SSL
	 * 
	 * @return HttpClient for REST processing, or null if there is a problem
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */

	@SuppressWarnings("deprecation")
	public static CloseableHttpClient getHttpClient(String caserts) throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, KeyManagementException {
//		final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//		trustStore.load(new FileInputStream(caserts), null);
//		final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
//				.build();
//		LOGGER.info(System.getProperty("https.protocols"));
//		LOGGER.info(System.getProperty("https.cipherSuites"));
//		final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
//				split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")),
//				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		SSLContextBuilder SSLBuilder = SSLContexts.custom();
		final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(new FileInputStream(caserts), null);
		SSLBuilder = SSLBuilder.loadTrustMaterial(trustStore, new TrustStrategy() {

			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				Boolean certificateValid = true;
				for (int i = 0; i < chain.length; i++) {
					try {
						chain[i].checkValidity();
					} catch (CertificateExpiredException ex) {
						certificateValid = false;
					}
				}
				// TODO Auto-generated method stub
				return certificateValid;
			}
		});
		SSLContext sslContext = SSLBuilder.build();
//		Broker broker=new Broker();
//		new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2", "TLSv1.3" }, null,
//				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		String[] tls = Alliance.TLS_Version.split(",");
		SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslContext, tls, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		HttpClientBuilder clientbuilder = HttpClients.custom();
		clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);
//		CloseableHttpClient httpclient = clientbuilder.build();
		return clientbuilder.build();
	}

//	private static String[] split(final String text) {
//		if (TextUtils.isBlank(text)) {
//			return null;
//		}
//		return text.split(" *, *");
//	}

}

//public static CloseableHttpClient getHttpClient() throws KeyStoreException, NoSuchAlgorithmException,
//CertificateException, IOException, KeyManagementException {
//CloseableHttpClient httpclient = null;
//final SSLContext sslContext = SSLContext.getInstance("SSL");
//// set up a TrustManager that trusts everything
//sslContext.init(null, new TrustManager[] { new X509TrustManager() {
//@Override
//public X509Certificate[] getAcceptedIssuers() {
//	return new X509Certificate[0];
//}
//
//@Override
//public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
//
//}
//
//@Override
//public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
//
//}
//} }, new SecureRandom());
//httpclient = HttpClients.custom().setSslcontext(sslContext).setHostnameVerifier(new AllowAllHostnameVerifier())
//	.build();
//return httpclient;
//}
//
//@SuppressWarnings("deprecation")
//public static CloseableHttpClient getHttpClient(final String caserts) throws KeyStoreException,
//NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException {
//final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//trustStore.load(new FileInputStream(caserts), null);// NOPMD
//final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
//	.build();
//final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
//	split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")),
//	SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//return HttpClients.custom().setSSLSocketFactory(sslsf).build();
//}
