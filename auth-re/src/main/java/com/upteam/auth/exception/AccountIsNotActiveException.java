package com.upteam.auth.exception;

/**
 * Created by Администратор on 21.12.2015.
 */
public class AccountIsNotActiveException extends RuntimeException{

    @Override
    public String getMessage() {
        return "NonActiveAccount";
    }

}
