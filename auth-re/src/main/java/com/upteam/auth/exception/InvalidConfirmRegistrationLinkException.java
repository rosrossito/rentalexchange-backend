package com.upteam.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Skirdovs on 10.12.2015.
 */
public class InvalidConfirmRegistrationLinkException extends RuntimeException {
    private String message;


    public InvalidConfirmRegistrationLinkException() {
        this.message = "fail registration - invalid link for activation(expired or unexist)";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
