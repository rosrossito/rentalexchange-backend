package com.upteam.auth.controller;

import com.upteam.auth.exception.EmptyPasswordException;
import com.upteam.auth.exception.InvalidPasswordFormatException;
import com.upteam.auth.exception.InvalidPasswordLengthException;
import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@CrossOrigin
@RestController
public class AuthController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public SuccessResponseVO userRegistration(@RequestBody RegistrationRequestVO request){
        authService.registration(request);
        //TODO is response json mandatory? Need investigation.
        SuccessResponseVO result = new SuccessResponseVO();
        result.setMessage("success");
        return result;
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    SuccessResponseVO userRegistrationConfirm(@Valid @RequestBody RegistrationConfirmRequestVO request) {
        authService.confirmRegistration(request);
        //TODO is response json mandatory? Need investigation.
        SuccessResponseVO result = new SuccessResponseVO();
        result.setMessage("success");
        return result;
    }

    @RequestMapping(value = "/user/change-password", method = RequestMethod.POST)
    SuccessResponseVO changePassword(@RequestBody ChangePasswordRequestVO request){
        authService.changePasswordRequest(request);
        //TODO is response json mandatory? Need investigation.
        SuccessResponseVO result = new SuccessResponseVO();
        result.setMessage("success");
        return result;
    }

    @RequestMapping(value = "/user/change-password-confirm", method = RequestMethod.POST)
    @ResponseBody
    SuccessResponseVO changePasswordConfirm(@Valid @RequestBody ChangePasswordVO request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            if (request.getPassword().length()==0) throw new EmptyPasswordException();
            else if (request.getPassword().length()<8 || request.getPassword().length()>20) throw new InvalidPasswordLengthException();
            else throw new InvalidPasswordFormatException();}
        authService.changePassword(request);
        //TODO is response json mandatory? Need investigation.
        SuccessResponseVO result = new SuccessResponseVO();
        result.setMessage("success");
        return result;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    SuccessResponseVO userLogin(@RequestBody LoginRequestVO request) {
        authService.login(request);
        //TODO is response json mandatory? Need investigation.
        SuccessResponseVO result = new SuccessResponseVO();
        result.setMessage("success");
        return result;
    }

    //TODO - test method only, should be deleted for production
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    TestVO userLogin() {
        return authService.test();
    }
}
