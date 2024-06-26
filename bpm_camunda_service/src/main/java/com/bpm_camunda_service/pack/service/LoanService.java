package com.bpm_camunda_service.pack.service;

import com.bpm_camunda_service.pack.entity.Loan;
import com.bpm_camunda_service.pack.entity.User;
import com.bpm_camunda_service.pack.model.camundaVariable.CamundaVariables;
import com.bpm_camunda_service.pack.model.request.*;
import com.bpm_camunda_service.pack.model.response.*;
import com.bpm_camunda_service.pack.repository.LoanRepository;
import com.bpm_camunda_service.pack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final WebClientService webClientService;
    private final ModelMapper modelMapper;
    @Autowired
    private  LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;

    public static int SEQ = 0;
    public static String START_PROCESS_URL = "/process-definition/key/Loan-Process/start";
    public static  String GET_UNASSIGN_TASK = "/task";
    public static String CLAIM_TASK = "/task/%s/claim";
    public static String UNCLAIM_TASK = "/task/%s/unclaim";
    public static String TASK_COUNT = "/history/task/count?taskAssignee=admin";
    public static String COMPLETE_TASK = "/task/%s/complete";



    public StartProcessResponse stratProcess(LoanRequestModel request){

        String businessKey = generateBusinessKey();
        CamundaVariables camundaVariables = new CamundaVariables();
        camundaVariables.setVariables(request.getVarablesMap());
        camundaVariables.setBusinessKey(businessKey);
        StartProcessResponse response = webClientService.postCall(START_PROCESS_URL,camundaVariables, StartProcessResponse.class);
        System.out.println(response);

        Loan loan = modelMapper.map(request, Loan.class);
        loan.setBusinessKey(response.getBusinessKey());
        loan.setProcessInstanceId(response.getId());
        loan.setEmpIdProcessStart(request.getEmpId());
        loanRepository.save(loan);
        return response;

    }

    public List<Loans> getUnassignTask(UnAssignRequest request){
        List<Loans> loanList = new ArrayList<>();
        TaskCamundaResponse[] response = webClientService.postCall(GET_UNASSIGN_TASK, request, TaskCamundaResponse[].class);
        loanList = generateLoans(response);
        return loanList;
    }

    public List<Loans> getAssignTask(AssigntaskRequest request){
        List<Loans> loanList = new ArrayList<>();
        TaskCamundaResponse[] response = webClientService.postCall(GET_UNASSIGN_TASK, request, TaskCamundaResponse[].class);
        loanList = generateLoans(response);
        return loanList;
    }



    public String claimTask(ClaimRequest request){
        String url = String.format(CLAIM_TASK,request.getTaskId());
        webClientService.postCall(url,new ClaimRequestCamunda(),Object.class);
        Loan loan = loanRepository.findByProcessInstanceId(request.getProcessInstanceId()).orElseThrow();
        loan.setTaskId(request.getTaskId());
        loanRepository.save(loan);
        return "Successfully Claimed";
    }

    public String unClaimTask(String taskId){
        String url = String.format(UNCLAIM_TASK,taskId);
        System.out.println(url);
        webClientService.postCall(url);
        return "Successfully Unclaimed";
    }

    public int getTaskCount(){
        Integer count = webClientService.getCall(TASK_COUNT, Integer.class);
        return count;
    }

    public String completeTask(String taskId){
        String url = String.format(COMPLETE_TASK,taskId);
        webClientService.postCall(url);
        return "Task Completed";
    }

    private List<Loans> generateLoans(TaskCamundaResponse[] tasks){
        List<Loans> loans = new ArrayList<>();
        for(TaskCamundaResponse task : tasks){
            System.out.println(task.getProcessInstanceId());
            Loan loan = loanRepository.findByProcessInstanceId(task.getProcessInstanceId()).orElse(null);
            User emp ;
            if(task.getName().equals("Approval 1")){
                System.out.println(loan.getEmpIdProcessStart());
                emp = userRepository.findByEmpId(loan.getEmpIdProcessStart());
            }
            else{
                emp = userRepository.findByEmpId(loan.getEmIdApproval1());
            }
            Loans loanItem = getLoansObject(loan, task,emp);
            loans.add(loanItem);
        }
        return loans;
    }

    private Loans getLoansObject(Loan loan, TaskCamundaResponse task,User emp) {

        TaskDetails taskDetails = modelMapper.map(task,TaskDetails.class);
        LoanDetails LoanDetails = modelMapper.map(loan,LoanDetails.class);
        UserResponse userDetails = modelMapper.map(emp,UserResponse.class);
        taskDetails.setStage(task.getName());
        return Loans.builder()
                .taskDetails(taskDetails)
                .loanDetails(LoanDetails)
                .userDetails(userDetails)
                .build();
    }

    private String generateBusinessKey(){
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        return "BPM"+randomNumber;

    }
}
