package com.zifo.ewb.alliance.webserviceutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JSONConstants;
import com.zifo.ewb.alliance.exceptions.ReadException;

public class SpreadsheetUtils {
	/**
	 * Logger
	 */
	static Logger logger = LoggerFactory.getLogger(SpreadsheetUtils.class.getName());

	public List<String> getHeaderalist(String response) {
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		final JsonArray tablesData = jsonObj.getAsJsonObject(JSONConstants.BATCH_RESPONSE)
				.getAsJsonArray(JSONConstants.API_RESPONSES).get(0).getAsJsonObject()
				.getAsJsonArray(JSONConstants.TABLES);
		return getHeaders(tablesData);
	}

	public List<List<String>> getTableDatalist(String response) throws ReadException {
		JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
		final JsonArray tablesData = jsonObj.getAsJsonObject(JSONConstants.BATCH_RESPONSE)
				.getAsJsonArray(JSONConstants.API_RESPONSES).get(0).getAsJsonObject()
				.getAsJsonArray(JSONConstants.TABLES);
		ArrayList<String> headersList = getHeaders(tablesData);
		final JsonNode tableData = getTableDataJSON(response);
		return getSourceTableDetails(tableData, headersList);
	}

	public static ArrayList<String> getHeaders(JsonArray tablesData) {
		final JsonArray tableData = tablesData.get(0).getAsJsonObject().get("ranges").getAsJsonArray().get(0)
				.getAsJsonObject().getAsJsonArray("data");
		final ArrayList<String> header = new ArrayList<>();

		int index = 0;
		for (final JsonElement row : tableData) {
			final Set<String> rowKeys = row.getAsJsonObject().keySet();

			final int size = rowKeys.size();

			for (final String key : rowKeys) {
				header.add(key);
				if (index == size) {
					break;
				}

			}
			break;
		}
		return header;

	}

	/**
	 * Method to get the values in a table
	 * 
	 * @param tableData
	 * @param columnHeaders
	 * @return
	 * @throws ReadException
	 */
	private static List<List<String>> getSourceTableDetails(final JsonNode tableData, final List<String> columnHeaders)
			throws ReadException {
		final List<List<String>> outputData = new ArrayList<>();

		for (final JsonNode dataIterator : tableData) {

			final ArrayList<String> outputArray = new ArrayList<>();
			for (final String columnHeader : columnHeaders) {
				String header = columnHeader.replace("\"", "");
				createOutputArray(outputArray, dataIterator, header);
			}
			outputData.add(outputArray);

		}

		return outputData;
	}

	/**
	 * @throws ReadException 	
	 * This method is to read output json file from the method getCompoundIds
	 * 
	 * @param outputArray
	 * @param dataIterator
	 * @param columnName
	 * @throws  
	 */
	private static void createOutputArray(final List<String> outputArray, final JsonNode dataIterator,
			final String columnName) throws ReadException {

		String cellValue;
		if (JSONUtils.checkJson(dataIterator, columnName) == Boolean.FALSE) {
			cellValue = JSONUtils.readJson(dataIterator, columnName);
			outputArray.add(cellValue);
		} else {

			outputArray.add(columnName + " column not found");
		}
	}

	private static JsonNode getTableDataJSON(String response) {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode tableData = null;
		try {
			final JsonNode root = mapper.readTree(response);
			final JsonNode apiResponse = root.path(AppConstantsUtils.BATCH_RESPONSE)
					.path(AppConstantsUtils.API_RESPONSES);
			for (final JsonNode tables : apiResponse) {

				for (final JsonNode ranges : tables.path(AppConstantsUtils.TABLES)) {

					for (final JsonNode data : ranges.path(AppConstantsUtils.RANGES)) {

						tableData = data.path(AppConstantsUtils.DATA);

					}
				}
			}
		} catch (IOException ioException) {
			logger.info("error");
			if (logger.isInfoEnabled()) {
				logger.info(ioException.getMessage(), ioException);
			}
		}
		return tableData;
	}

	/**
	 * Method to generate json for retrieve table structure
	 * 
	 * @param value
	 * @return
	 * @throws ReadException
	 */
	public static String createJSONToReadTableStructure(final String tableName) {
//		return "{\"batch-request\": [   { \"version\": \"1.0\" }, [	{ \"api-id\": \"table.structure\", \"api-version\": \"1.0\"	}, {\"data\": {	 \"tables\": [\""
//				+ tableName
//				+ "\"],		                    \"options\": {		                        \"itemNames\": \"all\"		                    }		                }		            }		]]	}";
		 String jsonTemplate = "{\"batch-request\": [ { \"version\": \"1.0\" }, [ { \"api-id\": \"table.structure\", \"api-version\": \"1.0\" }, {\"data\": { \"tables\": [\"%s\"], \"options\": { \"itemNames\": \"all\" } } } ]] }";
		    return String.format(jsonTemplate, tableName.trim());
	}

	public static String createJSONToClearTable(final String tableName) {
		return "{\r\n" + "    \"batch-request\": [\r\n" + "        {\r\n" + "            \"version\": \"1.0\"\r\n"
				+ "        },\r\n" + "        [\r\n" + "            {\r\n"
				+ "                \"api-id\": \"table.data.clear\",\r\n"
				+ "                \"api-version\": \"1.0\"\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"data\": {\r\n" + "                    \"ranges\": [\r\n"
				+ "                        {\r\n" + "                            \"table\": \"" + tableName + "\"\r\n"
				+ "                        }\r\n" + "                    ]\r\n" + "                }\r\n"
				+ "            }\r\n" + "        ]\r\n" + "    ]\r\n" + "}";

	}

	/**
	 * Method to generate json body for retrieve table data
	 * 
	 * @param value
	 * @return
	 */
	public static String createJSONToReadTableData(final String tableName) {

		return "{    \"batch-request\": [        {            \"version\": \"1.0\"        },        [            {                \"api-id\": \"table.data\",               \"api-version\": \"1.0\"            },            {                \"data\": {                    \"queries\": [                        {                           \"table\": \""
				+ tableName
				+ "\",                            \"range\": \"\"                        }                    ]                }            }        ]    ]}";
	}

	public static String createJSONToChangeTableData(final String tableName, final String nonDataDimensionName,
			final String count, final String insertAt) {

		return "{  \r\n" + "   \"batch-request\":[  \r\n" + "      {  \r\n" + "         \"version\":\"1.0\",\r\n"
				+ "         \"options\":{  \r\n" + "            \"force\":true\r\n" + "         }\r\n" + "      },\r\n"
				+ "      [  \r\n" + "         {  \r\n" + "            \"api-id\":\"table.structure.insert\",\r\n"
				+ "            \"api-version\":\"1.0\"\r\n" + "         },\r\n" + "         {  \r\n"
				+ "            \"data\":{  \r\n" + "               \"tables\":{  \r\n" + "                  \""
				+ tableName + "\":{  \r\n" + "                     \"" + nonDataDimensionName + "\":{  \r\n"
				+ "                        \"itemCount\":" + count + ",\r\n" + "                        \"insertAt\":"
				+ insertAt + "}\r\n" + "                  }\r\n" + "               }\r\n" + "            }\r\n"
				+ "         }\r\n" + "      ]\r\n" + "   ]\r\n" + "}";
	}
}