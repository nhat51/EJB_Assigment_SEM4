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
    private String password;
    private String accountNumber;
    private String email;
    private String phone;
    private double balance;
    private String role;
    private int status;

    @OneToMany(mappedBy = "user")
    Set<TransactionHistory> transactionHistories = new HashSet<>();

    public User(int id, String name, String password, String accountNumber, String email, String phone, double balance, String role, int status) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.accountNumber = accountNumber;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.role = role;
        this.status = status;
    }
}
