package com.upteam.auth.oauth2;

import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Vlad on 26.01.2016.
 */
@Component
public class OAuthUserDetailService implements UserDetailsService {

    @Autowired
    private SystemUserRepository systemUserRepository; // Repository methods are here

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.searchByEmail(userName);
        if(systemUser == null){
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return new OAuthUser(systemUser);
    }

}

