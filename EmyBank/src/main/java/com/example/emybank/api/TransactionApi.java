package com.example.emybank.api;

import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/transactions")
@RestController
public class TransactionApi {
    @Autowired
    TransactionService transactionService;

    //create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionHistory transaction){
        transactionService.save(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    //list
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(){
        return new ResponseEntity<>(transactionService.getList(), HttpStatus.OK);
    }

    //findbyid
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id){
        Optional<TransactionHistory> optionalUser = transactionService.findById(id);
        if (optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
