package bpm.camunda.engine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="DETAILS_LOAN")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int empIdProcessStart;
    int emIdApproval1;
    int empIdApproval2;
    String processInstanceId;
    String taskId;
    String businessKey;
    String name;
    int age;
    String gender;
    String company;
    Double salary;
    double existingLoan;
    double newLoanAmount;
    double ternure;
    double rateOfInterest;
    double emiPerMonth;

}
