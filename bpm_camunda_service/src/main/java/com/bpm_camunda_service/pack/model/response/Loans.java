package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loans {

    String name;
    int age;
    String gender;
    String company;
    Double salary;
    double existingLoan;
    double newLoanAmount;
    double ternure;
    double rateOfInterest;
    String processInstanceId;
    String taskId;
    String businessKey;
    Date startTime;
    String Stage;
    String processDefinitionKey;
    String empName;
    String empId;
    String empEmail;
    Long empPhone;
    String empDesignation;

}
