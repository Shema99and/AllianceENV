package com.zifo.ewb.alliance.serviceh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.helper.CatalogHelper;
import com.zifo.ewb.alliance.restclient.EWBRestClient;

@Service
public class CatalogService {
	
	@Autowired
	public  EWBRestClient ewbRestClient;
	@Autowired
	public CatalogHelper cataloghelper;
	
	public String getCatalogId(String path) throws ApiException {
		String response= ewbRestClient.getGuId(path);
//		String guid = cataloghelper.retrieveId(response);
		return cataloghelper.retrieveId(response);
	}

	public String getCatalogDetails(String catalogId) throws ApiException {
//		String response = ewbRestClient.getCatalogDetails(catalogId);
		return ewbRestClient.getCatalogDetails(catalogId);
	}
}
