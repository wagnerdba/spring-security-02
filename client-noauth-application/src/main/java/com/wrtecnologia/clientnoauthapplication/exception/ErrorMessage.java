package com.wrtecnologia.clientnoauthapplication.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "statusCode", "message" })
public class ErrorMessage {

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    public ErrorMessage(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
