package com.example.emybank.controller;

import com.example.emybank.dto.LoanDto;
import com.example.emybank.entity.LoanDetail;
import com.example.emybank.response.ResponseApi;
import com.example.emybank.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class LoanApi {
    LoanService service;
    @RequestMapping(method = RequestMethod.POST, path = "create")
    public ResponseEntity<ResponseApi> loan(@RequestBody LoanDto loanDto){
        return new ResponseEntity<>(service.create(loanDto), HttpStatus.CREATED);
    }
    @RequestMapping("list")
    public Iterable<LoanDetail> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public LoanDetail findByID(@PathVariable int user_id) {
        return service.findById(user_id);
    }
}
