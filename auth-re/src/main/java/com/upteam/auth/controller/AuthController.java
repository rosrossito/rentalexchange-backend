package com.upteam.auth.controller;

import com.upteam.auth.exception.InvalidConfirmRegistrationLinkException;
import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    void userRegistration(@RequestBody RegistrationRequestVO request) throws UserAlreadyExistException {
        //TODO REN-31 [BackEnd] REST для регистрации >Vlad
        authService.registration(request);
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    void userRegistrationConfirm(@RequestBody RegistrationConfirmRequestVO request) throws InvalidConfirmRegistrationLinkException {
        // TODO REN-32 [BackEnd] REST для подтверждения регистрации c отправкой писем >Kostik
        authService.confirmRegistration(request);
    }

    //TODO: test method
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    void sendEmailForTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Test");
        message.setText("TestText");
        message.setTo("opasichnyk@eisgroup.com");
        message.setFrom("exchange.rental.info@gmail.com");
        javaMailSender.send(message);
    }

}
