package com.marvelchallenge.exception;

import com.marvelchallenge.exception.config.StandarRuntimeException;
import org.springframework.http.HttpStatus;

public class MandatoryFieldsException extends StandarRuntimeException {

	private static final long serialVersionUID = -1591914980233704600L;
	private static final String MSG = "Faltam campos obrigatórios";
	private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	public MandatoryFieldsException() {
		super(MSG, STATUS, ExceptionCodes.MANDATORY_FIELDS);
	}

	public MandatoryFieldsException(String msg) {
		super(msg, STATUS, ExceptionCodes.MANDATORY_FIELDS);
	}

}
