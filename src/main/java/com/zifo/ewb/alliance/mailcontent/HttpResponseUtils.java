package com.zifo.ewb.alliance.mailcontent;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.constants.ServiceConstants;

/**
 * @author zifo
 */
public final class HttpResponseUtils {
	/**field logger*/
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponseUtils.class.getName());
	/**
	 * method to evaluate response
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public static String response(final HttpResponse response) throws ParseException, IOException {
		String finalResponse = StringUtils.EMPTY;
		final int statusCode = response.getStatusLine().getStatusCode();
		if(LOGGER.isInfoEnabled()) {
//		LOGGER.info("Status for the call: " + statusCode);
		LOGGER.info("Status for the call: {}", statusCode);
		}
		if (statusCode == ServiceConstants.FIVEHUNDRED) {
			finalResponse = String.valueOf(statusCode);
			LOGGER.info(finalResponse);

		} else if (statusCode == ServiceConstants.FOURHUNDRED) {
			finalResponse = String.valueOf(statusCode);
			LOGGER.info(finalResponse);

		} else if (statusCode == ServiceConstants.FOURZEROONE) {
			finalResponse = String.valueOf(statusCode);
			LOGGER.info(finalResponse);

		} else if (statusCode == ServiceConstants.FOURZEROFOUR) {
			finalResponse = String.valueOf(statusCode);
			LOGGER.info(finalResponse);

		} else if (statusCode == ServiceConstants.FOURZEROTHREE) {
			finalResponse = String.valueOf(statusCode);
			LOGGER.info(finalResponse);

		} else if (statusCode == ServiceConstants.TWOHUNDRED) {
			finalResponse = EntityUtils.toString(response.getEntity());

		}
		return finalResponse;
	}
private HttpResponseUtils() {
	
}
}
