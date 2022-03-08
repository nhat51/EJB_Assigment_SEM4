package com.example.emybank.repositoy;

import com.example.emybank.entity.LoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanDetail,Integer> {
    List<LoanDetail> findLoanDetailsByUser_id(int id);

}
