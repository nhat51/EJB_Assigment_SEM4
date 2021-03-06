package com.example.emybank.controller;

import com.example.emybank.dto.UserDto;
import com.example.emybank.response.ResponseApi;
import com.example.emybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
@CrossOrigin
public class AccountApi {

    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.POST,path = "register")
    public ResponseEntity<ResponseApi> register(@RequestBody UserDto userDto){
        return new ResponseEntity<>(service.create(userDto), HttpStatus.CREATED);
    }

}
