package com.upteam.auth.exception;

/**
 * Created by Skirdovs on 10.12.2015.
 */
public class InvalidConfirmRegistrationLinkException extends Exception{
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
}
