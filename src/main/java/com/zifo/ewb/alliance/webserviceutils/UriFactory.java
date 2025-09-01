package com.zifo.ewb.alliance.webserviceutils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.google.common.collect.ImmutableMap;

/**
 * Helper class for building URIs
 */
public class UriFactory {

	/**
	 * It is the scheme value
	 */
	private final transient String scheme;

	/**
	 * It is the host value
	 */
	private final transient String host;

	/**
	 * It is the port value
	 */
	private final transient int port;

	/**
	 * Constructor of this class
	 * 
	 * @param scheme
	 * @param host
	 * @param port
	 */
	public UriFactory(final String scheme, final String host, final int port) {
		this.scheme = scheme;
		this.host = host;
		this.port = port;
	}

	/**
	 * builds the uri for the request
	 * 
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 */
	public URI buildURI(final String url) throws URISyntaxException {

		final URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(scheme).setHost(host).setPort(port).setPath(url);

		return uriBuilder.build();
	}

	/**
	 * Builds the uri for the given parameters
	 * 
	 * @param url
	 * @param queryParams
	 * @return
	 * @throws URISyntaxException
	 */
	public URI buildUriWithQueryParams(final String url, final ImmutableMap<String, String> queryParams)
			throws URISyntaxException {
		final URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(scheme).setHost(host).setPort(port).setPath(url);

		for (final Map.Entry<String, String> entry : queryParams.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		return uriBuilder.build();
	}

	public URI buildUriWithQueryParamsForPool(final String url, final ImmutableMap<String, String> queryParams)
			throws URISyntaxException {
		final URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(scheme).setHost(host).setPort(port).setPath(url);

		for (final Map.Entry<String, String> entry : queryParams.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		return uriBuilder.build();
	}

}
