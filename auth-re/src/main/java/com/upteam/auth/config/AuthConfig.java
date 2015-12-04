package com.upteam.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@Import({RepositoryConfig.class,
        ServiceConfig.class,
        WebConfig.class,
        ComponenConfig.class})
public class AuthConfig {

}
