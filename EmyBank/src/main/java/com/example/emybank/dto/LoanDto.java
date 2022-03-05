package com.example.emybank.dto;

import java.time.LocalDate;

public class LoanDto {
    private int loan_id;
    private int user_id;
    private LocalDate created_at;
    private double amount;
    private double interest_rate;

    public LoanDto(int loan_id, int user_id, LocalDate created_at, double amount, double interest_rate, int tenor, String status) {
        this.loan_id = loan_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.amount = amount;
        this.interest_rate = interest_rate;
        this.tenor = tenor;
        this.status = status;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int tenor;
    private String status;

}
