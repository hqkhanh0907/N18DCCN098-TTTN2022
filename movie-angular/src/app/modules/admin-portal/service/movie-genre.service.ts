import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MovieGenre} from '../model/MovieGenre';


@Injectable({
  providedIn: `root`
})
export class MovieGenreService {
  public headers: any | null = sessionStorage.getItem(`token`);
  name = {name: ``};
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) {
  }

  public getGenre(): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/genre/getAll`,
      this.httpOptions
    );
  }

  public getGenreById(id: number): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/genre/getById/` + id,
      this.httpOptions
    );
  }

  public addGenre(name: MovieGenre): Observable<any> {
    // @ts-ignore
    this.name.name = name.name;
    console.log(JSON.stringify(this.name));
    return this.httpClient.post(
      `http://localhost:8080/api/genre/create`,
      JSON.stringify(this.name), this.httpOptions);
  }

  public getAllGenreByMovieId(id: number) {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(`http://localhost:8080/api/fkGenre/getAllGenre/${id}`, this.httpOptions);
  }
}
