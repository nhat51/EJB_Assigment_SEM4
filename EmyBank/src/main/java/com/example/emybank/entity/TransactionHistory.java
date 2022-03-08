package com.example.emybank.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_histories")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column(name = "sender_id",insertable = false,updatable = false)
    private int sender_id;
    @Column(name = "receiver_id",insertable = false,updatable = false)
    private int receiver_id;
    private double amount;
    private String message;
    private LocalDate created_at;
    private String status;
    private String senderName;
    private String receiverName;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonIgnore
    User receiver;
}
