package com.upteam.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Влад on 09.12.2015.
 */
public class UserAlreadyExistException extends Exception {

    @Override
    public String getMessage() {
        return "UserAlreadyExist";
    }
}
