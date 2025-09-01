package com.zifo.ewb.alliance.webserviceutils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zifo.ewb.alliance.constants.JSONConstants;
import com.zifo.ewb.alliance.exceptions.ReadException;

/**
 * Utility class provide common JSON related functions
 * 
 * @author zifo
 *
 */
public final class JSONUtils {

	/**
	 * 
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * Logger instance to write logs
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class.getName());

	/**
	 * Private constructor to prevent object creation for utility class.
	 */
	private JSONUtils() {

	}

	/**
	 * This method convert object to string
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String convertToString(final Object object) {
		String value;
		try {
			value = MAPPER.writeValueAsString(object);
		} catch (final IOException ioExcep) {
			value = StringUtils.EMPTY;
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info(ioExcep.getMessage());
			}
		}
		return value;

	}

	/**
	 * This method convert string to JsonNode
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static JsonNode convertToJson(final String data) {

		JsonNode node = null;
		try {
			node = MAPPER.readTree(data);
		} catch (IOException e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info(e.getMessage());
			}
		}
//		} catch (final IOException e) {
//			if (LOGGER.isInfoEnabled()) {
//				LOGGER.info(e.getMessage());
//			}
//		}
		return node;
	}

	/**
	 * @param value
	 * @return
	 */
	public static String checkForNull(final String value) {
		String checkedValue;
		if (value == null) {
			checkedValue = StringUtils.EMPTY;
		} else {
			checkedValue = value;
		}
		return checkedValue;
	}

	/**
	 * @param value
	 * @return
	 */
	public static int checkForNullOnInt(final String value) {
		int checkedValue;
//		if (value == null || value == StringUtils.EMPTY) {
		if (value == null || StringUtils.EMPTY.equals(value)) {
			checkedValue = 0;
		} else {
			checkedValue = Integer.valueOf(value);
		}
		return checkedValue;
	}

	/**
	 * Method that read data from json node
	 * 
	 * @param node
	 * @param value
	 * @return
	 * @throws ReadException
	 * @throws ErrorException
	 */
	public static String readJson(final JsonNode node, final String value) throws ReadException {

		JsonNode path;
		path = node.path(value);
		String textValue;
//		if (path.path(JSONConstants.ERROR).isMissingNode() == Boolean.FALSE) {
//			throw new ReadException("Please provide valid" + value + " value");
//		} else if (path.path(JSONConstants.NUMBER).isMissingNode() == Boolean.FALSE) {
//			textValue = path.path(JSONConstants.NUMBER).asText();
//		} else if (path.path(JSONConstants.HYPERLINK).isMissingNode() == Boolean.FALSE) {
//			textValue = path.path(JSONConstants.HYPERLINK).asText();
//		} else if (path.path(JSONConstants.STRING).isMissingNode() == Boolean.FALSE) {
//			textValue = path.path(JSONConstants.STRING).asText();
//		} else {

		if (!path.path(JSONConstants.ERROR).isMissingNode()) {
		    throw new ReadException("Please provide valid " + value + " value");
		} else if (!path.path(JSONConstants.NUMBER).isMissingNode()) {
		    textValue = path.path(JSONConstants.NUMBER).asText();
		} else if (!path.path(JSONConstants.HYPERLINK).isMissingNode()) {
		    textValue = path.path(JSONConstants.HYPERLINK).asText();
		} else if (!path.path(JSONConstants.STRING).isMissingNode()) {
		    textValue = path.path(JSONConstants.STRING).asText();
		} else {

			textValue = StringUtils.EMPTY;
		}

		return textValue;

	}

	public static Boolean checkJson(JsonNode dataIterator, String columnName) {
		return dataIterator.path(columnName).isMissingNode();
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String getString(final Object obj) {
		return obj == null ? StringUtils.EMPTY : obj.toString();
	}
}
