package com.example.emybank.service;

import com.example.emybank.dto.TransactionDto;
import com.example.emybank.entity.TransactionHistory;
import com.example.emybank.entity.User;
import com.example.emybank.repository.TransactionRepository;
import com.example.emybank.repositoy.UserRepository;
import com.example.emybank.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

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

    @Override
    public ResponseApi transferMoney(int sender_id, TransactionDto transactionDto) {
        User sender = userRepository.getById(sender_id);
        User receiver = userRepository.findUserByAccountNumber(transactionDto.getReceiverAccountNumber());
        if (receiver == null){
            return new ResponseApi(HttpStatus.BAD_REQUEST,"User does not exist","");
        }
        double amount = transactionDto.getAmount();
        double balance = sender.getBalance();
        if (amount > balance){
            return new ResponseApi(HttpStatus.BAD_REQUEST,"Balance is not enough","");
        }
        TransactionHistory history = new TransactionHistory();
        history.setAmount(amount);
        history.setSender_id(sender_id);
        history.setReceiver_id(receiver.getId());
        history.setCreated_at(LocalDate.now());
        history.setMessage(transactionDto.getMessage());
        history.setStatus("DONE");

        sender.setBalance(balance - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        return new ResponseApi(HttpStatus.CREATED,"Success",transactionRepository.save(history));
    }
}
