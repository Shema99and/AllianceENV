package com.gsk.api.datarestrictionpojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Agreement {

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("abbreviation")
	private String abbreviation;
	@JsonProperty("shortName")
	private String shortName;
	@JsonProperty("longName")
	private String longName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("abbreviation")
	public String getAbbreviation() {
		return abbreviation;
	}

	@JsonProperty("abbreviation")
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@JsonProperty("shortName")
	public String getShortName() {
		return shortName;
	}

	@JsonProperty("shortName")
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@JsonProperty("longName")
	public String getLongName() {
		return longName;
	}

	@JsonProperty("longName")
	public void setLongName(String longName) {
		this.longName = longName;
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
