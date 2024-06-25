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







import { Component, Input, SimpleChanges, ViewChild, AfterViewInit, OnChanges } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnChanges, AfterViewInit {
  @Input() dataSource: any[] = [];
  @Input() showInfoIcon: boolean = true;
  @Input() buttonLabel: string = 'claim';
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  displayedColumns: string[] = [];
  dataSourceMat!: MatTableDataSource<any>;

  constructor() {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['dataSource'] && changes['dataSource'].currentValue) {
      console.log('New data received:', changes['dataSource'].currentValue);
      this.displayedColumns = ['infoIcon', ...Object.keys(changes['dataSource'].currentValue[0]), 'action'];
      console.log('Displayed columns:', this.displayedColumns);
      this.dataSourceMat = new MatTableDataSource<any>(changes['dataSource'].currentValue);
      console.log('MatTableDataSource created:', this.dataSourceMat);
    }
  }

  ngAfterViewInit() {
    if (this.dataSourceMat) {
      this.dataSourceMat.paginator = this.paginator;
    }
  }

  onButtonClick(element: any) {
    console.log(`${this.buttonLabel} button clicked for element:`, element);
  }
}

