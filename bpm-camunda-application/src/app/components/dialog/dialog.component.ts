import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ProcessService } from 'src/app/services/process.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  user: any = null;
  processForm!: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    private processService: ProcessService,
    private formBuilder: FormBuilder,
    private loginservice: LoginService
  ) {}

  ngOnInit(): void {
    this.processForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      age: ['', [Validators.required]],
      gender: ['Male', Validators.required],
      company: ['', [Validators.required]],
      salary: ['', Validators.required],
      existingLoan: ['', [Validators.required]],
      newLoanAmount: ['', [Validators.required]],
      ternure: ['', [Validators.required]],
      rateOfInterest: ['', [Validators.required]]
    });

    this.user = this.loginservice.getEmployeeData();
    if (this.user) {
      console.log('Employee data:', this.user);
    } else {
      console.error('No employee data found');
    }
  }

  onSingup() {
    if (!this.user) {
      console.error('Cannot process form, employee data is missing');
      return;
    }

    const formValues = this.processForm.value;

    const processData = {
      name: formValues.name,
      empId: this.user.empId,
      age: formValues.age,
      gender: formValues.gender,
      company: formValues.company,
      salary: formValues.salary,
      existingLoan: formValues.existingLoan,
      newLoanAmount: formValues.newLoanAmount,
      ternure: formValues.ternure,
      rateOfInterest: formValues.rateOfInterest
    };

    this.processService.process(processData).subscribe(
      (response: any) => {
        if (response.problem == null) {
          console.log('posted successfully');
        } else {
          console.log(response.problem);
        }
      },
      (error: any) => {
        console.log('process error', error);
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
