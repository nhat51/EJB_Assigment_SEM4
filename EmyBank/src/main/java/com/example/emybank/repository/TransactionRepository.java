package com.example.emybank.repository;

import com.example.emybank.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionHistory, Integer>{
    @Query("select t from TransactionHistory t where t.sender_id = :sender_id or t.receiver_id = :sender_id")
    List<TransactionHistory> findByUserId(int sender_id);
}
