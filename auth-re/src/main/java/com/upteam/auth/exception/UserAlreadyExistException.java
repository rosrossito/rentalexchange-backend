package com.upteam.auth.exception;

/**
 * Created by Влад on 09.12.2015.
 */
public class UserAlreadyExistException  extends RuntimeException {

    @Override
    public String getMessage() {
        return "UserAlreadyExist";
    }

}
