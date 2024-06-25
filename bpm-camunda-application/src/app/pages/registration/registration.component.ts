import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  singupResponse: any;
  showPassword = false;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      lastName:['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email, Validators.pattern(/@eidiko-india\.com$/)]],
      phoneNu: ['', [Validators.required, Validators.pattern(/^[6-9]\d{9}$/)]],
      empId: ['', [Validators.required, Validators.max(9999)]],
      designation: ['', [Validators.required]],
      salary: ['', [Validators.required, Validators.max(99999999)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/)]],
      confirmPassword: ['', []],
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    if (password !== confirmPassword) {
      return { passwordsNotMatching: true };
    }
    return null;
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  onSingup() {
    const formValues = this.registrationForm.value;

    const registrationData = {
      firstName:formValues.firstName,
      lastName:formValues.lastName,
      email: formValues.email,
      phoneNu: formValues.phoneNu,
      empId: formValues.empId,
      designation: formValues.designation,
      salary: formValues.salary,
      password: formValues.password
    };
console.log(registrationData);

    this.loginService.register(registrationData).subscribe(
      (response: any) => {
        if (response.problem == null) {
          console.log("Regsitered successfully")
          alert(response.result[0])
          this.router.navigate(['/login']);
        } else {
          console.log(response.problem);
        }
      },
      (error: any) => {
        console.error('Registration error, route to error page', error);
      }
    );
  }


}
