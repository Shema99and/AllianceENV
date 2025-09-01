package com.zifo.ewb.alliance.mailcontent;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.zifo.ewb.alliance.utils.ServiceBase;

/**
 * this class is used 
 * @author zifo
 *
 */
public class MailAPI extends ServiceBase {
	
	/**
	 * field logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MailAPI.class);
	

	
	public static String getNewMailbody(final String details) {
		String body="";
//		String userFolderDetails = getTableDetails(userDetails);

		final String start = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<body>";
		
		final String end="</body>\r\n" + 
				"</html>\r\n";

		body = start +details+end;
		return body ;
	}
	/**
	 * method to create mail content
	 * @param mailMap @param from 
	 * @param to @param subject@param body
	 * @return
	 */
	public static String createMailContent(final Map<String, String> mailMap) {
		LOGGER.info("Creating Mail Object");
		final MailPOJO mailPOJO = new MailPOJO();
		if(LOGGER.isInfoEnabled()) {
//			LOGGER.info("Mail Body : "+mailMap.get("body"));
			LOGGER.info("Mail Body: {}", mailMap.get("body"));
		}

		mailPOJO.setFrom(mailMap.get("from"));
		mailPOJO.setTo(mailMap.get("to"));
		mailPOJO.setSubject(mailMap.get("subject"));
		mailPOJO.setHtmlBody(mailMap.get("body"));

		final Gson gson = new Gson();
		
		final String mailDetails = gson.toJson(mailPOJO);
		
		if(LOGGER.isInfoEnabled()) {
//		LOGGER.info("Input for Mail ==>" + mailDetails);
		LOGGER.info("Input for Mail ==> {}", mailDetails);
		}
		return mailDetails;
	}
	/**
	 * this method is used to send Mail
	 * @param mailDetails
	 * @return
	 */
	public static String sendMail(final String mailDetails) {

		String response;
		try {
			final HttpPost request = new HttpPost(ServiceBase.getMailUrl());
			final HttpEntity entity = new StringEntity(mailDetails,"UTF-8");
			
			request.setEntity(entity);
			request.addHeader("APIKey", ServiceBase.getApiKey());
			request.addHeader("Accept","application/json");
			request.addHeader("Content-Type","application/json");
			
			final HttpClient client = new DefaultHttpClient();
			final HttpResponse httpResponse = client.execute(request);
			response = HttpResponseUtils.response(httpResponse);
			if(LOGGER.isInfoEnabled()) {
//			LOGGER.info("mail repsonse: " + response);
			LOGGER.info("Mail response: {}", response);
			}

		} catch (ParseException | IOException exception) {
			response = StringUtils.EMPTY;
		}

		return response;

	}
	
	
	/**
	 * getMailbody is used to return complete html body
	 * @param details
	 * @param userDetails 
	 * @param duplicateInfo 
	 * @return
	 */
	public static String getMailbody(final String details, Map<String, String> userDetails) {
		String body="";
		String userFolderDetails = getTableDetails(userDetails);
		final String start="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<style>\r\n" + 
				"table, th, td {\r\n" + 
				"  border:1px solid black;\r\n" + 
				"  border-collapse:collapse; \r\n" + 
				"  width:60%;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"<body>";
		
		final String end="</body>\r\n" + 
				"</html>\r\n";
		body = start +details+"\r\n"+userFolderDetails+end;
		return body ;
	}
	
	
	public static String getTableDetails(final Map<String, String> userDetails) {
		String tableData = StringUtils.EMPTY;
		String startTag = StringUtils.EMPTY;
		String endtag = StringUtils.EMPTY;
		String tableRows = StringUtils.EMPTY;
		int count = 0;
		
		for(Map.Entry<String, String> rowData:userDetails.entrySet()) {
			if(count==0) {
				startTag = "<table>\r\n";
				endtag = "</table>";
				tableRows =" <tr>\r\n" + 
						"    <th>"+"UserName"+"</th>\r\n" + 
						"    <th>"+"MudId"+"</th>\r\n" + 
						"  </tr>"; 
				count++;
			}
			tableRows = tableRows+" <tr>\r\n" + 
					"    <td>"+rowData.getValue()+"</td>\r\n" + 
					"    <td>"+rowData.getKey()+"</td>\r\n" + 
					"  </tr>";
		}
		tableData = startTag+tableRows+endtag;
		return tableData;
	}

}
