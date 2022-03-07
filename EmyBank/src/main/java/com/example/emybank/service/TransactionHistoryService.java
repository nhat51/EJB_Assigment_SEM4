package com.example.emybank.service;

import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService {
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistory save(TransactionHistory transactionHistory){
        return transactionHistoryRepository.save(transactionHistory);
    }

    public List<TransactionHistory> findTransactionHistoryBySender_id(int sender_id){
        return transactionHistoryRepository.findTransactionHistoryBySender_id(sender_id);
    }

    public List<TransactionHistory> findTransactionHistoryByReceiver_id(int receiver_id){
        return transactionHistoryRepository.findTransactionHistoryByReceiver_id(receiver_id);
    }

    public List<TransactionHistory> findTransactionHistoryByUser(int sender_id){
        return transactionHistoryRepository.findTransactionHistoryByUser(sender_id);
    }
}
