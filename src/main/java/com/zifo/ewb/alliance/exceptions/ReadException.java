package com.zifo.ewb.alliance.exceptions;

/**
 * Custom exception will be throw when exception occurs while reading the Json
 * 
 * @author Zifo
 * 
 */
public class ReadException extends Exception {

	private static final long serialVersionUID = 9075209121479721654L;

	/**
	 * Constructor that give exception message as parameter
	 *
	 * @param message
	 */
	public ReadException(final String message) {
		super(message);
	}
}
