package com.upteam.auth.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Created by olegls2000 on 12/4/2015.
 */
@Component
public class EmailSenderImpl implements EmailSender {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Override
    public void sendEmail(EmailGenerator emailGenerator) {
       //TODO REN-29 [BackEnd] Отправка Емэла с ссылкой для подтверждения регистрации>Andrey
        javaMailSender.send(converter(emailGenerator));
    }

    private SimpleMailMessage converter(EmailGenerator emailGenerator){
        SimpleMailMessage result = new SimpleMailMessage();
        result.setFrom(emailGenerator.getFrom());
        result.setSubject(emailGenerator.getSubject());
        result.setText(emailGenerator.getText());
        //TODO need complete
        result.setTo(emailGenerator.getEmailsTo().get(0));
        return result;
    }
}
