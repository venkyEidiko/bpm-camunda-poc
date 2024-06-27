import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {
    const url = this.router.url;
    if(url.includes('approval1')){
      
    }

  }
  approval1() {
    this.router.navigate(['/dashboard/approval1/task/unassign']);
  }
  approval2() {
    this.router.navigate(['/dashboard/approval2/task/unassign']);
  }
}
