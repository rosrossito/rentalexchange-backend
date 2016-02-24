package com.upteam.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@ComponentScan(basePackages = "com.upteam.auth.service")
@Import(RepositoryConfig.class)
public class ServiceConfig {
}
