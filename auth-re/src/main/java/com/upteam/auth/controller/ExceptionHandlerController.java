package com.upteam.auth.controller;

import com.upteam.auth.exception.*;
import com.upteam.auth.vo.ErrorResponseValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;

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
    public ErrorResponseValueObject userAlreadyExist(UserAlreadyExistException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(IncorrectLoginException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseValueObject incorrectLoginException(IncorrectLoginException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(NonActiveAccountException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseValueObject nonActiveAccount(NonActiveAccountException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(BlockedAccountException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseValueObject blockedAccount(BlockedAccountException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(EmailIsAbsentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject emailIsAbsent(EmailIsAbsentException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(InvalidChangePasswordLinkException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject invalidPasswordLinkHandler(InvalidChangePasswordLinkException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject InvalidRequest(InvalidRequestException e) {
        return getErrorVO(e);
    }

    private ErrorResponseValueObject getErrorVO(Throwable throwable) {
        ErrorResponseValueObject result = new ErrorResponseValueObject();
        result.setReason(throwable.getMessage());
        result.setTimeStamp(LocalDate.now());
        return result;
    }

    @ExceptionHandler(EmptyPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject passwordAbsent(EmptyPasswordException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(InvalidPasswordFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject invalidPasswordFormat(InvalidPasswordFormatException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(InvalidPasswordLengthException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject invalidPasswordLength(InvalidPasswordLengthException e) {
        return getErrorVO(e);
    }

    @ExceptionHandler(EmptyUuidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject uuidAbsent(EmptyUuidException e) {
        return getErrorVO(e);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        FieldError fieldError = fieldErrors.get(0);
        ErrorResponseValueObject error = new ErrorResponseValueObject();
        error.setReason(fieldError.getDefaultMessage());
        error.setTimeStamp(LocalDate.now());
        return error;
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseValueObject userAlreadyRegistered(UserAlreadyRegisteredException e) {
        return getErrorVO(e);
    }


}



