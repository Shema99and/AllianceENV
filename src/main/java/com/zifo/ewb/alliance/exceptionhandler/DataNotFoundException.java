package com.zifo.ewb.alliance.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	public DataNotFoundException(final String exception, HttpStatus status) {
		super(exception);
		this.status = status;
	}

}
