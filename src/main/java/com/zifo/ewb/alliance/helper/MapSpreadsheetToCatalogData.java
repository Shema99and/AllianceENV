package com.zifo.ewb.alliance.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zifo.ewb.catalogpojo.CatalogDetailPojo;
import com.zifo.ewb.catalogpojo.Tuple;
import com.zifo.ewb.spreadsheetpojo.Datum;

public class MapSpreadsheetToCatalogData {
	public String mapSpreadsheetToCatalogData(String spreadSheetData) 
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		CatalogDetailPojo catalogDetail = new CatalogDetailPojo();
		com.zifo.ewb.spreadsheetpojo.TableData data = mapper.readValue(spreadSheetData,
				com.zifo.ewb.spreadsheetpojo.TableData.class);
		List<Tuple> tuples = new ArrayList<>();
		for (Datum singlerow : data.getBatchResponse().getApiResponses().get(0).getTables().get(0).getRanges().get(0)
				.getData()) {
			if (!singlerow.getAllianceName().getString().isEmpty()) {
				final Tuple input = new Tuple();
				input.setId(StringUtils.EMPTY);
				input.setDefaultTuple(false);
				input.setDeletable(true);
				input.setEditable(true);
				input.setEnabled(true);
				List<com.zifo.ewb.catalogpojo.Datum> dataList = new ArrayList<>();
				com.zifo.ewb.catalogpojo.Datum catalogData = new com.zifo.ewb.catalogpojo.Datum();
				if (!singlerow.getAllianceMembers().getString().isEmpty()) {
					catalogData.setName("Alliance Members");
					catalogData.setValue(singlerow.getAllianceMembers().getString());
					catalogData.setDisplayValue(singlerow.getAllianceMembers().getString());
				} else {
					catalogData.setName("Alliance Members");
					catalogData.setValue(StringUtils.EMPTY);
					catalogData.setDisplayValue(StringUtils.EMPTY);
				}
				com.zifo.ewb.catalogpojo.Datum catalogData1 = new com.zifo.ewb.catalogpojo.Datum();
				if (!singlerow.getAllianceName().getString().isEmpty()) {
					catalogData1.setName("Alliance Name");
					catalogData1.setValue(singlerow.getAllianceName().getString());
					catalogData1.setDisplayValue(singlerow.getAllianceName().getString());
				}
				com.zifo.ewb.catalogpojo.Datum catalogData2 = new com.zifo.ewb.catalogpojo.Datum();
				if (!singlerow.getIDBSMembers().getString().isEmpty()) {
					catalogData2.setName("IDBS Members");
					catalogData2.setValue(singlerow.getIDBSMembers().getString());
					catalogData2.setDisplayValue(singlerow.getIDBSMembers().getString());
				} else {
					catalogData2.setName("IDBS Members");
					catalogData2.setValue(StringUtils.EMPTY);
					catalogData2.setDisplayValue(StringUtils.EMPTY);
				}
				com.zifo.ewb.catalogpojo.Datum catalogData3 = new com.zifo.ewb.catalogpojo.Datum();
				if (!singlerow.getExperimentRestriction().getString().isEmpty()) {
					catalogData3.setName("Experiment Restriction");
					catalogData3.setValue(singlerow.getExperimentRestriction().getString());
					catalogData3.setDisplayValue(singlerow.getExperimentRestriction().getString());
				} else {
					catalogData3.setName("Experiment Restriction");
					catalogData3.setValue(StringUtils.EMPTY);
					catalogData3.setDisplayValue(StringUtils.EMPTY);
				}
				com.zifo.ewb.catalogpojo.Datum catalogData4 = new com.zifo.ewb.catalogpojo.Datum();
				if (!singlerow.getExperimentRestriction().getString().isEmpty()) {
					catalogData4.setName("Physical CompoundRestriction");
					catalogData4.setValue(singlerow.getPhysicalCompoundRestriction().getString());
					catalogData4.setDisplayValue(singlerow.getPhysicalCompoundRestriction().getString());
				} else {
					catalogData4.setName("Physical CompoundRestriction");
					catalogData4.setValue(StringUtils.EMPTY);
					catalogData4.setDisplayValue(StringUtils.EMPTY);
				}
				dataList.add(catalogData);
				dataList.add(catalogData1);
				dataList.add(catalogData2);
				dataList.add(catalogData3);
				dataList.add(catalogData4);
				input.setData(dataList);
				tuples.add(input);
			}
		}
		catalogDetail.setTuple(tuples);
		catalogDetail.setPath(StringUtils.EMPTY);
		String response = StringUtils.EMPTY;
		if (data.getBatchResponse().getApiResponses().get(0).getTables().get(0).getRanges().get(0).getData()
				.size() > 1) {
			response = mapper.writeValueAsString(catalogDetail);
		}
		return response;
	}
}
