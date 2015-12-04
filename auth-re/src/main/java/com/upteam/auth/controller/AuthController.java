package com.upteam.auth.controller;

import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    void userRegistration(@RequestBody RegistrationRequestVO request) {
        //TODO REN-31 [BackEnd] REST для регистрации >Vlad
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    void userRegistrationConfirm(@RequestBody RegistrationConfirmRequestVO request) {
        // TODO REN-32 [BackEnd] REST для подтверждения регистрации c отправкой писем >Kostik
    }

}
