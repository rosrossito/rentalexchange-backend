package com.upteam.auth.exception;

/**
 * Created by olegls2000 on 12/16/2015.
 */
public class SystemUserProblemException extends RuntimeException {
    @Override
    public String getMessage() {
        return "SystemUserProblem";
    }
}
