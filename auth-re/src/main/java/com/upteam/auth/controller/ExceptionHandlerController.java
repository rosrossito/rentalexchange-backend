package com.upteam.auth.controller;

import com.upteam.auth.exception.CustomException;
import com.upteam.auth.vo.ErrorResponseValueObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

/**
 * Created by opasichnyk on 12/8/2015.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject bedRequestHandler(CustomException e) {
        ErrorResponseValueObject responseValueObject = new ErrorResponseValueObject();
        responseValueObject.setReason(e.getMessage());
        responseValueObject.setTimeStamp(LocalDate.now());
        return responseValueObject;
    }

}
