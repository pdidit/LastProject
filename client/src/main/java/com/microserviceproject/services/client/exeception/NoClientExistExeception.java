package com.microserviceproject.services.client.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoClientExistExeception extends RuntimeException{

    /**
     * This is the constructor for the custom exception class.
     * The exception is for a client that doesn't exist in the system
     * @param message
     */
    public NoClientExistExeception(String message) {
        super(message);
    }
}
