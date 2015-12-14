package com.upteam.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by opasichnyk on 12/9/2015.
 */

@Configuration
public class EmailConfig {

    @Resource
    private Environment env;

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("smtp.host"));
        mailSender.setPort(env.getProperty("smtp.port", Integer.class));
        mailSender.setUsername(env.getProperty("smtp.username"));
        mailSender.setPassword(env.getProperty("smtp.password"));
        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.transport.protocol", env.getProperty("smtp.protocol"));
        prop.put("mail.smtp.auth",  env.getProperty("smtp.auth"));
        prop.put("mail.smtp.starttls.enable", env.getProperty("smtp.starttls.enable"));
        prop.put("mail.debug", env.getProperty("smtp.debug"));
        return mailSender;
    }
}
