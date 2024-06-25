package com.bpm_camunda_service.pack.model.response;

import com.bpm_camunda_service.pack.model.request.LoanRequestModel;
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

    LoanRequestModel loanDetails;
    TaskDetails taskDetails;
    UserResponse userDetails;

}
