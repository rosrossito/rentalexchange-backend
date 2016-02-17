package com.upteam.auth.exception;

/**
 * Created by Kostik on 17.02.2016.
 */
public class UserAlreadyRegisteredException extends RuntimeException {
    @Override
    public String getMessage() {
        return "UserAlreadyRegistered";
    }
}
