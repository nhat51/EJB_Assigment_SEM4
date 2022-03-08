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
@Table(name = "loan_details")
public class LoanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loan_id;
    @JoinColumn(name = "user_id")
    private int user_id;
    private LocalDate created_at;
    private LocalDate approvedDate;
    private double amount;
    private double interest_rate;
    private int tenor; // thời hạn vay
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @JsonIgnore
    User user;
}
