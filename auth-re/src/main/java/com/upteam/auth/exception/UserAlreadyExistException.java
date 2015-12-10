package com.upteam.auth.exception;

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
}
