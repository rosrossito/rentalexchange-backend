package com.upteam.auth.exception;

/**
 * Created by Andrew on 26.01.2016.
 */
public class InvalidPasswordFormatException extends RuntimeException {
    @Override
    public String getMessage() {
        return "InvalidPasswordFormat";
    }
}
