package com.marvelchallenge.exception;

import com.marvelchallenge.exception.config.StandarRuntimeException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends StandarRuntimeException {

    private static final long serialVersionUID = -3475370540432322043L;
    private static final String MSG = "Recurso não existente.";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public ResourceNotFoundException(String msg) {
        super(msg, STATUS, ExceptionCodes.BUSINESS_ERROR);
    }

    public ResourceNotFoundException() {
    	
        super(MSG, STATUS, ExceptionCodes.BUSINESS_ERROR);
    }

}
