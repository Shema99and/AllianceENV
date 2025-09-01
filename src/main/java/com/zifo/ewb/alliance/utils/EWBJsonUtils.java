package com.zifo.ewb.alliance.utils;

public class EWBJsonUtils {
	private EWBJsonUtils() {
	}

	
	public static String draftSaveBody(String experimentEntityId) {
//		String body = "{" + "\"" + "author" + "\"" + ':' + "{" + "\"" + "reason" + "\"" + ':' + "\"" + "..." + "\""
//				+ ',' + "\"" + "additionalComment" + "\"" + ':' + "\"" + "..." + "\"" + "}," + "\"" + "witness" + "\""
//				+ ':' + "{" + "\"" + "reason" + "\"" + ':' + "\"" + "..." + "\"" + ',' + "\"" + "additionalComment"
//				+ "\"" + ':' + "\"" + "..." + "\"" + "}," + "\"" + "entityId" + "\"" + ':' + "\"" + experimentEntityId + "\""
//				+ ',' + "\"" + "entityVersionType" + "\"" + ':' + "\"" + "DRAFT" + "\"" + "}";
		return "{" + "\"" + "author" + "\"" + ':' + "{" + "\"" + "reason" + "\"" + ':' + "\"" + "..." + "\""
				+ ',' + "\"" + "additionalComment" + "\"" + ':' + "\"" + "..." + "\"" + "}," + "\"" + "witness" + "\""
				+ ':' + "{" + "\"" + "reason" + "\"" + ':' + "\"" + "..." + "\"" + ',' + "\"" + "additionalComment"
				+ "\"" + ':' + "\"" + "..." + "\"" + "}," + "\"" + "entityId" + "\"" + ':' + "\"" + experimentEntityId + "\""
				+ ',' + "\"" + "entityVersionType" + "\"" + ':' + "\"" + "DRAFT" + "\"" + "}";
	}
}
