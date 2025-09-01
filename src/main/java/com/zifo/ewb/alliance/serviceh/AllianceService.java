package com.zifo.ewb.alliance.serviceh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zifo.ewb.alliance.exceptionhandler.ApiException;
import com.zifo.ewb.alliance.restclient.GSKRestClient;

@Service
public class AllianceService {
	
	@Autowired
	public GSKRestClient gskRestClient;

	public String getAllianceDetails(int offset, int limit) throws ApiException {
//		String allianceDetails= gskRestClient.getAllianceDetails(offset, limit);
		return gskRestClient.getAllianceDetails(offset, limit);
	}
}
