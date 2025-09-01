package com.zifo.ewb.alliance.helper;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CatalogHelper {
	
	public String retrieveId(String guid) {
		String catalogId = StringUtils.EMPTY;
		if (!guid.isEmpty()) {
			JSONObject response = new JSONObject(guid);
			catalogId = response.getString("id");
		}
		return catalogId;
	}

}
