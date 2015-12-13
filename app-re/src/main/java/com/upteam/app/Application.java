package com.upteam.app;

import com.upteam.auth.config.AuthConfig;
import com.upteam.auth.domain.SystemUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@EnableAutoConfiguration
@Import({AuthConfig.class})
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
