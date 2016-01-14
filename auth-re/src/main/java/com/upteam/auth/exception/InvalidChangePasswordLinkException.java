package com.upteam.auth.exception;

/**
 * Created by Andrew on 12.01.2016.
 */
public class InvalidChangePasswordLinkException extends RuntimeException {

    @Override
    public String getMessage() {
        return "InvalidChangePasswordLink";
    }
}
