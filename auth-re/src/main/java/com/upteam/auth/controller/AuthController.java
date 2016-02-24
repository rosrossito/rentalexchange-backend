package com.upteam.auth.controller;

import com.upteam.auth.exception.InvalidChangePasswordLinkException;
import com.upteam.auth.exception.NonActiveAccountException;
import com.upteam.auth.exception.SystemUserProblemException;
import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    void userRegistration(@Valid @RequestBody RegistrationRequestVO request) {
        authService.registration(request);
//        RegistrationRequestVO vo = new RegistrationRequestVO();
//        vo.setEmail("Vanya vse OK?");
//        return vo;
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    void userRegistrationConfirm(@Valid @RequestBody RegistrationConfirmRequestVO request) {
        authService.confirmRegistration(request);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    void userLogin(@RequestBody LoginRequestVO request) {
        authService.login(request);
    }

    @RequestMapping(value = "/user/change-password/request", method = RequestMethod.POST)
    void changePasswordRequest(@RequestBody ChangePasswordRequestVO request) throws SystemUserProblemException {
        authService.changePasswordRequest(request);
    }

    @RequestMapping(value = "/user/change-password", method = RequestMethod.POST)
    @ResponseBody
    void changePassword(@RequestBody ChangePasswordVO request) {
        authService.changePassword(request);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    TestVO userLogin() {
        return authService.test();
    }
}
