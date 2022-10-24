import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MovieCast } from '../model/MovieCast';

@Injectable({
  providedIn: `root`
})
export class MovieCastService {
  public headers: any | null = sessionStorage.getItem(`token`);
  cast = { avatar: ``, name: ``, story: ``, birthday: new Date() };
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) {
  }

  public getCast(): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/cast/getAll`,
      this.httpOptions
    );
  }
  deleteCast(id: number): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.delete<any>(
      `http://localhost:8080/api/cast/remove/${id}`,
      this.httpOptions
    );
  }

  public getCastById(id: number): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(
      `http://localhost:8080/api/cast/` + id,
      this.httpOptions
    );
  }
  editCast(cast: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders({
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.put(`http://localhost:8080/api/cast/edit`, JSON.stringify(cast), this.httpOptions);
  }

  public addCast(movieCast: any) {
    if (sessionStorage.getItem(`token`)) {
      this.headers = sessionStorage.getItem(`token`);
    }
    this.cast.avatar = movieCast.avatar;
    this.cast.name = movieCast.name;
    this.cast.story = movieCast.story;
    this.cast.birthday = movieCast.birthday;
    return this.httpClient.post(
      `http://localhost:8080/api/cast/create`,
      JSON.stringify(this.cast), this.httpOptions);
  }

  public getCastByMovieId(id: number): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders({
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(`http://localhost:8080/api/fkCast/cast/${id}`, this.httpOptions);
  }
  getNumCast(): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders({
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(`http://localhost:8080/api/cast/getNumCast`, this.httpOptions);
  }
}
