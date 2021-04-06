package com.microserviceproject.services.quote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoQuoteExistException extends RuntimeException {

    /**
     * This is the constructor for the custom exception class.
     * The exception is for a client that doesn't exist in the system
     * @param message
     */
    public NoQuoteExistException(String message) {
        super(message);
    }
}

