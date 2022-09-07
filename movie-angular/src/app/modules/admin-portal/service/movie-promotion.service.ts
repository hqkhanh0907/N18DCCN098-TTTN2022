import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MoviePromotionService {
  public headers: any | null = sessionStorage.getItem(`token`);
  name = { name: `` };
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) {
  }

  deleteGenre(id: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/promotion/all`,
      this.httpOptions
    );
  }
  getPromotions(): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/promotion/all`,
      this.httpOptions
    );
  }
  addPromotions(promo: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.post<any>(
      `http://localhost:8080/api/promotion/add`, JSON.stringify(promo),
      this.httpOptions
    );
  }
  editPromotions(promo: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.put<any>(
      `http://localhost:8080/api/promotion/edit`, JSON.stringify(promo),
      this.httpOptions
    );
  }
}