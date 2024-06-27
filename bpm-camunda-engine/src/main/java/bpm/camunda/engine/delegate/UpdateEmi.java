package bpm.camunda.engine.delegate;

import bpm.camunda.engine.Service.LoanService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "updateEMI")
public class UpdateEmi implements JavaDelegate {

    @Autowired
    LoanService loanService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        double emiPerMonth = (Double) delegateExecution.getVariable("emiPerMonth");
        System.out.println("Emi per month ::"+emiPerMonth);
        System.out.println("businessKey is :"+delegateExecution.getBusinessKey());
        loanService.updateEmi(emiPerMonth, delegateExecution.getBusinessKey());
    }
}
