package com.upteam.auth.controller;

import com.upteam.auth.exception.InvalidConfirmRegistrationLinkException;
import com.upteam.auth.exception.UserAlreadyExistException;
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

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseValueObject bedRequestHandler(Exception e) {
        ErrorResponseValueObject result = new ErrorResponseValueObject();
        result.setReason(e.getClass().getName());
        result.setTimeStamp(LocalDate.now());
        return result;
    }

    @ExceptionHandler(InvalidConfirmRegistrationLinkException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject invalidConfirmRegistrationLink(InvalidConfirmRegistrationLinkException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject userAreadyExist(UserAlreadyExistException e) {
        return getErrorVO(e);
    }

    private ErrorResponseValueObject getErrorVO (Throwable throwable ){
        ErrorResponseValueObject result = new ErrorResponseValueObject();
        result.setReason(throwable.getMessage());
        result.setTimeStamp(LocalDate.now());
        return result;
    }

}
