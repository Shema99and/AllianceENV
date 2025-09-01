package com.gsk.api.datarestrictionpojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InputPojo {

	@JsonProperty("data")
	private List<Datum> data = null;
	@JsonProperty("paging")
	private Paging paging;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	@JsonProperty("paging")
	public Paging getPaging() {
		return paging;
	}

	@JsonProperty("paging")
	public void setPaging(Paging paging) {
		this.paging = paging;
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