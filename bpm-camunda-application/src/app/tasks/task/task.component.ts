import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{

  constructor(private router:Router){}
  task = 'approval1';
  ngOnInit(): void {
    
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


  onUnassignClick(){
    const url = `/dashboard/${this.fetchUrl()}/task/unassign`;
    this.router.navigate([url]);

  }

  onAssignClick(){
    const url = `/dashboard/${this.fetchUrl()}/task/assign`;
    this.router.navigate([url]);
  }

}
