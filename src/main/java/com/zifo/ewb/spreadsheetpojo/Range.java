package com.zifo.ewb.spreadsheetpojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Range {

	@JsonProperty("range")
	private String range;
	@JsonProperty("data")
	private List<Datum> data;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("range")
	public String getRange() {
		return range;
	}

	@JsonProperty("range")
	public void setRange(String range) {
		this.range = range;
	}

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
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