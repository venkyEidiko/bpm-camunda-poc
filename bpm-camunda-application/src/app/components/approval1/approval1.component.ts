import { Component, OnInit } from '@angular/core';
import { ClaimService } from 'src/app/services/claim.service';
import { TaskDetails } from 'src/app/taskDetail';

@Component({
  selector: 'app-approval1',
  templateUrl: './approval1.component.html',
  styleUrls: ['./approval1.component.css']
})
export class Approval1Component{

  
  data1=
[
  {
  businessKey: 'key2',
  empId:'',
  existingLoan:'',
  
  newLoanAmount: 50000,
  }
]
}
