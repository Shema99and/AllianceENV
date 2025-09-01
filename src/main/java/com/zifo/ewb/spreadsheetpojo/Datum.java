package com.zifo.ewb.spreadsheetpojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Datum {

	@JsonProperty("Index")
	private Index index;
	@JsonProperty("Alliance Name")
	private AllianceName allianceName;
	@JsonProperty("Alliance Members")
	private AllianceMembers allianceMembers;
	@JsonProperty("Experiment Restriction")
	private ExperimentRestriction experimentRestriction;
	@JsonProperty("IDBS Members")
	private IDBSMembers iDBSMembers;
	@JsonProperty("Physical Compound Restriction")
	private PhysicalCompoundRestriction physicalCompoundRestriction;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("Index")
	public Index getIndex() {
		return index;
	}

	@JsonProperty("Index")
	public void setIndex(Index index) {
		this.index = index;
	}

	@JsonProperty("Alliance Name")
	public AllianceName getAllianceName() {
		return allianceName;
	}

	@JsonProperty("Alliance Name")
	public void setAllianceName(AllianceName allianceName) {
		this.allianceName = allianceName;
	}

	@JsonProperty("Alliance Members")
	public AllianceMembers getAllianceMembers() {
		return allianceMembers;
	}

	@JsonProperty("Alliance Members")
	public void setAllianceMembers(AllianceMembers allianceMembers) {
		this.allianceMembers = allianceMembers;
	}

	@JsonProperty("Experiment Restriction")
	public ExperimentRestriction getExperimentRestriction() {
		return experimentRestriction;
	}

	@JsonProperty("Experiment Restriction")
	public void setExperimentRestriction(ExperimentRestriction experimentRestriction) {
		this.experimentRestriction = experimentRestriction;
	}

	@JsonProperty("IDBS Members")
	public IDBSMembers getIDBSMembers() {
		return iDBSMembers;
	}

	@JsonProperty("IDBS Members")
	public void setIDBSMembers(IDBSMembers iDBSMembers) {
		this.iDBSMembers = iDBSMembers;
	}

	@JsonProperty("Physical Compound Restriction")
	public PhysicalCompoundRestriction getPhysicalCompoundRestriction() {
		return physicalCompoundRestriction;
	}

	@JsonProperty("Physical Compound Restriction")
	public void setPhysicalCompoundRestriction(PhysicalCompoundRestriction physicalCompoundRestriction) {
		this.physicalCompoundRestriction = physicalCompoundRestriction;
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