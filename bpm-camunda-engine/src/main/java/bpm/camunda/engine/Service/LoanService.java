package bpm.camunda.engine.Service;

import bpm.camunda.engine.entity.Loan;
import bpm.camunda.engine.repo.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public void updateEmi(double emiPerMonth,String businessKey){
    Loan loan = loanRepository.findByBusinessKey(businessKey);
    if(Objects.isNull(emiPerMonth)){
        loan.setEmiPerMonth(emiPerMonth);
        loanRepository.save(loan);
    }
    }
}
