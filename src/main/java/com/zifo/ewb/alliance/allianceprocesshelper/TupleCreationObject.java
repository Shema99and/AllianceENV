package com.zifo.ewb.alliance.allianceprocesshelper;

import java.util.List;

import com.gsk.api.datarestrictionpojo.Datum;
import com.zifo.ewb.alliance.services.CatalogService;
import com.zifo.ewb.catalogpojo.Tuple;



public class TupleCreationObject {
	
	private Datum allianceData;
	
	private Tuple tuple;
	
	private String catalogId;
	
	private String exptRestriction;
	
	private String phyCmdRestriction;
	
	private CatalogService catalogService;
	
	private List<String> sortedIDBS;
	
	private List<String> sortedCatIDBS;
	 
	private List<String> sortedAlliance;
	  
	private List<String> sortedCatAlliList;
	  
	private boolean check;

	public  Datum getAllianceData() {
		return allianceData;
	}

	public void setAllianceData(Datum allianceData) {
		this.allianceData = allianceData;
	}

	public Tuple getTuple() {
		return tuple;
	}

	public void setTuple(Tuple tuple) {
		this.tuple = tuple;
	}

	public  String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public  String getExptRestriction() {
		return exptRestriction;
	}

	public void setExptRestriction(String exptRestriction) {
		this.exptRestriction = exptRestriction;
	}

	public  String getPhyCmdRestriction() {
		return phyCmdRestriction;
	}

	public void setPhyCmdRestriction(String phyCmdRestriction) {
		this.phyCmdRestriction = phyCmdRestriction;
	}

	public  CatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public  List<String> getSortedIDBS() {
		return sortedIDBS;
	}

	public void setSortedIDBS(List<String> sortedIDBS) {
		this.sortedIDBS = sortedIDBS;
	}

	public  List<String> getSortedCatIDBS() {
		return sortedCatIDBS;
	}

	public  void setSortedCatIDBS(List<String> sortedCatIDBS) {
		this.sortedCatIDBS = sortedCatIDBS;
	}

	public  List<String> getSortedAlliance() {
		return sortedAlliance;
	}

	public void setSortedAlliance(List<String> sortedAlliance) {
		this.sortedAlliance = sortedAlliance;
	}

	public  List<String> getSortedCatAlliList() {
		return sortedCatAlliList;
	}

	public void setSortedCatAlliList(List<String> sortedCatAlliList) {
		this.sortedCatAlliList = sortedCatAlliList;
	}

	public  boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
