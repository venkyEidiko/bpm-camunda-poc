package com.bpm_camunda_service.pack.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String firstName;
    String lastName;
    String email;
    long phoneNu;
    String empId;
    String designation;
    double salary;
    String password;
}
