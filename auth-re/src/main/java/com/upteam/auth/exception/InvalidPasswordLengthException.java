package com.upteam.auth.exception;

/**
 * Created by Администратор on 08.03.2016.
 */
public class InvalidPasswordLengthException extends RuntimeException {
    @Override
    public String getMessage() {
        return "InvalidPasswordLength";
    }
}
