package com.zifo.ewb.spreadsheetpojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BatchResponse {

	@JsonProperty("api-responses")
	private List<ApiResponse> apiResponses;
	@JsonProperty("status")
	private Status status;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("api-responses")
	public List<ApiResponse> getApiResponses() {
		return apiResponses;
	}

	@JsonProperty("api-responses")
	public void setApiResponses(List<ApiResponse> apiResponses) {
		this.apiResponses = apiResponses;
	}

	@JsonProperty("status")
	public Status getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}