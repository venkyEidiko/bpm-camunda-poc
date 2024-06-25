// src/app/models/form-values.interface.ts

export interface FormValues {
    name: string;
    age: string;
    gender: string;
    company: string;
    salary: string;
    existingLoan: string;
    newLoanAmount: string;
    ternure: string;
    rateOfInterest: string;
  }
  
  export interface ProcessData extends FormValues {
    empId: string;
  }
  