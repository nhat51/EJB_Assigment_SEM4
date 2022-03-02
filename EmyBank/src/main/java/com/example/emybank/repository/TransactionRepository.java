package com.example.emybank.repository;

import com.example.emybank.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionHistory, Integer>{

}
