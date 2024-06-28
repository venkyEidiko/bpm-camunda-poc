// import { Component, Input, SimpleChanges, ViewChild, AfterViewInit, OnChanges } from '@angular/core';
// import { MatPaginator } from '@angular/material/paginator';
// import { MatTableDataSource } from '@angular/material/table';

// @Component({
//   selector: 'app-table',
//   templateUrl: './table.component.html',
//   styleUrls: ['./table.component.css']
// })
// export class TableComponent implements OnChanges, AfterViewInit {
//   @Input() dataSource: any[] = [];
//   @Input() showInfoIcon: boolean = true;
//   @Input() buttonLabel: string = 'claim';
//   @ViewChild(MatPaginator) paginator!: MatPaginator;
//   displayedColumns: string[] = [];
//   dataSourceMat!: MatTableDataSource<any>;

//   constructor() {}

//   ngOnChanges(changes: SimpleChanges) {
//     if (changes['dataSource'] && changes['dataSource'].currentValue) {
//       console.log('New data received:', changes['dataSource'].currentValue);
//       this.displayedColumns = Object.keys(changes['dataSource'].currentValue[0]);
//       console.log('Displayed columns:', this.displayedColumns);
//       this.dataSourceMat = new MatTableDataSource<any>(changes['dataSource'].currentValue);
//       console.log('MatTableDataSource created:', this.dataSourceMat);
//     }
//   }

//   ngAfterViewInit() {
//     if (this.dataSourceMat) {
//       this.dataSourceMat.paginator = this.paginator;
//     }
//   }

//   onButtonClick() {
//     console.log(`${this.buttonLabel} button clicked!`);
//   }
// }

import {
  Component,
  Input,
  SimpleChanges,
  ViewChild,
  AfterViewInit,
  OnChanges,
} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ClaimService } from 'src/app/services/claim.service';
import { UnassignComponent } from 'src/app/tasks/unassign/unassign.component';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnChanges, AfterViewInit {
  @Input() dataSource: any[] = [];
  @Input() showInfoIcon: boolean = true;
  @Input() buttonLabel: string = 'claim';
  @Input() list: any[] = [];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  displayedColumns: string[] = [];
  dataSourceMat!: MatTableDataSource<any>;
  showTable = true;

  constructor(private service: ClaimService, private router: Router) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['dataSource'] && changes['dataSource'].currentValue) {
      console.log('New data received:', changes['dataSource'].currentValue);
      this.displayedColumns = [
        'infoIcon',
        ...Object.keys(changes['dataSource'].currentValue[0]),
        'action',
      ];
      console.log('Displayed columns:', this.displayedColumns);
      this.dataSourceMat = new MatTableDataSource<any>(
        changes['dataSource'].currentValue
      );
      console.log('MatTableDataSource created:', this.dataSourceMat);
    }
  }

  ngAfterViewInit() {
    if (this.dataSourceMat) {
      this.dataSourceMat.paginator = this.paginator;
    }
  }

  onButtonClick(element: any) {
    console.log("element - ",element);
    console.log("list - ",this.list);
    const loanObject = this.getData(element);
    if (this.buttonLabel == 'claim') {
      const claimRequestBody ={
        taskId: loanObject.taskDetails.id,
        processInstanceId: loanObject.taskDetails.processInstanceId
      }
      this.service.claim(claimRequestBody).subscribe(
        response => {
          console.log(response);
          this.dataSourceMat.data = this.dataSourceMat.data.filter(item => item !== element);
        },
        error => {
          console.log(error);
        }
      )
    } 
    else {
      console.log(loanObject.taskDetails.id);
      this.service.unclaim(loanObject.taskDetails.id).subscribe(
        (response) => {
          console.log(response);
          this.dataSourceMat.data = this.dataSourceMat.data.filter(item => item !== element);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  onIbuttonClick(element:any){
    const loanObject = this.getData(element);
    this.service.setLoanObject(loanObject);
    this.router.navigate(['/dashboard/information-details'])
  }

  printList:any[] = [];

  onSelect(element: any) {
    const index = this.printList.findIndex(item => item === element.bussinessKey);
    if (index === -1) {
      this.printList.push(element.bussinessKey);
    } else {
      this.printList.splice(index, 1);
    }
    console.log("Selected items:", this.printList);
  }

  onDownload(){
    if(this.printList.length==0){
      console.log("please select items to print");
    }
    else{
      this.service.download(this.printList).subscribe(
        response =>{
          console.log("download success",response);
          alert("pdf downloaded sucessfully, please check c:/opt/pdfGeneration");

        },
        error =>{
          console.log("download fail error ",error);
          alert("something went wrong");
        }
      )
    }
    
  }

  getData(element: any) {
    for (let listItem of this.list) {
      if (element.bussinessKey === listItem.loanDetails.businessKey) {
        return listItem;
      }
    }
  }
  checkTable(){
    if(this.dataSourceMat.data.length==0){
      return false;
    }
    else {
      return true;
    }
  }
  
}
