package com.upteam.auth.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Created by olegls2000 on 12/4/2015.
 */

@Configuration
@PropertySource(value = { "classpath:application.properties" })

@Component
public class EmailSenderImpl implements EmailSender {

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    private Environment env;

    @Override
    public void sendEmail(EmailGenerator emailGenerator) {
       //TODO REN-29 [BackEnd] Отправка Емэла с ссылкой для подтверждения регистрации>Andrey
        javaMailSender = javaMailSenderImpl();
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

    private JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("smtp.host"));
        mailSender.setPort(env.getProperty("smtp.port", Integer.class));
        mailSender.setProtocol(env.getProperty("smtp.protocol"));
        mailSender.setUsername(env.getProperty("smtp.username"));
        mailSender.setPassword(env.getProperty("smtp.password"));
        return mailSender;
    }

}
