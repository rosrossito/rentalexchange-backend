package com.upteam.auth.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * Created by Vlad on 26.01.2016.
 */

@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private OAuthUserDetailService oAuthUserDetailService;  // This Class extends UserDetails Service
    //and overrides loadUserByUsername()

    @Autowired
    private CommonPasswordEncoder commonPasswordEncoder;  //password Encoder

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(oAuthUserDetailService).passwordEncoder(commonPasswordEncoder);
    }
}