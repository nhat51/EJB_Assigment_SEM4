package com.example.emybank.api;

import com.example.emybank.dto.LoanDto;
import com.example.emybank.response.ResponseApi;
import com.example.emybank.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/loans")
@CrossOrigin
public class LoanApi {

    @Autowired
    LoanService loanService;

    @RequestMapping(method = RequestMethod.POST, path = "create")
    public ResponseEntity<ResponseApi> create(@RequestParam(name = "user_id")int id, @RequestBody LoanDto loanDto){
        return new ResponseEntity<>(loanService.create(id, loanDto), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "list")
    public ResponseEntity<ResponseApi> getLoanByUserId(@RequestParam(name = "user_id") int id){
        return new ResponseEntity<>(loanService.findByUserId(id),HttpStatus.OK);
    }
}
