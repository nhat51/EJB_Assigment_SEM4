package com.example.emybank.repository;

import com.example.emybank.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    @Query("select t from TransactionHistory t where t.sender_id = :sender_id and t.status = 'Done'")
    List<TransactionHistory> findTransactionHistoryBySender_id(@Param("sender_id")int sender_id);

    @Query("select t from TransactionHistory t where t.receiver_id = :receiver_id and t.status = 'Done'")
    List<TransactionHistory> findTransactionHistoryByReceiver_id(@Param("received_id")int receiver_id);

    @Query("select t from TransactionHistory t where t.sender_id = :sender_id and t.receiver_id = :sender_id and t.status = 'Done'")
    List<TransactionHistory> findTransactionHistoryByUser(@Param("sender_id")int sender_id);
}
