package com.zifo.ewb.alliance.mailcontent;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "access_token", "token_type", "expires_in" })
/**
 * 
 * @author zifo
 *
 */
public class OAuthTokenPojo {
	/** access token */
	@JsonProperty("access_token")
	private String accessToken;
	/** field token type */
	@JsonProperty("token_type")
	private String tokenType;
	/**
	 * field
	 */
	@JsonProperty("expires_in")
	private Integer expiresIn;
	/** field properties */
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	@JsonProperty("token_type")
	public void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}

	@JsonProperty("expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	@JsonProperty("expires_in")
	public void setExpiresIn(final Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(final String name, final Object value) {
		this.additionalProperties.put(name, value);
	}

	/**
	 * 
	 */
	public OAuthTokenPojo() {
		/** empty constructor */
	}
}
