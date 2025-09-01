package com.zifo.ewb.alliance.mailcontent;

/**
 * this class is used to set mail 
 * content
 * @author zifo
 *
 */
public class MailPOJO {

	/**Field from*/
	private String from;
	/**Field to*/
	private String to;
	/**Field subject*/
	private String subject;
	/**Field html body*/
	private String htmlBody;

	public String getFrom() {
		return from;
	}

	public void setFrom(final String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(final String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getHtmlBody() {
		return htmlBody;
	}

	public void setHtmlBody(final String htmlBody) {
		this.htmlBody = htmlBody;
	}
	/**
	 * class constructor
	 */
	public MailPOJO() {
		/**
		 * empty constructor
		 */
	}

}
