package com.zifo.ewb.alliance.webserviceutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zifo.ewb.alliance.constants.AppConstantsUtils;
import com.zifo.ewb.alliance.constants.JsonConstantsUtils;

import lombok.NoArgsConstructor;

/**
 * Class that analyzing status from public API
 * 
 * @author Zifo
 * 
 */
@NoArgsConstructor
public class WebServiceStatus {

	/**
	 * Status code of the response
	 */
	private Integer statusCode;

	/**
	 * List of error messages
	 */
	private List<String> errorMessages = new ArrayList<>();

	/**
	 * Method that validating webservice output response
	 * 
	 * @param response
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	public static WebServiceStatus webServiceStatusValidator(final String response) throws JsonProcessingException, IOException {
		final WebServiceStatus serviceStatus = new WebServiceStatus();
		serviceStatus.setStatusCode(200);
		if (response.equalsIgnoreCase(AppConstantsUtils.ERROR1)) {
			serviceStatus.setStatusCode(201);
			serviceStatus.errorMessages.add(AppConstantsUtils.SPREADSHEETCLOSED);
		} else if (response.equalsIgnoreCase(AppConstantsUtils.ERROR2)) {
			serviceStatus.setStatusCode(203);
			serviceStatus.errorMessages.add(AppConstantsUtils.AUTHORIZATIONFAIL);
		} else if (response.equalsIgnoreCase(AppConstantsUtils.ERROR3)) {
			serviceStatus.setStatusCode(202);
			serviceStatus.errorMessages.add(AppConstantsUtils.IDNOTFOUND);
		}

		else {

			webServiceStatus(response, serviceStatus);
		}
		return serviceStatus;

	}

	/**
	 * Status of the webservice
	 * 
	 * @param response
	 * @param serviceStatus
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static void webServiceStatus(final String response, final WebServiceStatus serviceStatus) throws JsonProcessingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final JsonNode updateTableDataJson = mapper.readTree(response);
		final JsonNode batchResponse = updateTableDataJson.path(JsonConstantsUtils.BATCH_RESPONSE);
		final JsonNode status = batchResponse.path(JsonConstantsUtils.STATUS);
		final JsonNode success = status.path(JsonConstantsUtils.SUCCESS);

		if (success.isMissingNode() == Boolean.FALSE && success.booleanValue() == Boolean.FALSE) {
			serviceStatus.statusCode = status.path(JsonConstantsUtils.STATUS_CODE).intValue();

			if (HttpStatus.SC_NOT_FOUND == serviceStatus.statusCode) {
				serviceStatusTrack(serviceStatus, status);
			} else {
				final JsonNode statusMessages = status.path(JsonConstantsUtils.MESSAGES);
				if (statusMessages != null && statusMessages.isArray()) {
					for (final JsonNode message : statusMessages) {
						serviceStatus.setStatusCode(404);
						serviceStatus.errorMessages.add(message.asText());
					}
				}
			}
		}

	}

	/**
	 * Validates webservice response
	 * 
	 * @param serviceStatus
	 * @param status
	 */
	public static void serviceStatusTrack(final WebServiceStatus serviceStatus, final JsonNode status) {
		final JsonNode statusMessages = status.path(JsonConstantsUtils.MESSAGES);
		if (statusMessages != null && statusMessages.isArray()) {
			for (final JsonNode message : statusMessages) {
				if (message.textValue().contains(AppConstantsUtils.TABLENOTFOUND)) {
					serviceStatus.setStatusCode(404);
					serviceStatus.errorMessages.add(AppConstantsUtils.TABLENOTFOUND);

				}

			}
		}
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final Integer statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(final List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
