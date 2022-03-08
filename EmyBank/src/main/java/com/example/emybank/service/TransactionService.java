package com.example.emybank.service;

import com.example.emybank.dto.TransactionDto;
import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.response.ResponseApi;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    TransactionHistory save(TransactionHistory transaction);
    List<TransactionHistory> getList();
    Optional<TransactionHistory> findById(int id);
    List<TransactionHistory> findByUserId( int sender_id);
    ResponseApi transferMoney(int sender_id, TransactionDto transactionDto);
}
