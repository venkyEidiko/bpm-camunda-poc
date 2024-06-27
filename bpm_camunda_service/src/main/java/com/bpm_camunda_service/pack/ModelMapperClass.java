//package com.bpm_camunda_service.pack;
//
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.bpm_camunda_service.pack.entity.Loan;
//import com.bpm_camunda_service.pack.model.request.LoanRequestModel;
//
//
//public class ModelMapperClass {
//
//	
//	
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	
//	public LoanRequestModel loanToLoanRequestModel(Loan loan) {
//		return modelMapper.map(loan, LoanRequestModel.class);
//	}
//}
