import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DATE_PIPE_DEFAULT_OPTIONS } from '@angular/common';
@Injectable({
  providedIn: 'root'
})
export class ClaimService {
   url='http://localhost:8081/loan/claim';
   unclaimurl='http://localhost:8081/loan/unclaim/';

   unassignTask = "http://localhost:8081/loan/unassigntask";
   assignTask = "http://localhost:8081/loan/assigntask";
   completeTaskurl = "http://localhost:8081/loan/task/complete"
   downloandUrl = "http://10.0.0.38:8083/download/pdf";

   loanData:any;

  constructor(private http:HttpClient) { }

  claim(data: any): Observable<any> {
    console.log('claim Data Sent - ', data); 
    return this.http.post<any>(this.url, data);
  }

  unclaim(data:any):Observable<any>
  {
    const url = `${this.unclaimurl}${data}`;
    console.log(url);
    return this.http.get<any>(url);
  }

  getUnassignTask(data:any):Observable<any>{
   return this.http.post<any>(this.unassignTask,data);
  }

  getAssignTask(data:any):Observable<any>{
    return this.http.post<any>(this.assignTask,data);
  }

  completeTask(data:any):Observable<any>{
    return this.http.post<any>(this.completeTaskurl,data);
  }

  download(businessKeys: string[]): Observable<Blob> {
    let params = new HttpParams();
    businessKeys.forEach(key => {
      params = params.append('businessKey', key);
    });

    return this.http.get('http://localhost:8081/download/pdf', {
      responseType: 'blob', // Set response type to Blob
      params: params
    });
  }
  setLoanObject(data:any){
    this.loanData = data;
   }
   getLoanObject(): any{
    return this.loanData;
   }
}
