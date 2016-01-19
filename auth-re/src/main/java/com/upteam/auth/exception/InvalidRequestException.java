package com.upteam.auth.exception;

/**
 * Created by vnikolaev on 17.01.2016.
 */
public class InvalidRequestException extends RuntimeException {
    @Override
    public String getMessage() {
        return "InvalidRequest";
    }

}
