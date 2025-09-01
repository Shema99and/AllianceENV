//package com.zifo.ewb.alliance.Controller;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.lang.NonNull;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.NoArgsConstructor;
//
///**
// * @author zifo
// *
// */
//@RestController
//@NoArgsConstructor
//@RequestMapping("/EWBExportAPI/v1")
//@Api(tags = "Authorization API", description = "Get the access token for API access", position = 3)
//public class Authorization {
//
//	private RestTemplate restTemplate = new RestTemplate();
//	@Value("${tokenurl}")
//	String tokenurl;
//
//	@ApiOperation(value = "Get the token before accessing the resource endpoints", position = 3)
//	@PostMapping("/oauth2/token")
//	public ResponseEntity<Object> getAccessToken(
//			@RequestHeader(name = "Authorization", required = true) @NonNull String encodedCredentials) {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		headers.set(HttpHeaders.AUTHORIZATION, encodedCredentials);
//
//		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(tokenurl);
//		builder.queryParam("grant_type", "client_credentials");
//
//		ResponseEntity<Object> exchange = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, entity,
//				Object.class);
//
//		return exchange;
//	}
//}