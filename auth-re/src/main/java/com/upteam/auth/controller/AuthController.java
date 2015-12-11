package com.upteam.auth.controller;

import com.upteam.auth.component.EmailGenerator;
import com.upteam.auth.component.EmailGeneratorImpl;
import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.EmailSenderImpl;
import com.upteam.auth.exception.InvalidConfirmRegistrationLinkException;
import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        message.setTo("andrewkarnaukhow@gmail.com");
        message.setFrom("exchange.rental.info@gmail.com");
        javaMailSender.send(message);
    }
    //created by akarnaukhow
    @RequestMapping(value = "/mailtest", method = RequestMethod.GET)
    void sendTestEmail(){
        EmailGenerator mail = new EmailGeneratorImpl(new ArrayList<String>()
            {{add("andrewkarnaukhow@gmail.com");add("rentalexchange.t@gmail.com");}},
                "test subject",
                "test text",
                "test to");
        EmailSender sender = new EmailSenderImpl();
        sender.sendEmail(mail);
    }



}
