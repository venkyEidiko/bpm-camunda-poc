package com.bpm_camunda_service.pack.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpm_camunda_service.pack.entity.Loan;
import com.bpm_camunda_service.pack.model.request.ClaimRequest;
import com.bpm_camunda_service.pack.model.request.LoanRequestModel;
import com.bpm_camunda_service.pack.model.request.UnAssignRequest;

import com.bpm_camunda_service.pack.model.request.*;

import com.bpm_camunda_service.pack.model.response.Commonresponse;
import com.bpm_camunda_service.pack.model.response.Loans;
import com.bpm_camunda_service.pack.model.response.StartProcessResponse;
import com.bpm_camunda_service.pack.service.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/**")
public class LoanController {

    private final LoanService loanService;

    @PostMapping ("/loanRequest")
    public Commonresponse startProcess(@RequestBody LoanRequestModel request){
        System.out.println(request);
        StartProcessResponse response = loanService.stratProcess(request);
        return Commonresponse.builder()
                .result(Arrays.asList("Loan Request Sent SuccessFully"))
                .build();
    }

    @PostMapping("/unassigntask")
    public Commonresponse getUnAssignTask(@RequestBody UnAssignRequest request){
        List<Loans> loans = loanService.getUnassignTask(request);
        System.out.println("loans - "+loans);
        Commonresponse commonresponse = Commonresponse.builder().result(new ArrayList<>()).build();
        commonresponse.getResult().add(loans);
        return commonresponse;
    }

    @PostMapping("/assigntask")
    public Commonresponse getAssignTask(@RequestBody AssigntaskRequest request){
        List<Loans> loans = loanService.getAssignTask(request);
        System.out.println("loans - "+loans);
        Commonresponse commonresponse = Commonresponse.builder().result(new ArrayList<>()).build();
        commonresponse.getResult().add(loans);
        return commonresponse;
    }

    @PostMapping("/claim")
    public Commonresponse claimTask(@RequestBody ClaimRequest request){
        String msg = loanService.claimTask(request);
        return Commonresponse.builder().result(Arrays.asList(msg)).build();
    }


    @GetMapping("/unclaim/{taskId}")
    public Commonresponse unClaimTask(@PathVariable String taskId){
        System.out.println(taskId);
        String msg = loanService.unClaimTask(taskId);
        return Commonresponse.builder().result(Arrays.asList(msg)).build();
    }

    @GetMapping("/tastcount")
    public Commonresponse getTaskCount(){
        return
                Commonresponse.builder()
                        .result(Arrays.asList(loanService.getTaskCount()))
                        .build();
    }


    @GetMapping("/getLoanDetails")
    public ResponseEntity<List<Loan>>getAll(@RequestParam("businessKey") List<String> list)
    {
    	
    	List<Loan> loanData = loanService.getLoanData(list);
    	
		return new ResponseEntity<List<Loan>>(loanData, HttpStatus.OK);
    	
    }

    @PostMapping("/task/complete")
    public Commonresponse completeTask(@RequestBody CompleteTaskRequest request){
        String msg = loanService.completeTask(request);
        return Commonresponse.builder().result(Arrays.asList(msg)).build();
    }

}
