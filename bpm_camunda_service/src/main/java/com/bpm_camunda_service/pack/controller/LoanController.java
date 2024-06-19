package com.bpm_camunda_service.pack.controller;

import com.bpm_camunda_service.pack.model.request.LoanRequestModel;
import com.bpm_camunda_service.pack.model.response.Commonresponse;
import com.bpm_camunda_service.pack.model.response.StartProcessResponse;
import com.bpm_camunda_service.pack.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping ("/loanRequest")
    public Commonresponse startProcess(@RequestBody LoanRequestModel request){
        StartProcessResponse response = loanService.stratProcess(request);
        return Commonresponse.builder()
                .result(Arrays.asList("Loan Request Sent SuccessFully"))
                .build();
    }

}
