package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetails {

    int empIdProcessStart;
    int emIdApproval1;
    int empIdApproval2;
    String name;
    int age;
    String gender;
    String company;
    Double salary;
    double existingLoan;
    double newLoanAmount;
    double ternure;
    double rateOfInterest;
    String businessKey;

}
