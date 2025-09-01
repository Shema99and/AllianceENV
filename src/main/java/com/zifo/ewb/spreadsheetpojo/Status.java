package com.zifo.ewb.spreadsheetpojo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success", "statusCode" })
@Generated("jsonschema2pojo")
public class Status {

	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("statusCode")
	private Integer statusCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@JsonProperty("statusCode")
	public Integer getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
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