package com.example.emybank.respository;

import com.example.emybank.entity.LoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanDetail, Integer>  {
    LoanDetail findLoanDetailByUser_id(Integer user_id);



}
