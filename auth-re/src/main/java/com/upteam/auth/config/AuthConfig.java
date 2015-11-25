package com.upteam.auth.config;

import com.upteam.auth.config.RepositoryConfig;
import com.upteam.auth.config.ServiceConfig;
import com.upteam.auth.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@Import({RepositoryConfig.class, ServiceConfig.class, WebConfig.class})
public class AuthConfig {

}
