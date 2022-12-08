package com.sanedge.newspaginate.exception;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;
    private int numberOfErrors;

    private List<ApiSubError> subError;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiSubError> getSubError() {
        return subError;
    }

    public void setSubError(List<ApiSubError> subError) {
        this.subError = subError;
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(int numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }
}
