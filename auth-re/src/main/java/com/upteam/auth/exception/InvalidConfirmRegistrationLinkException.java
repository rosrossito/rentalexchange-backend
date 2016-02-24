package com.upteam.auth.exception;

/**
 * Created by Skirdovs on 10.12.2015.
 */
public class InvalidConfirmRegistrationLinkException extends RuntimeException {

    @Override
    public String getMessage() {
        return "InvalidActivationLink";
    }

}
