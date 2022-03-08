package com.example.emybank.api;

import com.example.emybank.dto.TransactionDto;
import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return new ResponseEntity<>(transactionService.getList(), HttpStatus.CREATED);
    }

    //findbyid
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id){
        Optional<TransactionHistory> optionalTransaction = transactionService.findById(id);
        if (optionalTransaction.isPresent()){
            return new ResponseEntity<>(optionalTransaction.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

   //findbyuserid
   @RequestMapping(method = RequestMethod.GET,path = "search")
   public ResponseEntity<Object> findByUserId(@RequestParam(name = "user_id") int sender_id){
       return new ResponseEntity<>(transactionService.findByUserId(sender_id),HttpStatus.OK);
   }

   @RequestMapping(method = RequestMethod.POST,path = "transfer")
    public ResponseEntity<Object> transferMoney(@RequestParam(name = "sender_id") int sender_id, @RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.transferMoney(sender_id, transactionDto),HttpStatus.CREATED);
   }
}
