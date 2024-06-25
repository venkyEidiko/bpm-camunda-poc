import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private employeeData: any = null;


  url = "http://10.0.0.51:8081/api/register";
  loginUrl = "http://10.0.0.51:8081/api/login";


  constructor(private http: HttpClient) {}

  register(data: any): Observable<any> {
    console.log('Register Data Sent:', data); 
    return this.http.post<any>(this.url, data);
  }

  login(data: any): Observable<any> {
    return this.http.post<any>(this.loginUrl, data).pipe(
      tap(response => {
        if (response && response.result && response.result.length > 0) {
          this.setEmployeeData(response.result[0]);
        }
      })
    );
  }

  setEmployeeData(data: any) {
    this.employeeData = data;
    localStorage.setItem('employee-data', JSON.stringify(data));
  }

  getEmployeeData(): any {
    if (!this.employeeData) {
      const storedData = localStorage.getItem('employee-data');
      if (storedData) {
        try {
          this.employeeData = JSON.parse(storedData);
        } catch (error) {
          console.error('Error parsing employee data from localStorage:', error);
        }
      }
    }
    return this.employeeData;
  }
}
