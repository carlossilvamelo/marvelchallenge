package com.marvelchallenge.exception;

import com.marvelchallenge.exception.config.StandarRuntimeException;
import org.springframework.http.HttpStatus;

public class DataTransationalException extends StandarRuntimeException {

	private static final long serialVersionUID = -2795256688699473099L;

	private static final String MSG = "Problemas de banco de dados, consultar suporte técnico.";
	private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	public DataTransationalException() {
		super(MSG, STATUS, ExceptionCodes.DATA_LAYER_ERROR);
	}

	public DataTransationalException(String msg) {
		super(msg, STATUS, ExceptionCodes.DATA_LAYER_ERROR);
	}

}
