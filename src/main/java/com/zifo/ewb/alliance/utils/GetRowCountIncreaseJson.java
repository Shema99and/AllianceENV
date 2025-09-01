package com.zifo.ewb.alliance.utils;

public class GetRowCountIncreaseJson {
	private GetRowCountIncreaseJson() {
	}
	public static String getRowCountIncreaseJson(final String tableName, final String nonDD, final int position, final int newRowCount) {
		return "{\r\n" + " \"batch-request\": [\r\n" + " {\r\n" + " \"version\": \"1.0\",\r\n" + " \"options\": {\r\n"
				+ " \"force\": true\r\n" + " }\r\n" + " },\r\n" + " [\r\n" + " {\r\n"
				+ " \"api-id\": \"table.structure.insert\",\r\n" + " \"api-version\": \"1.0\"\r\n" + " },\r\n"
				+ " {\r\n" + " \"data\": {\r\n" + " \"tables\": {\r\n" + " \"" + tableName + "\": {\r\n" + " \"" + nonDD
				+ "\": {\r\n" + " \"itemCount\": " + newRowCount + ",\r\n" + " \"insertAt\": " + position + "\r\n"
				+ " }\r\n" + " }\r\n" + " }\r\n" + " }\r\n" + " }\r\n" + " ]\r\n" + " ]\r\n" + "}";
	}
}
