package com.zifo.ewb.alliance.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Constants of the application
 * 
 * @author Zifo
 */

public final class AppConstantsUtils {
	public static final String FORMAT = "dd-MMM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_SSS = "yyyy-MM-dd'T'hh:mm:ss.SSS";
	public static final String DATE_FORMAT_SS = "yyyy-MM-dd'T'hh:mm:ss";
	public static final String JSONREQUEST = "'{'\"batch-request\": ['{'\"version\": \"1.0\"},['{'\"api-id\": \"table.data\",\"api-version\": \"1.0\"},"
			+ "'{'\"data\": '{'\"queries\": ['{'\"table\": \"{0}\",\"range\": \"\"}]}}]]}";
	public static final String SPREADSHEETDATA = "/spreadsheet/data";
	public static final String LOAD = "/spreadsheet";
	public static final String ERROR1 = "Resource not found for given ID: Spreadsheet is not loaded";
	public static final String ERROR2 = "Authorization failure: The spreadsheet is not open for editing";
	public static final String ERROR3 = "Resource not found for given ID: Spreadsheet entity not found";
	public static final String SPREADSHEETCLOSED = "Spreadsheet is not opened";
	public static final String AUTHORIZATIONFAIL = "Spreadsheet authorization failure";
	public static final String IDNOTFOUND = "Spreadsheet ID not found";
	public static final String TABLENOTFOUND = "Table not found";
	public static final String XLS = ".xls";
	public static final String ENTITY_VERSION_SAVED = "ENTITY_VERSION_SAVED";
	public static final String STATUS_PROPERTY_CHANGED = "STATUS_PROPERTY_CHANGED";
	public static final String CLOSED = "CURRENT_STATUS_PROPERTY=Closed";
	public static final String TWO_LAKH = "200000";
	public static final String ONE = "1";
	public static final String LAST_SERVICE_RUN_TIME = "last.rundate";
	public static final String ARCHIVAL_STATUS = "Archival Status";
	
	
	// public static final String PROPFILENAME = "lastRunDate.properties";
//	public static final String PROPFILENAME = "/opt/apache-tomcat-9.0.22/webapps/lastRunDate.properties";
	// public static final String PROPFILENAME = "/lastRunDate.properties";
	// Template constants
	public static final String KEY = "Key";
	public static final String KEY_NAME = "KeyName";
	public static final String KEY_VALUE = "KeyValue";
	protected static final List<String> TEMPLATE_HEADER = Arrays.asList("KeyName", "KeyValue", "fromSheet");
	public static final String WORKING_PROC_TABLE = "Working Processing List";
	public static final String PROCESSING_SUBMITTAL_TABLE = "NuGenesis Processing Submittal";
	public static final String TOTAL_ROW_TABLE = "Total Rows";
	public static final String TEMPLATE_TABLE = "NuGenesis Template Mapping";
	public static final String NUM_ROWS = "NumRows";
	public static final String NGS = "NGS";

	public static final String ENTITIY_TYPE_SPREADSHEET = "IDBS_SPREADSHEET";
	public static final String HTTPS = "https";
	public static final String HTTP = "http";
	public static final String SELFSIGNED = "self";
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	protected static final List<String> TOTAL_ROW_HEADER = Arrays.asList(NUM_ROWS);

	/**
	 * Has value 'DOCUMENT'
	 */
	public static final String DOCUMENT = "DOCUMENT";

	/**
	 * Has value 'EXPERIMENT'
	 */
	public static final String EXPERIMENT = "EXPERIMENT";

	/**
	 * Has value 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	 */
	public static final String EXCEL_SHEET = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static final String WORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

	/**
	 * Has value 'application/vnd.ms-excel'
	 */
	public static final String EXCEL_FILE = "application/vnd.ms-excel";

	/**
	 * Has value 'application/vnd.ms-excel'
	 */
	public static final String PDF_FILE = "application/pdf";

	public static final String OCTET_STREAM = "application/octet-stream";

	public static final String XLSX = ".xlsx";
	public static final String PDF = ".pdf";
	public static final String SPREADSHEET = ".ewbss";
	public static final String DOC = ".docx";

	public static final String PLUS_SYMBOL = "+";
	public static final String FORWARD_SLASH = "/";

	public static final String PATH = "classpath:resources/EntityIDList.csv";
	public static final int HTTP_200_STATUS = 200;

	public static final String FAILURE = "Failure";
	public static final String SUCCESS = "Success";

	public static final String REGEX = "[\\\\\\\\/:*?\\\"<>\\r|]|(\\n)|(\\r\\n)";

	public static final String UNDERSCORE = "_";
	public static final String SERVICEBASE = "/ewb/services/1.0";

	// Attributes names
	public static final String EXPERIMENTID = "Experiment ID";
	public static final String REQUESTID = "Request ID";
	public static final String TITLE = "title";
	public static final String STATUSNAME = "statusName";
	public static final String PREVEXPREF = "prevExpRef";
	public static final String SUCCEXPREF = "succExpRef";
	public static final String PROJETREF = "Project Ref";
	public static final String USER = "User";
	public static final String CREATEDFROMTEMPLATE = "createdFromTemplate";
	public static final String TIMESTAMP = "statusTimestamp";
	public static final String TEMPLATE = "TEMPLATE";

	public static final String ACTIVITYSERVICE = "/feeds/entities/";
	public static final String CACHESERVICE = "/cache/entities/";
	public static final String CATALOGSERVICE = "/catalog";
	public static final String ENTITYCONFIGSERVICE = "/entityconfig/";
	public static final String ENTITYLOCKSERVICE = "/locks/entities/";
	public static final String RECORDSERVICE = "/records/";
	public static final String SECURITYSERVICE = "/security/administration/groups/";
	public static final String TASKACTIONSERVICE = "/tasks/";
	public static final String ENTITYSERVICE = "/entities/";
	public static final String BATCH_RESPONSE = null;
	public static final String API_RESPONSES = null;
	public static final String TABLES = null;
	public static final String RANGES = null;
	public static final String DATA = null;
	public static final String CACHEENTITIES = "/cache/entities/";
	public static final String COMMIT = "/commit";
	/** Field "/security/administration/users" */
	public static final String SECURITY_USERS ="/security/administration/users";
	/** Field "userName" */
	public static final String USER_NAME = "userName";
	/** Field "userlist" */
	public static final String USER_LIST = "userlist";
	public static final String INCLUDE_TYPE = "includeType";
	/**Field Business Area*/
	public static final String BUSINESS_AREA = "BUSINESS_AREA";

	protected static final List<String> SUBMITTAL_HEADER = Arrays.asList("Process Type", "Acquisition MS Asset",
			"PC Asset", "Instrument Software Name", "Instrument Software Version", "UNC Path", "NuGenesis Queue Path",
			"Site", "Department", "Division");
	protected static final String[] PROCESSED_FILES_HEADER = { "File Type", "File Name", "Status", ARCHIVAL_STATUS,
			"Resubmission Comment", "Results Modification Comment", "UserID", "Software Name" };
	protected static final List<String> WORKING_PROC_HEADER = Arrays.asList("File Type", "File Name", "Status",
			ARCHIVAL_STATUS, "Resubmission Comment", "UserID", "Results Modification Comment", "FileSize",
			"FileModifiedDate", "Division", "Department", "Site", "Software Name", "Software Version",
			"Acquisition MS Asset");

	/**
	 * An Empty Constructor to avoid object Creation for constant class
	 */
	private AppConstantsUtils() {

	}
}
