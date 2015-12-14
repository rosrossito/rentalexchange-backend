package com.upteam.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Влад on 09.12.2015.
 */
public class UserAlreadyExistException extends Exception {
    private String reason;


    public UserAlreadyExistException() {
        this.reason = "User already exist";
    }

    @Override
    public String getMessage() {
        return reason;
    }

    public void setMessage(String reason) {
        this.reason = reason;
    }

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
