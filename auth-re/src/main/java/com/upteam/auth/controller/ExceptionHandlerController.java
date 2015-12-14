package com.upteam.auth.controller;


import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.exception.InvalidConfirmRegistrationLinkException;
import com.upteam.auth.vo.ErrorResponseValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseValueObject bedRequestHandler(Exception e) {
        ErrorResponseValueObject responseValueObject = new ErrorResponseValueObject();
        responseValueObject.setReason(e.getMessage());
        responseValueObject.setTimeStamp(LocalDate.now());
        return responseValueObject;
    }

    @ExceptionHandler(InvalidConfirmRegistrationLinkException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject invalidConfirmRegistrationLink(InvalidConfirmRegistrationLinkException e) {
        ErrorResponseValueObject responseValueObject = new ErrorResponseValueObject();
        responseValueObject.setReason(e.getMessage());
        responseValueObject.setTimeStamp(LocalDate.now());
        return responseValueObject;
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject userAreadyExist(UserAlreadyExistException e) {
        ErrorResponseValueObject responseValueObject = new ErrorResponseValueObject();
        responseValueObject.setReason(e.getMessage());
        responseValueObject.setTimeStamp(LocalDate.now());
        return responseValueObject;
    }

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
