package com.upteam.auth.exception;

/**
 * Created by Администратор on 22.12.2015.
 */
public class EmailIsAbsentException extends RuntimeException {
    @Override
    public String getMessage() {
        return "InvalidEmail";
    }
}
