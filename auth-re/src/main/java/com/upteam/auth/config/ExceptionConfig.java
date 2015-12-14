package com.upteam.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Skirdovs on 10.12.2015.
 */
@Configuration
@ComponentScan(basePackages = "com.upteam.auth.exception")
public class ExceptionConfig {

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
