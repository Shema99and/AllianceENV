package com.zifo.ewb.alliance.helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zifo.ewb.alliance.constants.JSONConstants;

public class GetTableUpdateJson {
	public static String getTableUpdateJSON(final List<Map<String, String>> exceptionlist, final String tableName,   // need to put in helper class
			final Map<String, String> columnType) {

		final String start = "{\r\n" + "	\"batch-request\": [{\r\n" + "			\"version\": \"1.0\"\r\n"
				+ "		},\r\n" + "		[{\r\n" + "			\"api-version\": \"1.0\",\r\n"
				+ "			\"api-id\": \"table.data.set\"\r\n" + "		}, {\r\n" + "			\"data\": {\r\n"
				+ "				\"tables\": [{\r\n" + "						\"name\": \"" + tableName + "\"\r\n"
				+ "					},\r\n" + "					[";

		String mid = StringUtils.EMPTY;
		for (final Map<String, String> exceptiondata : exceptionlist) {
			for (final Map.Entry<String, String> entry : exceptiondata.entrySet()) {
				final String keyvalue = entry.getKey();
				if (mid.length() > 0) {
					mid = mid + "\"" + keyvalue + "\": {\r\n" + "\"" + columnType.get(keyvalue) + "\": ";
					if (columnType.get(keyvalue).equals(JSONConstants.NUMBER)) {
						mid = mid + entry.getValue() + "},\r\n";
					} else {
						mid = mid + "\"" + entry.getValue() + "\"\r\n" + "},\r\n";
					}
				} else {
					mid = mid + "{\r\n" + "						\"" + keyvalue + "\": {\r\n"
							+ "							\"" + columnType.get(keyvalue) + "\":";
					if (columnType.get(keyvalue).equals(JSONConstants.NUMBER)) {
						mid = mid + entry.getValue() + "},\r\n";
					} else {
						mid = mid + "\"" + entry.getValue() + "\"\r\n" + "},\r\n";
					}
				}
			}
			if (mid.length() > 0) {
				mid = mid.substring(0, mid.lastIndexOf(','));
			}
			mid = mid + "					},{";
		}
		if (mid.length() > 0) {
			mid = mid.substring(0, mid.lastIndexOf(','));
		}

		final String end = "]\r\n" + "]\r\n" + "}\r\n" + "}]\r\n" + "]\r\n" + "}";

		return start + mid + end;
	}

}
