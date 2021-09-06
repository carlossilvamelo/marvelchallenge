package com.marvelchallenge.exception.config;


import com.marvelchallenge.exception.ExceptionCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class StandarRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8409217189345844434L;

    private HttpStatus status;
    private ExceptionCodes exceptionCode;
    
    public StandarRuntimeException(String msg, HttpStatus status, ExceptionCodes exceptionCode) {
        super(msg);
        this.status = status;
        this.exceptionCode = exceptionCode;
        log.error(String.format("%s: %s", exceptionCode.name(), msg));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

	public ExceptionCodes getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(ExceptionCodes exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

    

}
