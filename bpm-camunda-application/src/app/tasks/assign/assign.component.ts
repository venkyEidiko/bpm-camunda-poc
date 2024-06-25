import { Component } from '@angular/core';

@Component({
  selector: 'app-assign',
  templateUrl: './assign.component.html',
  styleUrls: ['./assign.component.css']
})
export class AssignComponent {
  data: any[] = [
    {
      businessKey: 'key1',
      empId:'',
      existingLoan:'',
      
      newLoanAmount: 50000,
    },
    
  ];
}
