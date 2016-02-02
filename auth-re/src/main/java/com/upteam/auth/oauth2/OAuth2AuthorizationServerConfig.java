package com.upteam.auth.oauth2;

/**
 * Created by Vlad on 26.01.2016.
 */
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@ComponentScan(basePackages = "com.upteam.auth.oauth2")
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(dataSource);  // If you want to maintain client details is database

//Section below for in-memory clients

/*clients.inMemory().withClient("<client_id>")
.resourceIds(<resource_id>)
.authorizedGrantTypes("authorization_code", "implicit")
.authorities("<roles>")
.scopes("read", "write")
.secret("secret")
.and()
.withClient("<client_id>")
.resourceIds(<resource_id>)
.authorizedGrantTypes("authorization_code", "implicit")
.authorities("<roles>")
.scopes("read", "write")
.secret("secret")
.redirectUris(<redirect_url>)
.and()
    .withClient("<client_id>")
.resourceIds(<resource_id>)
          .authorizedGrantTypes("authorization_code", "client_credentials")
          .authorities("ROLE_CLIENT")
          .scopes("read", "trust")
          .redirectUris("http://anywhere?key=value")
    .and()
          .withClient("my-trusted-client")
            .authorizedGrantTypes("password","refresh_token")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .accessTokenValiditySeconds(60)
            .refreshTokenValiditySeconds(600)
    .and()
          .withClient("my-trusted-client-with-secret")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("somesecret")
          .and()
            .withClient("my-less-trusted-client")
            .authorizedGrantTypes("authorization_code", "implicit")
            .authorities("ROLE_CLIENT")
            .scopes("read", "write", "trust")
          .and()
            .withClient("my-less-trusted-autoapprove-client")
                .authorizedGrantTypes("implicit")
                .authorities("ROLE_CLIENT")
                .scopes("read", "write", "trust")
                .autoApprove(true);*/
    }


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource); // access and refresh tokens will be maintain in database
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }

}