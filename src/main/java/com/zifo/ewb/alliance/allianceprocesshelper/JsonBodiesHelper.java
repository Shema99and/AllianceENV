package com.zifo.ewb.alliance.allianceprocesshelper;

/**
 * this Class is used to maintain 
 * method which create different json bodies
 * @author Zifo
 */
public final class JsonBodiesHelper {
	
	/**
	 * method to create user creation bodies	
	 * @param user
	 * @return
	 */
	public static String getUserCreationBodies(final String user) {
//		return "{\r\n" + 
//				"	\"entityType\": \"USER\",\r\n" + 
//				"	\"entityName\": \"IDBS\",\r\n" + 
//				"	\"iconUrl\": \"/services/1.0/entityconfig/icons/USER/\",\r\n" + 
//				"	\"attributes\": {\r\n" + 
//				"		\"attribute\": [\r\n" + 
//				"\r\n" + 
//				"			{\r\n" + 
//				"				\"name\": \"Name\",\r\n" + 
//				"				\"values\": {\r\n" + 
//				"					\"value\": [\r\n" + 
//				"						\""+user.trim()+"\"\r\n" + 
//				"					]\r\n" + 
//				"				}\r\n" + 
//				"			},\r\n" + 
//				"			{\r\n" + 
//				"				\"name\": \"Site\",\r\n" + 
//				"				\"values\": {\r\n" + 
//				"					\"value\": [\r\n" + 
//				"						\"Stevenage\"\r\n" + 
//				"					]\r\n" + 
//				"				}\r\n" + 
//				"			}\r\n" + 
//				"		]\r\n" + 
//				"	}\r\n" + 
//				"}\r\n" + 
//				"";
		String jsonTemplate = "{\"entityType\":\"USER\",\"entityName\":\"IDBS\",\"iconUrl\":\"/services/1.0/entityconfig/icons/USER/\",\"attributes\":{\"attribute\":[{\"name\":\"Name\",\"values\":{\"value\":[\"%s\"]}},{\"name\":\"Site\",\"values\":{\"value\":[\"Stevenage\"]}}]}}";
	    return String.format(jsonTemplate, user.trim());
	}
	private JsonBodiesHelper() {
		
	}
}
