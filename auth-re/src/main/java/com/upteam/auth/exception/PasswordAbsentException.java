package com.upteam.auth.exception;

/**
 * Created by Kostik on 21.01.2016.
 */
public class PasswordAbsentException extends RuntimeException{
    @Override
    public String getMessage() {
        return "InvalidPassword";
    }
}
