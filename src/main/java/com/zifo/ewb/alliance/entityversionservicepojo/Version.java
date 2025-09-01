package com.zifo.ewb.alliance.entityversionservicepojo;

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
@JsonPropertyOrder({ "versionState", "versionNumber", "versionId", "timeSaved", "userName", "userFullName",
		"authorComment", "authorAdditionalComment", "witness", "witnessComment", "witnessAdditionalComment" })
@Generated("jsonschema2pojo")
public class Version {

	@JsonProperty("versionState")
	private String versionState;
	@JsonProperty("versionNumber")
	private Integer versionNumber;
	@JsonProperty("versionId")
	private String versionId;
	@JsonProperty("timeSaved")
	private Long timeSaved;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("userFullName")
	private String userFullName;
	@JsonProperty("authorComment")
	private String authorComment;
	@JsonProperty("authorAdditionalComment")
	private Object authorAdditionalComment;
	@JsonProperty("witness")
	private Object witness;
	@JsonProperty("witnessComment")
	private Object witnessComment;
	@JsonProperty("witnessAdditionalComment")
	private Object witnessAdditionalComment;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("versionState")
	public String getVersionState() {
		return versionState;
	}

	@JsonProperty("versionState")
	public void setVersionState(String versionState) {
		this.versionState = versionState;
	}

	@JsonProperty("versionNumber")
	public Integer getVersionNumber() {
		return versionNumber;
	}

	@JsonProperty("versionNumber")
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	@JsonProperty("versionId")
	public String getVersionId() {
		return versionId;
	}

	@JsonProperty("versionId")
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	@JsonProperty("timeSaved")
	public Long getTimeSaved() {
		return timeSaved;
	}

	@JsonProperty("timeSaved")
	public void setTimeSaved(Long timeSaved) {
		this.timeSaved = timeSaved;
	}

	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty("userFullName")
	public String getUserFullName() {
		return userFullName;
	}

	@JsonProperty("userFullName")
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	@JsonProperty("authorComment")
	public String getAuthorComment() {
		return authorComment;
	}

	@JsonProperty("authorComment")
	public void setAuthorComment(String authorComment) {
		this.authorComment = authorComment;
	}

	@JsonProperty("authorAdditionalComment")
	public Object getAuthorAdditionalComment() {
		return authorAdditionalComment;
	}

	@JsonProperty("authorAdditionalComment")
	public void setAuthorAdditionalComment(Object authorAdditionalComment) {
		this.authorAdditionalComment = authorAdditionalComment;
	}

	@JsonProperty("witness")
	public Object getWitness() {
		return witness;
	}

	@JsonProperty("witness")
	public void setWitness(Object witness) {
		this.witness = witness;
	}

	@JsonProperty("witnessComment")
	public Object getWitnessComment() {
		return witnessComment;
	}

	@JsonProperty("witnessComment")
	public void setWitnessComment(Object witnessComment) {
		this.witnessComment = witnessComment;
	}

	@JsonProperty("witnessAdditionalComment")
	public Object getWitnessAdditionalComment() {
		return witnessAdditionalComment;
	}

	@JsonProperty("witnessAdditionalComment")
	public void setWitnessAdditionalComment(Object witnessAdditionalComment) {
		this.witnessAdditionalComment = witnessAdditionalComment;
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