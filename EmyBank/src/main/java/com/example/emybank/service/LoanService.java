package com.example.emybank.service;

import com.example.emybank.dto.LoanDto;
import com.example.emybank.entity.LoanDetail;
import com.example.emybank.response.ResponseApi;
import com.example.emybank.respository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class LoanService {
    @Autowired
    LoanRepository loanRepository;
    public ResponseApi create(LoanDto loanDto){
        LoanDetail exist = loanRepository.findLoanDetailByUser_id(loanDto.getUser_id());
        if (exist != null){
            return new ResponseApi(HttpStatus.BAD_REQUEST, "User id already exist","");
        }
        LoanDetail loanDetail = new LoanDetail();
        loanDetail.setLoan_id(loanDto.getLoan_id());
        loanDetail.setUser_id(loanDto.getUser_id());
        loanDetail.setCreated_at(loanDto.getCreated_at());
        loanDetail.setAmount(loanDto.getAmount());
        loanDetail.setInterest_rate(loanDto.getInterest_rate());
        loanDetail.setTenor(loanDto.getTenor());
        loanDetail.setStatus("1");
        return new ResponseApi(HttpStatus.CREATED,"Created", loanRepository.save(loanDetail));
    }
}
