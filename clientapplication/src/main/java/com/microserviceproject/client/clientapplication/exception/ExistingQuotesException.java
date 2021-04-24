package com.microserviceproject.client.clientapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to when quote exist.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistingQuotesException extends RuntimeException {

    /**
     * Exception to be raised if quotes exist for the customer in question.
     * @param message String Message for the Runtime Exception.
     */
    public ExistingQuotesException(String message) {
        super(message);
    }
}
