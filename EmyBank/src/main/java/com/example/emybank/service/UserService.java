package com.example.emybank.service;

import com.example.emybank.dto.UserDto;
import com.example.emybank.entity.User;
import com.example.emybank.repositoy.UserRepository;
import com.example.emybank.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseApi create(UserDto userDto){
        User exist =userRepository.findUserByAccountNumber(userDto.getAccountNumber());
        if (exist != null){
            return new ResponseApi(HttpStatus.BAD_REQUEST,"Account number already exist","");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        user.setAccountNumber(userDto.getAccountNumber());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setRole("USER");
        user.setStatus(1);
        return new ResponseApi(HttpStatus.CREATED,"Created",userRepository.save(user));
    }

    public Optional<User> finByUserName(String name){
        return userRepository.findFirstByName(name);
    }
}
