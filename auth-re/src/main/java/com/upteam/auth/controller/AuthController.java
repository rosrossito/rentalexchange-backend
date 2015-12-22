package com.upteam.auth.controller;

import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@RestController
public class AuthController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    void userRegistration(@RequestBody RegistrationRequestVO request) throws UserAlreadyExistException {
        authService.registration(request);
    }

    @RequestMapping(value = "/user/registration-confirm", method = RequestMethod.POST)
    @ResponseBody
    void userRegistrationConfirm(@RequestBody RegistrationConfirmRequestVO request)  {
        authService.confirmRegistration(request);
    }

//-------------------- Test method: ---------------------

    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @RequestMapping(value = "/email-template", method = RequestMethod.GET)
    @ResponseBody
    void sendEmailTemplate()  {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("yeapless@gmail.com");
                message.setFrom("exchange.rental.info@gmail.com");
                Map model = new HashMap();
                User user = new User();
                user.setEmailAddress("emailFromUserObject@gmail.com");
                user.setUserName("userNameFromUserObject");
                model.put("user", user);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "com/temp.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        this.javaMailSender.send(preparator);
    }

    private class User {
        private String userName;
        private String emailAddress;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }
    }

}
