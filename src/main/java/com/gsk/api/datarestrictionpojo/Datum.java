package com.gsk.api.datarestrictionpojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Datum {

	@JsonProperty("agreementContractId")
	private Integer agreementContractId;
	@JsonProperty("agreement")
	private Agreement agreement;
	@JsonProperty("projects")
	private List<Object> projects = null;
	@JsonProperty("teamMembers")
	private List<String> teamMembers = null;
	@JsonProperty("approvers")
	private List<String> approvers = null;
	@JsonProperty("agreementParties")
	private List<Object> agreementParties = null;
	@JsonProperty("contacts")
	private List<Contact> contacts = null;
	@JsonProperty("experimentRestriction")
	private ExperimentRestriction experimentRestriction;
	@JsonProperty("physicalCompoundRestriction")
	private PhysicalCompoundRestriction physicalCompoundRestriction;
	@JsonProperty("multipleRestrictionsFlag")
	private Boolean multipleRestrictionsFlag;
	@JsonProperty("multipleRestrictonInstructions")
	private Object multipleRestrictonInstructions;
	@JsonProperty("creationDate")
	private String creationDate;
	@JsonProperty("modificationDate")
	private String modificationDate;
	@JsonProperty("effectiveDate")
	private Object effectiveDate;
	@JsonProperty("endDate")
	private Object endDate;
	@JsonProperty("version")
	private Integer version;
	@JsonProperty("status")
	private String status;
	@JsonProperty("isActive")
	private Boolean isActive;
	@JsonProperty("securityGroupName")
	private String securityGroupName;
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

	@JsonProperty("agreementContractId")
	public Integer getAgreementContractId() {
		return agreementContractId;
	}

	@JsonProperty("agreementContractId")
	public void setAgreementContractId(Integer agreementContractId) {
		this.agreementContractId = agreementContractId;
	}

	@JsonProperty("agreement")
	public Agreement getAgreement() {
		return agreement;
	}

	@JsonProperty("agreement")
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	@JsonProperty("projects")
	public List<Object> getProjects() {
		return projects;
	}

	@JsonProperty("projects")
	public void setProjects(List<Object> projects) {
		this.projects = projects;
	}

	@JsonProperty("teamMembers")
	public List<String> getTeamMembers() {
		return teamMembers;
	}

	@JsonProperty("teamMembers")
	public void setTeamMembers(List<String> teamMembers) {
		this.teamMembers = teamMembers;
	}

	@JsonProperty("approvers")
	public List<String> getApprovers() {
		return approvers;
	}

	@JsonProperty("approvers")
	public void setApprovers(List<String> approvers) {
		this.approvers = approvers;
	}

	@JsonProperty("agreementParties")
	public List<Object> getAgreementParties() {
		return agreementParties;
	}

	@JsonProperty("agreementParties")
	public void setAgreementParties(List<Object> agreementParties) {
		this.agreementParties = agreementParties;
	}

	@JsonProperty("contacts")
	public List<Contact> getContacts() {
		return contacts;
	}

	@JsonProperty("contacts")
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@JsonProperty("experimentRestriction")
	public ExperimentRestriction getExperimentRestriction() {
		return experimentRestriction;
	}

	@JsonProperty("experimentRestriction")
	public void setExperimentRestriction(ExperimentRestriction experimentRestriction) {
		this.experimentRestriction = experimentRestriction;
	}

	@JsonProperty("physicalCompoundRestriction")
	public PhysicalCompoundRestriction getPhysicalCompoundRestriction() {
		return physicalCompoundRestriction;
	}

	@JsonProperty("physicalCompoundRestriction")
	public void setPhysicalCompoundRestriction(PhysicalCompoundRestriction physicalCompoundRestriction) {
		this.physicalCompoundRestriction = physicalCompoundRestriction;
	}

	@JsonProperty("multipleRestrictionsFlag")
	public Boolean getMultipleRestrictionsFlag() {
		return multipleRestrictionsFlag;
	}

	@JsonProperty("multipleRestrictionsFlag")
	public void setMultipleRestrictionsFlag(Boolean multipleRestrictionsFlag) {
		this.multipleRestrictionsFlag = multipleRestrictionsFlag;
	}

	@JsonProperty("multipleRestrictonInstructions")
	public Object getMultipleRestrictonInstructions() {
		return multipleRestrictonInstructions;
	}

	@JsonProperty("multipleRestrictonInstructions")
	public void setMultipleRestrictonInstructions(Object multipleRestrictonInstructions) {
		this.multipleRestrictonInstructions = multipleRestrictonInstructions;
	}

	@JsonProperty("creationDate")
	public String getCreationDate() {
		return creationDate;
	}

	@JsonProperty("creationDate")
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@JsonProperty("modificationDate")
	public String getModificationDate() {
		return modificationDate;
	}

	@JsonProperty("modificationDate")
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	@JsonProperty("effectiveDate")
	public Object getEffectiveDate() {
		return effectiveDate;
	}

	@JsonProperty("effectiveDate")
	public void setEffectiveDate(Object effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("endDate")
	public Object getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}

	@JsonProperty("version")
	public Integer getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(Integer version) {
		this.version = version;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("isActive")
	public Boolean getIsActive() {
		return isActive;
	}

	@JsonProperty("isActive")
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@JsonProperty("securityGroupName")
	public String getSecurityGroupName() {
		return securityGroupName;
	}

	@JsonProperty("securityGroupName")
	public void setSecurityGroupName(String securityGroupName) {
		this.securityGroupName = securityGroupName;
	}

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