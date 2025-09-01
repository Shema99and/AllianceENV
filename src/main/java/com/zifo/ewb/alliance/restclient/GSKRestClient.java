package com.zifo.ewb.alliance.restclient;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;


@Service
public class GSKRestClient {

	private static final Logger logger = LogManager.getLogger(GSKRestClient.class);

	private final WebClient restClient;

	public GSKRestClient(@Value("${gsk.hostUrl}") String hostUrl, @Value("${gsk.apiBaseUrl}") String apiBaseUrl,
			@Value("${gsk.authorization}") String authorization) {

		restClient = WebClient.builder().baseUrl(hostUrl + apiBaseUrl).defaultHeaders(headers -> {
			headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			headers.set("Authorization",authorization);
			headers.setContentType(MediaType.APPLICATION_JSON);
		}).exchangeStrategies(ExchangeStrategies.builder() // Increasing size of the response accepted to 16MB
				.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build()).build();
	}
	
	public String getAllianceDetails(int offset, int limit) throws ApiException {
//		ResponseEntity<String> response = null;
		try {
			ResponseEntity<String> response = restClient.get()
					.uri(uriBuilder -> uriBuilder.path("/agreement-packets").queryParam("offset",offset).queryParam("limit", limit).build())
					.retrieve().toEntity(String.class)
					.block();
			if (response == null || response.getBody() == null) {
	            throw new ApiException("Alliance details response is empty or null", HttpStatus.NO_CONTENT);
	        }

	        return response.getBody();
		} catch (WebClientResponseException e) {
			throw new ApiException("Error Retrieving the Alliance Details", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
	        throw new ApiException("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
//		return response.getBody();
	}
}
