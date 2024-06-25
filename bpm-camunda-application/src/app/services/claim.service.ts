import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DATE_PIPE_DEFAULT_OPTIONS } from '@angular/common';
@Injectable({
  providedIn: 'root'
})
export class ClaimService {
   url='http://10.0.0.51:8081/loan/claim'
   unclaimurl='http://localhost:8081/api1'
  constructor(private http:HttpClient) { }

claim(data: any): Observable<any> {
    console.log('claim Data Sent:', data); 
    return this.http.post<any>(this.url, data);
  }
  unclaim(data:any):Observable<any>
  {
    return this.http.post<any>(this.unclaimurl,data);
  }
}
