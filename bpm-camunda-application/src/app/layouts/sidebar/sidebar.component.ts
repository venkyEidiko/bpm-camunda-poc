import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  constructor(private router:Router){}
approval1()
{
   this.router.navigate(['/dashboard/task/unassign'])
}
approval2()
{
  this.router.navigate(['/dashboard/task/approval2'])
}
}
