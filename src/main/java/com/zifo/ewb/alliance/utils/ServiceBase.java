package com.zifo.ewb.alliance.utils;

import java.util.ResourceBundle;

import com.zifo.ewb.alliance.constants.JsonConstantsUtils;
import com.zifo.ewb.alliance.webserviceutils.RESTExecutor;
import com.zifo.ewb.alliance.webserviceutils.UriFactory;

/**
 * common service features
 */
public class ServiceBase {

	/**
	 * It is the scheme of the service, present in the url which is used to hit the
	 * service.
	 */
	private static String scheme;

	/**
	 * It is the host name of the service, present in the url which is used to hit
	 * the service.
	 */
	private static String host;

	/**
	 * It is the port name of the host in which the service is present.
	 */
	private static int port;

	/**
	 * It is the user name of the account used to access the E-WorkBook
	 */
	private static String username;

	/**
	 * It is the password of the account used to access the E-WorkBook
	 */
	private static String password;

	/**
	 * It is the authentication Header.
	 */
	private static String authHeader;

	private static String sslLocation;

	/**
	 * It is the base64 encoded SSL password.
	 */
	private static String sslPassword;

	private static String connectionType;

	private static String proxyHost;

	private static String proxyPort;

	private static String proxyScheme;
	private static String isThroughProxy;
	private static String proxyUserName;
	private static String proxyPassWord;
	private static String entityAuth;
	/**
	 * consumer details
	 */
	private static String accessTokenbaseurl;

	private static String consumerClientId;

	private static String consumerClientSecret;

	private static String consumerRedirectUrl;

	private static String consumerGrantType;

	private static String cacertsLocation;

	public ServiceBase() {
		super();
	}

	// private Properties properties;

	/**
	 * Helper method for issuing RESTful requests Provides request header properties
	 * 
	 * @return
	 */
	public RESTExecutor getRESTExecutor() {
		return new RESTExecutor(authHeader, JsonConstantsUtils.ACCEPT, JsonConstantsUtils.CONTENT_TYPE_JSON);
	}

	public RESTExecutor getRESTExecutor(final String a, final String b) {
		return new RESTExecutor(authHeader, a, b);
	}

	public static RESTExecutor getRESTExecutor(final String authheader, final String accept, final String contenttype) {
		return new RESTExecutor(authHeader, accept, contenttype);
	}

	/**
	 * Helper method for building URIs. Provides base url properties
	 * 
	 * @return
	 */
	public static UriFactory getUriFactory() {
		return new UriFactory(scheme, host, port);
	}

	public static String getConnectionType() {
		return connectionType;
	}

	public static String getEntityAuth() {
		return entityAuth;
	}

	/**
	 * redirect URI
	 */
	private static String redirectURI;

	/**
	 * client ID
	 */
	private static String clientID;

	private static String grantType;

	private static String tokenRequestURL;

	private static String contentTypeForm;

	private static String acceptJSON;

	private static String authorization;

	private static String servicebase;

	private static String baseServiceURL;

	private static String entityId;

	private static String allianceUrl;

	private static String allianceAuth;
	
	private static String url;
	
	private static String from;
	
	private static String to;
	
	private static String subject;
	
	private static String mailUrl;
	
	private static String apiKey;
	
	private static String env;

	

	/**
	 * Load the service configuration details
	 * 
	 * @param environment
	 * @throws NumberFormatException
	 * @throws ValueNotFoundException
	 */
	public static void loadProperties() {

		ConfigResources obj = new ConfigResources();
		obj.load();
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(obj.getActiveconfig());
		scheme = resourceBundle.getString("scheme");
		host = resourceBundle.getString("host");
		port = Integer.valueOf(resourceBundle.getString("port"));
		baseServiceURL = resourceBundle.getString("servicebase");
		authorization = resourceBundle.getString("authorization");
		entityAuth = resourceBundle.getString("entity_auth");
		cacertsLocation = resourceBundle.getString("casertslocation");
		entityId = resourceBundle.getString("entityId");
		allianceUrl = resourceBundle.getString("allianceUrl");
		allianceAuth = resourceBundle.getString("allianceAuth");
		url = resourceBundle.getString("url");
		from = resourceBundle.getString("from");
		to = resourceBundle.getString("to");
		subject = resourceBundle.getString("subject");
		mailUrl = resourceBundle.getString("mailurl");
		apiKey = resourceBundle.getString("apikey");
		authHeader = "Basic " + authorization;
		env = resourceBundle.getString("env");
	}

	/**
	 * @return the tokenRequestURL
	 */
	public static String getTokenRequestURL() {
		return tokenRequestURL;
	}

	/**
	 * 
	 * @return allianceUrl
	 */
	public static String getAllianceUrl() {
		return allianceUrl;
	}

	/**
	 * 
	 * @return allianceAuth
	 */
	public static String getAllianceAuth() {
		return allianceAuth;
	}

	/**
	 * @return the entityId
	 */
	public static String getEntityId() {
		return entityId;
	}

	public static String getBaseServiceURL() {
		return baseServiceURL;
	}

	/**
	 * @param tokenRequestURL the tokenRequestURL to set
	 */
	public static void setTokenRequestURL(final String tokenRequestURL) {
		ServiceBase.tokenRequestURL = tokenRequestURL;
	}

	/**
	 * @return the grantType
	 */
	public static String getGrantType() {
		return grantType;
	}

	/**
	 * @param grantType the grantType to set
	 */
	public static void setGrantType(final String grantType) {
		ServiceBase.grantType = grantType;
	}

	/**
	 * @return the redirectURI
	 */
	public static String getRedirectURI() {
		return redirectURI;
	}

	/**
	 * @return the acceptJSON
	 */
	public static String getAcceptJSON() {
		return acceptJSON;
	}

	/**
	 * @param acceptJSON the acceptJSON to set
	 */
	public static void setAcceptJSON(final String acceptJSON) {
		ServiceBase.acceptJSON = acceptJSON;
	}

	/**
	 * @return the authorization
	 */
	public static String getAuthorization() {
		return authorization;
	}

	/**
	 * @return the scheme
	 */
	public static String getScheme() {
		return scheme;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public static void setScheme(final String scheme) {
		ServiceBase.scheme = scheme;
	}

	/**
	 * @return the port
	 */
	public static Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public static void setPort(final Integer port) {
		ServiceBase.port = port;
	}

	/**
	 * @return the servicebase
	 */
	public static String getServicebase() {
		return servicebase;
	}

	/**
	 * @param servicebase the servicebase to set
	 */
	public static void setServicebase(final String servicebase) {
		ServiceBase.servicebase = servicebase;
	}

	/**
	 * @param authorization the authorization to set
	 */
	public static void setAuthorization(final String authorization) {
		ServiceBase.authorization = authorization;
	}

	/**
	 * @return the contentTypeForm
	 */
	public static String getContentTypeForm() {
		return contentTypeForm;
	}

	/**
	 * @param contentTypeForm the contentTypeForm to set
	 */
	public static void setContentTypeForm(final String contentTypeForm) {
		ServiceBase.contentTypeForm = contentTypeForm;
	}

	/**
	 * @param redirectURI the redirectURI to set
	 */
	public static void setRedirectURI(final String redirectURI) {
		ServiceBase.redirectURI = redirectURI;
	}

	/**
	 * @return the clientID
	 */
	public static String getClientID() {
		return clientID;
	}

	/**
	 * @param clientID the clientID to set
	 */
	public static void setClientID(final String clientID) {
		ServiceBase.clientID = clientID;
	}

	/**
	 * @return the sslLocation
	 */
	public static String getSslLocation() {
		return sslLocation;
	}

	/**
	 * @return the sslPassword
	 */
	public static String getSslPassword() {
		return sslPassword;
	}

	/**
	 * @return Returns the username.
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username The username to set.
	 */
	public void setUsername(final String username) {
		ServiceBase.username = username;
	}

	/**
	 * @return Returns the password.
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(final String password) {
		ServiceBase.password = password;
	}

	/**
	 * It gets the Shared drive location.
	 * 
	 * @return
	 */

	public static String getProxyHost() {
		return proxyHost;
	}

	public static String getProxyPort() {
		return proxyPort;
	}

	public static String getProxyScheme() {
		return proxyScheme;
	}

	public static String getIsThroughProxy() {
		return isThroughProxy;
	}

	public static String getProxyUserName() {
		return proxyUserName;
	}

	public static String getProxyPassWord() {
		return proxyPassWord;
	}

	public static String getHost() {
		return host;
	}

	public String getaccessTokenbaseurl() {
		return accessTokenbaseurl;
	}

	public String getconsumerClientId() {
		return consumerClientId;
	}

	public String getconsumerClientSecret() {
		return consumerClientSecret;
	}

	public String getconsumerRedirectUrl() {
		return consumerRedirectUrl;
	}

	public String getconsumerGrantType() {
		return consumerGrantType;
	}

	public static String getCacertsLocation() {
		return cacertsLocation;
	}
	
	public static String getUrl() {
		return url;
	}

	public static void setCredentials() {

		ServiceBase.scheme = ServiceBase.getScheme();
		ServiceBase.host = ServiceBase.getHost();
		ServiceBase.port = ServiceBase.getPort();
		ServiceBase.baseServiceURL = ServiceBase.getServicebase();
	}

	public static String getFrom() {
		return from;
	}

	public static String getTo() {
		return to;
	}

	public static String getSubject() {
		return subject;
	}

	public static String getMailUrl() {
		return mailUrl;
	}

	public static String getApiKey() {
		return apiKey;
	}

	public static String getEnv() {
		return env;
	}
}
