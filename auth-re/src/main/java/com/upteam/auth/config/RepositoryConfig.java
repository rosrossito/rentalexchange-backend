package com.upteam.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Configuration
@EnableJpaRepositories("com.upteam.auth.repository")
public class RepositoryConfig {
}
