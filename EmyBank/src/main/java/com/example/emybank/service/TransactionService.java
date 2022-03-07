package com.example.emybank.service;

import com.example.emybank.entity.TransactionHistory;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    TransactionHistory save(TransactionHistory transaction);
    List<TransactionHistory> getList();
    Optional<TransactionHistory> findById(int id);
    List<TransactionHistory> findByUserId( int sender_id);
}
