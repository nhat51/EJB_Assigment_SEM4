package com.example.emybank.controller;

import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionHistoryApi {
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TransactionHistory> create (@RequestBody TransactionHistory transactionHistory){
        transactionHistoryService.save(transactionHistory);
        return new ResponseEntity<TransactionHistory>(transactionHistory, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path ="/transactions/transfer/${sender_id}")
    public List<TransactionHistory> findTransferHistory(@PathVariable int sender_id){
        return transactionHistoryService.findTransactionHistoryBySender_id(sender_id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/transactions/received/${received_id")
    public List<TransactionHistory> findReceivedHistory(@PathVariable int receiver_id){
        return transactionHistoryService.findTransactionHistoryByReceiver_id(receiver_id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/transactions/history/${sender_id}")
    public List<TransactionHistory> findTransactionHistoryByUser(@PathVariable int sender_id){
        return transactionHistoryService.findTransactionHistoryByUser(sender_id);
    }
}
