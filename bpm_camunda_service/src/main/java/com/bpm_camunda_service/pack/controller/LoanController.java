package com.bpm_camunda_service.pack.controller;

import com.bpm_camunda_service.pack.model.request.*;
import com.bpm_camunda_service.pack.model.response.Commonresponse;
import com.bpm_camunda_service.pack.model.response.Loans;
import com.bpm_camunda_service.pack.model.response.StartProcessResponse;
import com.bpm_camunda_service.pack.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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

    @GetMapping("/claim")
    public Commonresponse claimTask(@RequestBody ClaimRequest request){
        String msg = loanService.claimTask(request);
        return Commonresponse.builder().result(Arrays.asList(msg)).build();
    }

    @GetMapping("/unclaim/{taskId}")
    public Commonresponse unClaimTask(@PathVariable String taskId){
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
    @PostMapping("/task/complete/{taskId}")
    public Commonresponse completeTask(@PathVariable String taskId){
        String msg = loanService.completeTask(taskId);
        return Commonresponse.builder().result(Arrays.asList(msg)).build();
    }

}
