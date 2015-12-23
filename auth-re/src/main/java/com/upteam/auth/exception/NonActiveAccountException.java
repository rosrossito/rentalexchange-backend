package com.upteam.auth.exception;

/**
 * Created by Влад on 23.12.2015.
 */
public class NonActiveAccountException extends RuntimeException {
    @Override
    public String getMessage() {
        return "NonActiveAccount";
    }
}
