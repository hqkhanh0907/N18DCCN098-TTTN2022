import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MovieGenre } from '../model/MovieGenre';


@Injectable({
  providedIn: `root`
})
export class MovieGenreService {
  public headers: any | null = sessionStorage.getItem(`token`);
  name = { name: `` };
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) {
  }

  public getGenre(): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/genre/getAll`,
      this.httpOptions
    );
  }
  public deleteGenre(id: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.delete<any>(
      `http://localhost:8080/api/genre/remove/${id}`,
      this.httpOptions
    );
  }

  public getGenreById(id: number): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/genre/getById/` + id,
      this.httpOptions
    );
  }

  public addGenre(name: MovieGenre): Observable<any> {
    // @ts-ignore
    this.name.name = name.name;
    return this.httpClient.post(
      `http://localhost:8080/api/genre/create`,
      JSON.stringify(this.name), this.httpOptions);
  }
  public editGenre(genre: any): Observable<any> {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    }
    return this.httpClient.put(
      `http://localhost:8080/api/genre/edit`,
      JSON.stringify(genre), this.httpOptions);
  }

  public getAllGenreByMovieId(id: number) {
    this.httpOptions = {
      headers: new HttpHeaders().set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get(`http://localhost:8080/api/fkGenre/getAllGenre/${id}`, this.httpOptions);
  }
}
