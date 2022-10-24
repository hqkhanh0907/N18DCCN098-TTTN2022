import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  public headers: any | null = sessionStorage.getItem(`token`);
  name = { name: `` };
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) { }

  public getChartByYear(): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/bill/getChartByYear`,
      this.httpOptions
    );
  }
  public getListYear(): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/bill/getListYear`,
      this.httpOptions
    );
  }
  public getChartByMonth(year: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/bill/getChartByMonth/${year}`,
      this.httpOptions
    );
  }
}
