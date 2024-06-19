package com.bpm_camunda_service.pack.service;

import com.bpm_camunda_service.pack.entity.Loan;
import com.bpm_camunda_service.pack.model.camundaVariable.CamundaVariables;
import com.bpm_camunda_service.pack.model.request.LoanRequestModel;
import com.bpm_camunda_service.pack.model.response.StartProcessResponse;
import com.bpm_camunda_service.pack.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final WebClientService webClientService;
    @Autowired
    private  LoanRepository loanRepository;

    public static int SEQ = 0;
    public static String START_PROCESS_URL = "/engine-rest/process-definition/key/Loan-Process/start";



    public StartProcessResponse stratProcess(LoanRequestModel request){

        String businessKey = generateBusinessKey();
        System.out.println(businessKey);
        CamundaVariables camundaVariables = new CamundaVariables();
        camundaVariables.setVariables(request.getVarablesMap());
        camundaVariables.setBusinessKey(businessKey);
        System.out.println("input to the api- "+camundaVariables);
        StartProcessResponse response = webClientService.postCall(START_PROCESS_URL,camundaVariables);
        System.out.println("output from the api"+ response);
        Loan loan = Loan.builder()
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

    private String generateBusinessKey(){
        List<Loan> data = loanRepository.findAll();
        SEQ = data.isEmpty() ? SEQ : ++SEQ;
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = formatter.format(currentDate);
        return "BPM"+formattedDate+String.format("%03d", SEQ);

    }
}
