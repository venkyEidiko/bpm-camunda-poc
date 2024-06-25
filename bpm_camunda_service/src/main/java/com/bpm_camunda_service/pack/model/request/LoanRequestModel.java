package com.bpm_camunda_service.pack.model.request;

import com.bpm_camunda_service.pack.model.camundaVariable.CamundaChildVariables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequestModel {

    String name;
    int age;
    String gender;
    String company;
    Double salary;
    int empId;
    double existingLoan;
    double newLoanAmount;
    double ternure;
    double rateOfInterest;
    double emiPerMonth;

    public Map<String,Object> getVarablesMap(){
        Map<String, Object> map = new HashMap<>();

        CamundaChildVariables name = CamundaChildVariables.builder()
                                    .type("String")
                                    .value(this.name)
                                    .build();
        map.put("name",name);

        CamundaChildVariables age = CamundaChildVariables.builder()
                .type("Integer")
                .value(this.age)
                .build();
        map.put("age",age);

        CamundaChildVariables gender = CamundaChildVariables.builder()
                .type("String")
                .value(this.gender)
                .build();
        map.put("gender",gender);

        CamundaChildVariables company = CamundaChildVariables.builder()
                .type("String")
                .value(this.company)
                .build();
        map.put("company",company);

        CamundaChildVariables salary = CamundaChildVariables.builder()
                .type("Double")
                .value(this.salary)
                .build();
        map.put("salary",salary);

        CamundaChildVariables existingLoan = CamundaChildVariables.builder()
                .type("Double")
                .value(this.existingLoan)
                .build();
        map.put("existingLoan",existingLoan);

        CamundaChildVariables newLoanAmount = CamundaChildVariables.builder()
                .type("Double")
                .value(this.newLoanAmount)
                .build();
        map.put("newLoanAmount",newLoanAmount);

        CamundaChildVariables ternure = CamundaChildVariables.builder()
                .type("Double")
                .value(this.ternure)
                .build();
        map.put("ternure",ternure);

        CamundaChildVariables rateOfInterest = CamundaChildVariables.builder()
                .type("Double")
                .value(this.rateOfInterest)
                .build();
        map.put("rateOfInterest",rateOfInterest);


        return map;
    }
}
