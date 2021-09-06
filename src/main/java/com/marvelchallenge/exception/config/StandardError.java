package com.marvelchallenge.exception.config;

import com.marvelchallenge.exception.ExceptionCodes;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 5934463695493609766L;

    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private ExceptionCodes exceptionCode;

    public StandardError() {

    }

    public StandardError(String timestamp, Integer status, String error, String message
            , String path,ExceptionCodes ExceptionCode) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.exceptionCode = ExceptionCode;
    }



	public ExceptionCodes getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(ExceptionCodes exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
