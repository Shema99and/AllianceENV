package com.zifo.ewb.alliance.constants;


/**
 * Json constants are present in this class
 *
 * @author zifo
 *
 */
public final class JSONConstants {
	private JSONConstants() {
	}

	public static final String DATA = "data";

	public static final String VALUE = "value";

	public static final String ODATA_CONTEXT = "@odata.context";

	public static final String RANGES = "ranges";

	public static final String API_RESPONSES = "api-responses";

	public static final String BATCH_RESPONSE = "batch-response";

	public static final String EMPTY_STRING = "";

	public static final int EMPTY_NUMBER = 0;

	public static final String COMMA = ",";

	public static final String SINGLE_QUOTE = "'";

	public static final String API_ID = "api-id";

	public static final String API_ID_VALUE = "table.data.set";

	public static final String API_VERSION = "api-version";

	public static final String API_VERSION_VALUE = "1.0";

	public static final String STRING = "string";

	public static final String NUMBER = "number";

	public static final String DATE_TIME = "datetime";

	public static final String INDEX = "index";

	public static final String TABLES = "tables";

	public static final String SUCCESS = "success";

	public static final String STATUS = "status";

	public static final String STATUS_CODE = "statusCode";

	public static final String HYPERLINK = "hyperlink";

	public static final String ERROR = "error";

	public static final String NAME = "name";

	public static final String BATCH_REQUEST = "batch-request";

	public static final String VERSION = "version";

	public static final String ACCESS_TOKEN = "access_token";

	public static final String DATA_DIMENSION = "dataDimensionName";

	public static final String DIMENSIONS = "dimensions";

	public static final String ITEM_NAMES = "itemNames";

	public static final String VERSION_ID = "versionId";

	public static final String GET_DATA_REQ_BEGIN = "{\n    \"batch-request\": [\n        { \"version\": \"1.0\" },\n        [\n            {\n                \"api-id\": \"table.data\",\n                \"api-version\": \"1.0\"\n            },\n            {\n               \"data\": {\t\n    \"queries\": [\n        {\n            \"table\": \"";

	public static final String GET_DATA_REQ_END = "\",\n            \"range\": \"\" \n        }\n      \n    ]\n}\n            }\n]]}";

	public static final String CLEAR_DATA_REQ_BEGIN = "{\r\n    \"batch-request\": [\r\n        {\r\n            \"version\": \"1.0\"\r\n        },\r\n        [\r\n            {\r\n                \"api-id\": \"table.data.clear\",\r\n                \"api-version\": \"1.0\"\r\n            },\r\n            {\r\n                \"data\": {\r\n                    \"ranges\": [\r\n                        {\r\n                            \"table\": \"";

	public static final String CLEAR_DATA_REQ_END = "\"\r\n                        }\r\n                    ]\r\n                }\r\n            }\r\n        ]\r\n    ]\r\n}\r\n\r\n\r\n";

	public static final String NOTUBEID = "No Tube Id(s) are present in ";

	public static final String HELPERTABLEMOSAICPULL = " helper table to pull tube data";

	public static final String NOTUBEINFO = "No Tube Infomation(s) are present in ";

	public static final String HELPERTABLEPUSH = " helper table to push tube data";

	public static final String MOSAIC_PUSH_TUBE_ALREADY_EXIST = "Labware Item with this barcode already exists";

	public static final String MOSAIC_PUSH_TUBE_SUCCESS = "Successfully submitted request.";

	public static final String TUBE_ALREADY_EXIST = "Tube(s) already exists";

	public static final String NOLNB = "No LNB Ref(s) are present in ";

	public static final String HELPERTABLEBIOREGPULL = " helper table to pull lot information";

	public static final String COMPOUNDIDS = "No Compound Id(s) are present in ";

	public static final String HELPERTABLEPROJECTPULL = " helper table to pull project data";

	public static final String NOSAMPLETEXTS = "No Sample text(s) are present in ";

	public static final String NOSAMPLEIDS = "No Sample id(s) are present in ";

	public static final String HELPERTABLENLSPULL = " helper table to pull NLS data";

	public static final String NOTESTS = "No Sample Infomation(s) are present in ";

	public static final String HELPERTABLENLSPUSH = " helper table to push NLS data";

	public static final int SUCCESS_CODE = 200;

	public static final int ERROR_INTL_SERVER_CODE = 500;

	public static final int ERROR_NOT_FOUND_CODE = 404;

	public static final int ERROR_UNAUTHORIZED_CODE = 401;

	public static final int ERROR_BAD_REQ_CODE = 400;

	public static final String TABLE = "table";

	public static final String NO_RECORD_ID = "No Record ID(s) are present in ";

	public static final String HELPERTABLEINVENTORYPULL = " helper table to pull item IDs";

	public static final String ITEMS = "items";

	public static final String UNIQUE_ID = "uniqueId";

	public static final String SYSTEM = "system";

	public static final String PARAM_MISSING = "Necessary parameters are Empty/InCorrect";

	public static final String NODATA = "No Data to Push to Mosaic";

	public static final String TUBES_EXIST = "Some Tubes already exist, Unique Tubes are pushed";
	
	public static final String DATEANDTIME = "datetime";
	/**
	 * @param mosaicpull
	 * @return
	 */
	public static String formEnterTableIdJson(final String mosaicpull) {
		return "{    \"batch-request\": [        {            \"version\": \"1.0\"        },        [            {                \"api-id\": \"table.data\",               \"api-version\": \"1.0\"            },            {                \"data\": {                    \"queries\": [                        {                           \"table\": \""
				+ mosaicpull
				+ "\",                            \"range\": \"\"                        }                    ]                }            }        ]    ]}";
	}

}
