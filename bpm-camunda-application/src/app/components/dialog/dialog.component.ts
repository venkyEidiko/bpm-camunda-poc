import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ProcessService } from 'src/app/services/process.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { FormValues, ProcessData } from 'src/app/components/dialog/dialoginter'

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

  onSubmit() {
    if (!this.user) {
      console.error('Cannot process form, employee data is missing');
      return;
    }

    if (this.processForm.valid) {
      const formValues: FormValues = this.processForm.value;
    
      const processData: ProcessData = {
        ...formValues,
        empId: this.user.empId
      };
      console.log(processData)

      this.processService.process(processData).subscribe(
        
        (response: any) => {
          console.log("into service");
          if (response.problem===null) {
            console.log(response.problem)
            console.log('posted successfully');
            this.dialogRef.close();
          } else {
            console.error('Response error:', response.problem);
          }
        },
        (error: any) => {
          console.error('Process error:', error);
        }
      );
    } else {
      this.processForm.markAllAsTouched();
      console.warn('Form is invalid');
    }
  }
}
