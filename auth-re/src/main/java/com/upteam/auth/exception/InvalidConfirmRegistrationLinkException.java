package com.upteam.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Skirdovs on 10.12.2015.
 */
public class InvalidConfirmRegistrationLinkException extends RuntimeException {

    @Override
    public String getMessage() {
        return "InvalidConfirmRegistrationLink";
    }

}
