package com.bpm_camunda_service.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpm_camunda_service.pack.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
    Optional<Loan> findByProcessInstanceId(String processInstanceId);

     List<Loan> findAllByBusinessKeyIn(List<String> businessKeys);


    Loan findByTaskId(String taskId);
    // Loan findByBusinessKey(String businessKey);

}
