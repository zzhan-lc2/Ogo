package com.ogomonkey.web.dao;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Lists;

@Slf4j
public class AppUserDetailsServiceDAO implements UserDetailsService {

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername username=" + username);

        // dummy code check
        if (!username.equals("ofudii")) {
            throw new UsernameNotFoundException(username + " not found");
        }

        // TODO
        // creating dummy user details, should call service or JDBC operations
        UserDetailsImpl dummy = new UserDetailsImpl();
        dummy.setEnabled(true);
        dummy.setPassword("dummyPassword123");
        List<SimpleGrantedAuthority> auths = Lists.newArrayList();
        auths.add(new SimpleGrantedAuthority("admin"));
        dummy.setAuthorities(auths);

        return dummy;
    }

}
