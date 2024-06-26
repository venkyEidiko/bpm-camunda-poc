package bpm.camunda.engine.repo;

import bpm.camunda.engine.entity.Loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Loan findByBusinessKey(String businessKey);
}
