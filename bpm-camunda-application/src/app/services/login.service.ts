import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // private employeeData: any = null; 
  // private jwtToken: string | null = null;

  url = "http://10.0.0.53:8081/api/register";
  loginUrl="http://10.0.0.53:8081/api/login";

  constructor(private http: HttpClient) {}

  register(data: any): Observable<any> {
    console.log('Register Data Sent:', data); 
    return this.http.post<any>(this.url, data);
  }
  login(data: any): Observable<any> {
    return this.http.post<any>(this.loginUrl, data);
  }
  // setEmployeeData(data: any) {
  //   this.employeeData = data;
  //   localStorage.setItem('employee-data', JSON.stringify(data));
  // }

  // getEmployeeData(): any {
  //   if (!this.employeeData) {
  //     const storedData = localStorage.getItem('employee-data');
  //     if (storedData) {
  //       try {
  //         this.employeeData = JSON.parse(storedData);
  //       } catch (error) {
  //         console.error('Error parsing employee data from localStorage:', error);
  //       }
  //     }
  //   }
  //   return this.employeeData;
  // }

  // setJwtToken(token: string) {
  //   this.jwtToken = token;
  //   localStorage.setItem('jwt-token', token);
  // }

  // getJwtToken(): string | null {
  //   if (!this.jwtToken) {
  //     this.jwtToken = localStorage.getItem('jwt-token');
  //   }
  //   return this.jwtToken;
  // }

  // getAuthHeaders(): HttpHeaders {
  //   const token = this.getJwtToken();
  //   return new HttpHeaders({
  //     'Authorization': `Bearer ${token}`
  //   });
  // }
}
