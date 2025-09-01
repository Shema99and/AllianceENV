package com.gsk.api.datarestrictionpojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhysicalCompoundRestriction {

	@JsonProperty("restrictionTag")
	private Object restrictionTag;
	@JsonProperty("id")
	private String id;
	@JsonProperty("restriction")
	private String restriction;
	@JsonProperty("code")
	private Integer code;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("restrictionTag")
	public Object getRestrictionTag() {
		return restrictionTag;
	}

	@JsonProperty("restrictionTag")
	public void setRestrictionTag(Object restrictionTag) {
		this.restrictionTag = restrictionTag;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("restriction")
	public String getRestriction() {
		return restriction;
	}

	@JsonProperty("restriction")
	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	@JsonProperty("code")
	public Integer getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(Integer code) {
		this.code = code;
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
