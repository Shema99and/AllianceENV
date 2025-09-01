package com.zifo.ewb.spreadsheetpojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TableData {

	@JsonProperty("batch-response")
	private BatchResponse batchResponse;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("batch-response")
	public BatchResponse getBatchResponse() {
		return batchResponse;
	}

	@JsonProperty("batch-response")
	public void setBatchResponse(BatchResponse batchResponse) {
		this.batchResponse = batchResponse;
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