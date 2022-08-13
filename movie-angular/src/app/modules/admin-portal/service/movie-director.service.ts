import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MovieDirector} from '../model/MovieDirector';

@Injectable({
  providedIn: `root`
})
export class MovieDirectorService {

  public headers: any | null = sessionStorage.getItem(`token`);
  director = {avatar: ``, name: ``, story: ``, birthday: new Date()};
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set(`Content-Type`, `application/json`),
  };

  constructor(private httpClient: HttpClient) {
  }

  public getDirector(): Observable<any> {
    this.headers = sessionStorage.getItem(`token`);
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set(`Content-Type`, `application/json`),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/director/getAll`,
      this.httpOptions
    );
  }

  public getDirectorByMovieId(id: number) {
    this.httpOptions = {
      headers: new HttpHeaders({
      }).set(`Content-Type`, `application/json`),
    };
    console.log('service director', id);
    return this.httpClient.get(`http://localhost:8080/api/fkDirector/director/${id}`, this.httpOptions);
  }

  public addDirector(movieDirector: MovieDirector) {
    if (sessionStorage.getItem(`token`)) {
      this.headers = sessionStorage.getItem(`token`);
    }
    this.director.avatar = movieDirector.avatar;
    this.director.name = movieDirector.name;
    this.director.story = movieDirector.story;
    this.director.birthday = movieDirector.birthday;
    console.log(JSON.stringify(this.director));
    return this.httpClient.post(
      `http://localhost:8080/api/director/create`,
      JSON.stringify(this.director), this.httpOptions);
  }
}
