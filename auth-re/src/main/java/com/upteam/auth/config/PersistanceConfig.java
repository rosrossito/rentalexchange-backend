package com.upteam.auth.config;

/**
 * Created by mmaximov on 12/11/15.
 */

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "com.upteam.auth.domain")
@EnableTransactionManagement
public class PersistanceConfig {
}
