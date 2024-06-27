import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onLogin() {
    const loginReq = {
      email: this.loginForm.get('email')?.value,
      password: this.loginForm.get('password')?.value
    };
    
    console.log("Request sent to backend: ", loginReq);

    this.loginService.login(loginReq).subscribe(
      (response: any) => {
        if (response.problem == null) {
          console.log("Response from backend: ", response);
          this.router.navigate(["/dashboard/task/unassign"])
        } else {
          console.log("Error: ", response.problem);
        }
      },
      (error: any) => {
        console.error('Login error:', error);
      }
    );
  }
}
