package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    String firstName;
    String lastName;
    String email;
    long phoneNu;
    String empId;
    String designation;
    double salary;

}
