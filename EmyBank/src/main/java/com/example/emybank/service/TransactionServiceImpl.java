package com.example.emybank.service;

import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionHistory save(TransactionHistory transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionHistory> getList() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<TransactionHistory> findById(int id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<TransactionHistory> findByUserId(int sender_id) {
        return transactionRepository.findByUserId(sender_id);
    }
}
