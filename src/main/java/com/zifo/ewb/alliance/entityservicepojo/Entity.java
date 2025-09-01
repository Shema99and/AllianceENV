package com.zifo.ewb.alliance.entityservicepojo;

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
@JsonPropertyOrder({ "entityId", "visibleUniqueId", "viewableInEntityId", "entityTypeName", "entityNature",
		"nodeDisplayText", "entityName", "publishedFlag", "lockedAgainstEdit", "collaboration", "iconUrl" })
@Generated("jsonschema2pojo")
public class Entity {

	@JsonProperty("entityId")
	private String entityId;
	@JsonProperty("visibleUniqueId")
	private String visibleUniqueId;
	@JsonProperty("viewableInEntityId")
	private String viewableInEntityId;
	@JsonProperty("entityTypeName")
	private String entityTypeName;
	@JsonProperty("entityNature")
	private String entityNature;
	@JsonProperty("nodeDisplayText")
	private String nodeDisplayText;
	@JsonProperty("entityName")
	private String entityName;
	@JsonProperty("publishedFlag")
	private Boolean publishedFlag;
	@JsonProperty("lockedAgainstEdit")
	private Boolean lockedAgainstEdit;
	@JsonProperty("collaboration")
	private Boolean collaboration;
	@JsonProperty("iconUrl")
	private String iconUrl;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("entityId")
	public String getEntityId() {
		return entityId;
	}

	@JsonProperty("entityId")
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@JsonProperty("visibleUniqueId")
	public String getVisibleUniqueId() {
		return visibleUniqueId;
	}

	@JsonProperty("visibleUniqueId")
	public void setVisibleUniqueId(String visibleUniqueId) {
		this.visibleUniqueId = visibleUniqueId;
	}

	@JsonProperty("viewableInEntityId")
	public String getViewableInEntityId() {
		return viewableInEntityId;
	}

	@JsonProperty("viewableInEntityId")
	public void setViewableInEntityId(String viewableInEntityId) {
		this.viewableInEntityId = viewableInEntityId;
	}

	@JsonProperty("entityTypeName")
	public String getEntityTypeName() {
		return entityTypeName;
	}

	@JsonProperty("entityTypeName")
	public void setEntityTypeName(String entityTypeName) {
		this.entityTypeName = entityTypeName;
	}

	@JsonProperty("entityNature")
	public String getEntityNature() {
		return entityNature;
	}

	@JsonProperty("entityNature")
	public void setEntityNature(String entityNature) {
		this.entityNature = entityNature;
	}

	@JsonProperty("nodeDisplayText")
	public String getNodeDisplayText() {
		return nodeDisplayText;
	}

	@JsonProperty("nodeDisplayText")
	public void setNodeDisplayText(String nodeDisplayText) {
		this.nodeDisplayText = nodeDisplayText;
	}

	@JsonProperty("entityName")
	public String getEntityName() {
		return entityName;
	}

	@JsonProperty("entityName")
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@JsonProperty("publishedFlag")
	public Boolean getPublishedFlag() {
		return publishedFlag;
	}

	@JsonProperty("publishedFlag")
	public void setPublishedFlag(Boolean publishedFlag) {
		this.publishedFlag = publishedFlag;
	}

	@JsonProperty("lockedAgainstEdit")
	public Boolean getLockedAgainstEdit() {
		return lockedAgainstEdit;
	}

	@JsonProperty("lockedAgainstEdit")
	public void setLockedAgainstEdit(Boolean lockedAgainstEdit) {
		this.lockedAgainstEdit = lockedAgainstEdit;
	}

	@JsonProperty("collaboration")
	public Boolean getCollaboration() {
		return collaboration;
	}

	@JsonProperty("collaboration")
	public void setCollaboration(Boolean collaboration) {
		this.collaboration = collaboration;
	}

	@JsonProperty("iconUrl")
	public String getIconUrl() {
		return iconUrl;
	}

	@JsonProperty("iconUrl")
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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