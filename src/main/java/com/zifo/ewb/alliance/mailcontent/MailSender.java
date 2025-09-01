package com.zifo.ewb.alliance.mailcontent;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zifo.ewb.alliance.utils.ServiceBase;



public class MailSender {
	private MailSender() {
	}
	
	/**
	 * Field LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class.getName());
	
	public static void sendMail(String htmlbody){
//	 throws IOException, KeyManagementException, NoSuchAlgorithmException, CertificateException, URISyntaxException, KeyStoreException, 
		String [] mailIdList = ServiceBase.getTo().split(",");
		
		for (String mail : mailIdList) {
			final Map<String, String> mailMap = new LinkedHashMap<>();
			mailMap.put("from", ServiceBase.getFrom());
			mailMap.put("to", mail);
			mailMap.put("subject", ServiceBase.getSubject());
			mailMap.put("body", htmlbody);
			final String mailContent = MailAPI.createMailContent(mailMap);
//			LOGGER.info("Mail Content :" + mailContent);
			LOGGER.info("Mail Content: {}", mailContent);
			MailAPI.sendMail(mailContent);
		}
		}
	
	public static void sendMailAlliance(String htmlbody,String allianceName){
//	 throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, URISyntaxException {
		
		String [] mailIdList = ServiceBase.getTo().split(",");
		
		for (String mail : mailIdList) {
			final Map<String, String> mailMap = new LinkedHashMap<>();
			mailMap.put("from", ServiceBase.getFrom());
			mailMap.put("to", mail);
			mailMap.put("subject", ServiceBase.getSubject());
			mailMap.put("body", htmlbody);
			final String mailContent = MailAPI.createMailContent(mailMap);
//			LOGGER.info("Mail Content :" + mailContent);
			LOGGER.info("Mail Content: {}", mailContent);
			MailAPI.sendMail(mailContent);
		}
	}
	
//	public static void sendMailToAdmins(String htmlbody) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException {
//		final String admins = "dm541713,vmb61811,ss339177,ssz33936";
//		SecurityService  securityService =  new SecurityService();
//		final List<String> adminList = Arrays.asList(admins.split(","));
//		final List<String> emailList = securityService.getMailId(adminList);
//		for(String emailId:emailList) {
//		final Map<String, String> mailMap = new LinkedHashMap<>();
//			mailMap.put("from", ConfigResources.getFrom());
//			mailMap.put("to", emailId);
//			mailMap.put("subject", ConfigResources.getSubject());
//			mailMap.put("body", htmlbody);
//			final String mailContent = MailAPI.createMailContent(mailMap);
//			LOGGER.info("Mail Content :" + mailContent);
//			MailAPI.sendMail(mailContent);
//		}
//	}
	
}
