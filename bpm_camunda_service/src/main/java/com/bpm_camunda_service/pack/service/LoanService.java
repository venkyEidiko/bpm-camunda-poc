package com.bpm_camunda_service.pack.service;

import com.bpm_camunda_service.pack.entity.Loan;
import com.bpm_camunda_service.pack.entity.User;
import com.bpm_camunda_service.pack.model.camundaVariable.CamundaVariables;
import com.bpm_camunda_service.pack.model.request.ClaimRequest;
import com.bpm_camunda_service.pack.model.request.LoanRequestModel;
import com.bpm_camunda_service.pack.model.request.UnAssignRequest;
import com.bpm_camunda_service.pack.model.response.Loans;
import com.bpm_camunda_service.pack.model.response.StartProcessResponse;
import com.bpm_camunda_service.pack.model.response.TaskCamundaResponse;
import com.bpm_camunda_service.pack.repository.LoanRepository;
import com.bpm_camunda_service.pack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final WebClientService webClientService;
    @Autowired
    private  LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;

    public static int SEQ = 0;
    public static String START_PROCESS_URL = "/process-definition/key/Loan-Process/start";
    public static  String GET_UNASSIGN_TASK = "/history/task";
    public static String CLAIM_TASK = "/task/%s/claim";
    public static String UNCLAIM_TASK = "task/%s/unclaim";
    public static String TASK_COUNT = "/history/task/count?taskAssignee=admin";



    public StartProcessResponse stratProcess(LoanRequestModel request){

        String businessKey = generateBusinessKey();
        CamundaVariables camundaVariables = new CamundaVariables();
        camundaVariables.setVariables(request.getVarablesMap());
        camundaVariables.setBusinessKey(businessKey);
        StartProcessResponse response = webClientService.postCall(START_PROCESS_URL,camundaVariables, StartProcessResponse.class);
        Loan loan = Loan.builder()
                .empIdProcessStart(request.getEmpId())
                .name(request.getName())
                .age(request.getAge())
                .processInstanceId(response.getId())
                .businessKey(response.getBusinessKey())
                .newLoanAmount(request.getNewLoanAmount())
                .existingLoan(request.getExistingLoan())
                .salary(request.getSalary())
                .gender(request.getGender())
                .ternure(request.getTernure())
                .rateOfInterest(request.getRateOfInterest())
                .company(request.getCompany())
                .build();
        loanRepository.save(loan);
        return response;

    }

    public List<Loans> getTask(UnAssignRequest request){
        List<Loans> loanList = new ArrayList<>();
        TaskCamundaResponse[] response = webClientService.postCall(GET_UNASSIGN_TASK, request, TaskCamundaResponse[].class);
        loanList = generateLoans(response);
        return loanList;
    }

    public String claimTask(String processInstanceId){
        String url = String.format(CLAIM_TASK,processInstanceId);
        webClientService.postCall(url,new ClaimRequest(),Object.class);
        return "Successfully Claimed";
    }

    public String unClaimTask(String processInstanceId){
        String url = String.format(UNCLAIM_TASK,processInstanceId);
        webClientService.postCall(url,null,Object.class);
        return "Successfully Unclaimed";
    }

    public int getTaskCount(){
        Integer count = webClientService.getCall(TASK_COUNT, Integer.class);
        return count;
    }

    private List<Loans> generateLoans(TaskCamundaResponse[] tasks){
        List<Loans> loans = new ArrayList<>();
        for(TaskCamundaResponse task : tasks){
            Loan loan = loanRepository.findByProcessInstanceId(task.getProcessInstanceId()).orElse(null);
            User emp =
            Loans loanItem = getLoansObject(loan, task);
            loans.add(loanItem);
        }
        return loans;
    }

    private Loans getLoansObject(Loan loan, TaskCamundaResponse task) {
        return Loans.builder()
                .name(loan.getName())
                .age(loan.getAge())
                .gender(loan.getGender())
                .company(loan.getCompany())
                .salary(loan.getSalary())
                .existingLoan(loan.getExistingLoan())
                .newLoanAmount(loan.getNewLoanAmount())
                .ternure(loan.getTernure())
                .rateOfInterest(loan.getRateOfInterest())
                .processInstanceId(task.getProcessInstanceId())
                .taskId(task.getId())
                .businessKey(loan.getBusinessKey())
                .startTime(task.getStartTime())
                .Stage(task.getName())
                .processDefinitionKey(task.getProcessDefinitionKey())
                .build();
    }

    private String generateBusinessKey(){
        List<Loan> data = loanRepository.findAll();
        SEQ = data.isEmpty() ? SEQ : ++SEQ;
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = formatter.format(currentDate);
        return "BPM"+formattedDate+String.format("%03d", SEQ);

    }
}
