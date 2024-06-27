import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClaimService } from 'src/app/services/claim.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-info-details',
  templateUrl: './info-details.component.html',
  styleUrls: ['./info-details.component.css']
})
export class InfoDetailsComponent implements OnInit {

  constructor(private service:ClaimService, private router:Router,private loginService:LoginService){}
  loanObject:any;

  ngOnInit(): void {
    this.loanObject = this.service.getLoanObject();
    console.log("loanObject - ",this.loanObject.loanDetails.name);
    
  }

  onApproval(){
    const request =  {
      taskId :this.loanObject.taskDetails.id,
      empId:this.loginService.getEmployeeData().empId
    }
    this.service.completeTask(request)
    .subscribe(
      response =>{
        console.log(response);
        this.router.navigate(['/dashboard/task/assign']);
      },
      error => {
        console.log(error);
      }
    )
  }

}
