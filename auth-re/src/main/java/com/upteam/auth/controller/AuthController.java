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

/**
 * Created by opasichnyk on 11/25/2015.
 */
@RestController
public class AuthController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthService authService;

    @CrossOrigin(origins = {"http://rental.com:63342", "http://localhost:63342"})
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    void userRegistration(@RequestBody RegistrationRequestVO request) throws UserAlreadyExistException {
        authService.registration(request);
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    void userRegistrationConfirm(@RequestBody RegistrationConfirmRequestVO request)  {
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
    void changePasswond(@RequestBody ChangePasswordVO request) { authService.changePassword(request);}

}
