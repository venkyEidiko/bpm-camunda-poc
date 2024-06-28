import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClaimService } from 'src/app/services/claim.service';
import { TaskDetails } from 'src/app/taskDetail';

@Component({
  selector: 'app-assign',
  templateUrl: './assign.component.html',
  styleUrls: ['./assign.component.css']
})
export class AssignComponent implements OnInit{


  constructor(private service: ClaimService, private router: Router){}

  showdata=false;
  showLoader = true;

  UnassignTableData:TaskDetails[]=[];
  tableData:any;

  ngOnInit(): void {
    this.fetchData(this.bodyForUnassign);
  }

  bodyForUnassign:any= 
  {
    "assigned":true,
    "taskDefinitionKey": this.fetchUrl()
  }

  fetchUrl(){
    const url = this.router.url;
    if(url.includes('approval1')){
      return 'approval1';
    }
    else if(url.includes('approval2')){
      return 'approval2';
    }
    return '';
  }


  fetchData(data:any){
    this.service.getAssignTask(data).subscribe(
      (response:any) =>{
        this.tableData =response;
        this.tableData = this.tableData.result[0];
        this.getList(this.tableData);
      }
    )
  }

  
 getList(tableData:any){
  this.showLoader = true;
  this.UnassignTableData = [];
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
  if(this.UnassignTableData.length == 0){
    this.showdata = false;
  }
  else{
    this.showdata = true;
  }
  this.showLoader = false;
}

  


  
  
  
  
  
  data: any[] = [
    {
      businessKey: 'key1',
      empId:'',
      existingLoan:'',
      
      newLoanAmount: 50000,
    },
    
  ];
}
