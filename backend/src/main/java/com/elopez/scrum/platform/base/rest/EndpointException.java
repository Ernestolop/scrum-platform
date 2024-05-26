package com.elopez.scrum.platform.base.rest;

import org.springframework.http.HttpStatus;

public class EndpointException extends RuntimeException {

    private int code = 0;
    private String message = "unknown";
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public EndpointException(int code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}