package com.upteam.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by olegls2000 on 12/4/2015.
 */
@Configuration
@ComponentScan(basePackages = "com.upteam.auth.component")
@PropertySource(value = {"classpath:application.properties"})
public class ComponenConfig {
    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
