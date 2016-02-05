package com.upteam.auth.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Vlad on 05.02.2016.
 */
public class SpringWebMvcInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { CustomWebSecurityConfigurerAdapter.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {CustomWebSecurityConfigurerAdapter.class} ;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
