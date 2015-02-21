package com.ogomonkey.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ogomonkey.web.dao.AppUserDetailsServiceDAO;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AppUserDetailsServiceDAO userDetailServiceDao;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
        throws Exception {

        // in-memory authentication
        // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

        // using custom UserDetailsService DAO
        auth.userDetailsService(userDetailServiceDao);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            // Spring Security should completely ignore URLs ending with .html
            .antMatchers("/*.html");
    }
}
