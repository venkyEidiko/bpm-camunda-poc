
import { Component, OnInit } from '@angular/core';
import { ClaimService } from 'src/app/services/claim.service';
import { TaskDetails } from 'src/app/taskDetail';

@Component({
  selector: 'app-unassign',
  templateUrl: './unassign.component.html',
  styleUrls: ['./unassign.component.css']
})
export class UnassignComponent implements OnInit{


  constructor(private service: ClaimService){}

  showdata=false;

  UnassignTableData:TaskDetails[]=[];
  tableData:any;

  ngOnInit(): void {
    this.fetchData(this.bodyForUnassign);
  }

  bodyForUnassign:any= 
  {
    "unassigned":true,
    "taskDefinitionKey": "approval1"
  }

  fetchData(data:any){
    this.service.getUnassignTask(data).subscribe(
      (response:any) =>{
        this.tableData =response;
        this.tableData = this.tableData.result[0];
        this.getList(this.tableData);
      }
    )
  }

  
 getList(tableData:any){
  for(let data of tableData){
    const tableEntry:TaskDetails = {
    bussinessKey:data.loanDetails.businessKey,
    empId:data.userDetails.empId,
    existingLoanAmount:data.loanDetails.existingLoan,
    newLoanAmount:data.loanDetails.newLoanAmount
    }
    this.UnassignTableData.push(tableEntry);
    console.log(this.UnassignTableData);
  }
  this.showdata = true;
}

  


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

