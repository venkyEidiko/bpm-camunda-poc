
import { Component } from '@angular/core';

@Component({
  selector: 'app-unassign',
  templateUrl: './unassign.component.html',
  styleUrls: ['./unassign.component.css']
})
export class UnassignComponent {
  data: any[] = [
    {
    
      businessKey: 'key1',
      empId:'',
      existingLoan:'',
      
      newLoanAmount: 50000,
      
    },
    {
      businessKey: 'key1',
      empId:'',
      existingLoan:'',
      
      newLoanAmount: 50000,
    },
  ];
}

