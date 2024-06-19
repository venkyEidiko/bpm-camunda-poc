package com.bpm_camunda_service.pack.repository;

import com.bpm_camunda_service.pack.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
   // Loan findByBusinessKey(String businessKey);
}
