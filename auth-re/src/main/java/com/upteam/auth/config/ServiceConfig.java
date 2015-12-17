package com.upteam.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@ComponentScan(basePackages = "com.upteam.auth.service")
@Import(RepositoryConfig.class)
public class ServiceConfig {
    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private Environment env;

    @Bean
    public long linkPeriod() {
        return Long.valueOf(env.getProperty("activation.link.period"));
    }


}
