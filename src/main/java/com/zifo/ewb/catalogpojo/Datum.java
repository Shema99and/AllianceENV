package com.zifo.ewb.catalogpojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Datum {

	@JsonProperty("name")
	private String name;
	@JsonProperty("value")
	private String value;
	@JsonProperty("displayValue")
	private String displayValue;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("displayValue")
	public String getDisplayValue() {
		return displayValue;
	}

	@JsonProperty("displayValue")
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

}
