package com.example.emybank.controller;

import com.example.emybank.dto.UserDto;
import com.example.emybank.response.ResponseApi;
import com.example.emybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountApi {

    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.POST,path = "register")
    public ResponseEntity<ResponseApi> register(@RequestBody UserDto userDto){
        return new ResponseEntity<>(service.create(userDto), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST,path = "login")
    public String login(@RequestBody UserDto userDto){
        return "Hello";
    }
}
