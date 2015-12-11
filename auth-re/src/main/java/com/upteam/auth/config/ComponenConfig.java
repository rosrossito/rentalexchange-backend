package com.upteam.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by olegls2000 on 12/4/2015.
 */
@Configuration
@ComponentScan(basePackages = "com.upteam.auth.component")
@PropertySource(value = { "classpath:application.properties" })
public class ComponenConfig {
}
