package com.upteam.auth.security;

import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.domain.domainenum.SystemUserStatus;
import com.upteam.auth.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.plugin.util.UserProfile;

import java.util.ArrayList;
import java.util.List;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemUserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SystemUser user = userService.searchByLogin(s);
        if (user==null) user = userService.searchByEmail(s);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                user.getStatus().equals(SystemUserStatus.active), true, true, true, getGrantedAuthorities(user));


    }

    private List<GrantedAuthority> getGrantedAuthorities(SystemUser user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

/*        for(UserProfile userProfile : user.getUserProfiles()){
            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        System.out.print("authorities :"+authorities);*/
        return authorities;
    }
}
