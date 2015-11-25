package com.upteam.auth.controller;

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
    String userRegistration(@RequestBody RegistrationRequestVO request) {
        return "User - registrated!" + request.getEmail();
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    String userRegistrationConfirm(@RequestBody RegistrationConfirmRequestVO request) {
        return "User - registration-confirm!" + request.getUuid() + request.getPassword();
    }

}
