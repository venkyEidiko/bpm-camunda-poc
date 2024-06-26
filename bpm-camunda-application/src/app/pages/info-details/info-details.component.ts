import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClaimService } from 'src/app/services/claim.service';

@Component({
  selector: 'app-info-details',
  templateUrl: './info-details.component.html',
  styleUrls: ['./info-details.component.css']
})
export class InfoDetailsComponent implements OnInit {

  constructor(private service:ClaimService, private router:Router){}
  loanObject:any;

  ngOnInit(): void {
    this.loanObject = this.service.getLoanObject();
    console.log("loanObject - ",this.loanObject.loanDetails.name);
    
  }

  onApproval(){
    this.service.completeTask(this.loanObject.taskDetails.id)
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
