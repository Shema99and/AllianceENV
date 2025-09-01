package com.zifo.ewb.alliance.utils;

public class GetRowCountJson {
	private GetRowCountJson() {
	}
	
	public static String getRowCount() {             
		return "{\r\n" + "    \"batch-request\": [\r\n" + "        { \"version\": \"1.0\" },\r\n" + "        [\r\n"
				+ "            {\r\n" + "                \"api-id\": \"table.structure\",\r\n"
				+ "                \"api-version\": \"1.0\"\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"data\": {\r\n" + "                    \"tables\": [\"Alliance\"],\r\n"
				+ "                    \"options\": {\r\n"
				+ "                        \"itemNames\": \"dataDimension\"\r\n" + "                    }\r\n"
				+ "                }\r\n" + "            }\r\n" + "]]}";
	}
}
