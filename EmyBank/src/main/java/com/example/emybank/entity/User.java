package com.example.emybank.entity;

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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String passwordHash;
    private String accountNumber;
    private String email;
    private String phone;
    private double balance;
    private String role;
    private int status;

    @OneToMany(mappedBy = "user")
    Set<TransactionHistory> transactionHistories = new HashSet<>();

    @OneToMany(mappedBy = "user")
    Set<LoanDetail> loanDetails = new HashSet<>();
}
