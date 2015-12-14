package com.upteam.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@Import({RepositoryConfig.class,
        ServiceConfig.class,
        WebConfig.class,
        ComponenConfig.class,
        EmailConfig.class, PersistanceConfig.class
})
public class AuthConfig {

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
