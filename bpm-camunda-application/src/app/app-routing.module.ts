import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from './pages/registration/registration.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AssignComponent } from './tasks/assign/assign.component';
import { UnassignComponent } from './tasks/unassign/unassign.component';
import { TaskComponent } from './tasks/task/task.component';
import { InfoDetailsComponent } from './pages/info-details/info-details.component';

const routes: Routes = [
  {
    path:'',component:RegistrationComponent
  },
  {
    path:'login',component:LoginComponent
  },
  {
    path:'dashboard',component:DashboardComponent,children:[
     {
      path:'task',component:TaskComponent,children:[
        {
          path:'unassign',component:UnassignComponent
        },
        {
          path:'assign',component:AssignComponent
        }
      ]
     },
     {
      path:'information-details',component:InfoDetailsComponent
     }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
