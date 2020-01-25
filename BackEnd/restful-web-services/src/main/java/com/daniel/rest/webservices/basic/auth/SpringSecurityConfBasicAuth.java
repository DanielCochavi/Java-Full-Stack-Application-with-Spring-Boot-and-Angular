package com.daniel.rest.webservices.basic.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/* This class is created in order to fix the OPTION error -
An option request is sent to check which had been sent to check if we have the right permissions */
@Configuration
public class SpringSecurityConfBasicAuth extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        (((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.
                csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
                anyRequest()).authenticated().and()
                //formLogin().and().
                )).httpBasic();
    }
}
