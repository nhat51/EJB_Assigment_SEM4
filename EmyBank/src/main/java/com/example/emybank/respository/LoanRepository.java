package com.example.emybank.respository;

import com.example.emybank.entity.LoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanDetail, Integer> {
    LoanDetail findLoanDetailByUser_id(Integer user_id);

}
