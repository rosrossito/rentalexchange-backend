package com.upteam.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@ComponentScan(basePackages = "com.upteam.auth.repository")
public class RepositoryConfig {
    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
