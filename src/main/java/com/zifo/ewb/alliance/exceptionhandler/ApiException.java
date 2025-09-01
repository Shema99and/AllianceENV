package com.zifo.ewb.alliance.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7633352480352928607L;
	final String message;
	final HttpStatus code;
	final Object data;

	public ApiException(String message) {
		this.message = message;
		this.code = HttpStatus.INTERNAL_SERVER_ERROR;
		this.data = null;
	}

	public ApiException(String message, HttpStatus code) {
		this.message = message;
		this.code = code;
		this.data = null;
	}

	public ApiException(String message, HttpStatus code, Object data) {
		this.message = message;
		this.code = code;
		this.data = data;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return ("Exception Occurred : " + this.message);
	}
}