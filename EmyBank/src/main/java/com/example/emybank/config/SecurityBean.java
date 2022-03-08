package com.example.emybank.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBean {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static Algorithm algorithm;

    public static Algorithm algorithm(){
        if (algorithm == null){
            algorithm = Algorithm.HMAC256("abcde".getBytes());
        }
       return algorithm;
    }
}
