package com.example.emybank.config;

import com.example.emybank.entity.User;
import com.example.emybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.finByUserName(username);
        if (!optionalUser.isPresent()){
            return null;
        }
        User user = optionalUser.get();
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (user.getRole().equals("USER")){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        }else if (user.getRole().equals("ADMIN")){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        UserDetails userDetails
                = new org.springframework.security.core.userdetails.User(user.getName(),user.getPasswordHash(),simpleGrantedAuthorities);
        return userDetails;
    }
}
