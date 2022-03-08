package com.example.emybank.service;

import com.example.emybank.dto.LoanDto;
import com.example.emybank.entity.LoanDetail;
import com.example.emybank.entity.User;
import com.example.emybank.repositoy.LoanRepository;
import com.example.emybank.repositoy.UserRepository;
import com.example.emybank.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    public List<LoanDetail> findAll() {
        return loanRepository.findAll();
    }

    public ResponseApi findByUserId(int id) {
        return new ResponseApi(HttpStatus.OK,"Success", loanRepository.findLoanDetailsByUser_id(id));
    }

    public ResponseApi create(int user_id, LoanDto loanDto) {
        // lấy approvedDate = today + 3 days;
        LocalDate approvedDate;
        LocalDate currentTime = LocalDate.now();
        approvedDate = currentTime.plusDays(3);

        User user = userRepository.getById(user_id);

        // Rate co dinh la 5%
        LoanDetail loan = new LoanDetail();
        loan.setAmount(loanDto.getAmount());
        loan.setTenor(loanDto.getTenure());
        loan.setStatus("PROCESSING");
        loan.setInterest_rate(5);
        loan.setUser_id(user.getId());
        loan.setCreated_at(currentTime);
        loan.setApprovedDate(approvedDate);

        return new ResponseApi(HttpStatus.CREATED,"Loan will approve at: " + approvedDate, loanRepository.save(loan));
    }
    public double calculate(int user_id,LoanDto loanDto) {
        double amount = loanDto.getAmount();
        double tenure = loanDto.getTenure();
        double rate =(double) 5/ (100 * loanDto.getTenure());
        double exponential = Math.pow((1 + rate), tenure);
        double amountPerMonth = amount * (rate * exponential / (exponential - 1));

        return amountPerMonth;
    }

}
