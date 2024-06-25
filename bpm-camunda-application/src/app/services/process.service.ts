import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {
  processUrl="http://10.0.0.53:8081/loan/loanRequest"

  constructor(private http:HttpClient) { }

  process(data: any): Observable<any> {
    return this.http.post<any>(this.processUrl, data);
  }
}
