package com.upteam.auth.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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

    @Bean
    public VelocityEngine velocityEngine(){
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("resource.loader", "class");
        properties.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.setVelocityPropertiesMap(properties);
        return velocityEngine.getObject();
    }

}
