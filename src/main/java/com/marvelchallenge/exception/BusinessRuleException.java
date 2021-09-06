package com.marvelchallenge.exception;

import com.marvelchallenge.exception.config.StandarRuntimeException;
import org.springframework.http.HttpStatus;

public class BusinessRuleException extends StandarRuntimeException {

	private static final long serialVersionUID = 6457974739559236468L;
	private static final String MSG = "Algo ocoreu errado com a aplicação, consulte o suporte técnico";
	private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public BusinessRuleException(String msg, HttpStatus status) {
		super(msg, status, ExceptionCodes.BUSINESS_ERROR);
	}

	public BusinessRuleException() {
		super(MSG, STATUS, ExceptionCodes.BUSINESS_ERROR);
	}

	public BusinessRuleException(String msg) {
		super(msg, STATUS, ExceptionCodes.BUSINESS_ERROR);
	}

}
