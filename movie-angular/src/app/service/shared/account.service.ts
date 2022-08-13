
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: `root`,
})
export class AccountService {
  public headers: any | null = sessionStorage.getItem(`token`);
  name = { name: `` };
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) {}

  getAccount(accId: number): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(
      `http://localhost:8080/api/acc/getAccById/` + accId,
      this.httpOptions
    );
  }
  getAllAccount(): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(
      `http://localhost:8080/api/acc/getAllAccount`,
      this.httpOptions
    );
  }

  updateAccount(account: any): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.put(
      `http://localhost:8080/api/acc/edit`,
      JSON.stringify(account),
      this.httpOptions
    );
  }

  deleteAccByUsername(username: string): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.delete(
      `http://localhost:8080/api/acc/deleteAcc/` + username,
      this.httpOptions
    );
  }
  getAllRole(): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(
      `http://localhost:8080/role/getAllRole`,
      this.httpOptions
    );
  }

  addAccount(acc: any) {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.post(
      `http://localhost:8080/api/acc/createAcc/`,
      acc,
      this.httpOptions
    );
  }
  getHistory(historyKey: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.post(
      `http://localhost:8080/api/history/get-history-by-id`, JSON.stringify(historyKey),
      this.httpOptions
    );
  }
  saveHistory(history: any): Observable<any>   {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.post(
      `http://localhost:8080/api/acc/saveHistory`, JSON.stringify(history),
      this.httpOptions
    );
  }
  public getProvinceByCode(code: any): Observable<any> {
    return this.httpClient.get(`https://provinces.open-api.vn/api/p/` + code);
  }
  public getDistrictByCode(code: any): Observable<any> {
    return this.httpClient.get(`https://provinces.open-api.vn/api/d/` + code);
  }
  public getWardByCode(code: any): Observable<any> {
    return this.httpClient.get(`https://provinces.open-api.vn/api/w/` + code);
  }
  public getAllProvince(): Observable<any> {
    return this.httpClient.get(`https://provinces.open-api.vn/api/p`);
  }
  public getAllDistrict(): Observable<any> {
    return this.httpClient.get(`https://provinces.open-api.vn/api/d`);
  }
  public getAllWard(): Observable<any> {
    return this.httpClient.get(`https://provinces.open-api.vn/api/w`);
  }
  public checkOldPass(password: any, username: any): Observable<any> {
    return this.httpClient.post(
      `http://localhost:8080/api/acc/checkPassword/` + username,
      password
    );
  }
  public changePass(password: any, username: any): Observable<any> {
    return this.httpClient.post(
      `http://localhost:8080/api/acc/changePassword/` + username,
      password
    );
  }
}
