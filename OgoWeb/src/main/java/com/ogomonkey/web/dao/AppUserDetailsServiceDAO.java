package com.ogomonkey.web.dao;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Lists;
import com.ogomonkey.common.dao.CustomerDao;
import com.ogomonkey.common.entity.customer.Customer;

@Slf4j
public class AppUserDetailsServiceDAO implements UserDetailsService {

    @Autowired
    CustomerDao customerDao;

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername username=" + username);

        Customer customer = customerDao.findByLogin(username);

        // dummy code check
        if (customer == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        // TODO
        // creating dummy user details, should call service or JDBC operations
        UserDetailsImpl details = new UserDetailsImpl();
        details.setUsername(username);
        details.setEnabled(true);
        details.setPassword(customer.getEncryptedPassword()); // do we need to decrypt this password
        List<SimpleGrantedAuthority> auths = Lists.newArrayList();
        auths.add(new SimpleGrantedAuthority("admin")); // every customer is his/her own account admin
        details.setAuthorities(auths);

        return details;
    }

}
