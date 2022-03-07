package com.example.emybank.controller;

import com.example.emybank.dto.TransactionResult;
import com.example.emybank.exception.CheckBalanceException;
import com.example.emybank.exception.OverDraftException;
import com.example.emybank.exception.UserNotExistException;
import com.example.emybank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionHistoryApi {
    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(TransactionHistoryApi.class);

    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
    public ResponseEntity transferMoney(@RequestBody TransactionResult request) throws Exception {

        try {
            userService.transferBalances(request);

            TransactionResult result = new TransactionResult();
            result.setSender_id(request.getSender_id());
            result.setBalanceAfterTransfer(userService.checkBalance(request.getSender_id()));

            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (UserNotExistException | OverDraftException e) {
            log.error("Fail to transfer balances, please check with system administrator.");
            throw e;
        } catch (CheckBalanceException cbEx) {
            log.error("Fail to check balances after transfer, please check with system administrator.");
            throw cbEx;
        }
    }
}
