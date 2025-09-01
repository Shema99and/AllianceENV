package com.zifo.ewb.catalogpojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tuple {

	@JsonProperty("data")
	private List<Datum> data;
	@JsonProperty("id")
	private String id;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonProperty("defaultTuple")
	private Boolean defaultTuple;
	@JsonProperty("deletable")
	private Boolean deletable;
	@JsonProperty("editable")
	private Boolean editable;

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("defaultTuple")
	public Boolean getDefaultTuple() {
		return defaultTuple;
	}

	@JsonProperty("defaultTuple")
	public void setDefaultTuple(Boolean defaultTuple) {
		this.defaultTuple = defaultTuple;
	}

	@JsonProperty("deletable")
	public Boolean getDeletable() {
		return deletable;
	}

	@JsonProperty("deletable")
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

	@JsonProperty("editable")
	public Boolean getEditable() {
		return editable;
	}

	@JsonProperty("editable")
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

}
