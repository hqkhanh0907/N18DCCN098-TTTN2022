import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  public headers: any | null = sessionStorage.getItem('token');
  private httpOptions = {
    headers: new HttpHeaders({
      Authorization: this.headers,
    }).set('Content-Type', 'application/json'),
  };
  formMovie = new FormGroup({
    title: new FormControl('', [Validators.required]),
    poster: new FormControl('', [Validators.required]),
    details: new FormControl('', [Validators.required]),
    movieStatus: new FormControl(true, [Validators.required]),
    linkTrailer: new FormControl(''),
    linkMovie: new FormControl('', [Validators.required]),
    releaseDate: new FormControl('', [Validators.required]),
  });
  private httpOptionsBasic = {
    headers: new HttpHeaders().set('Content-Type', 'application/json'),
  };

  constructor(private httpClient: HttpClient) {}

  initializeFormGroup() {
    this.formMovie = new FormGroup({
      title: new FormControl('', [Validators.required]),
      poster: new FormControl('', [Validators.required]),
      details: new FormControl('', [Validators.required]),
      movieStatus: new FormControl(true, [Validators.required]),
      linkTrailer: new FormControl(''),
      linkMovie: new FormControl('', [Validators.required]),
      releaseDate: new FormControl('', [Validators.required]),
    });
  }
  editFormGroup(movie: any) {
    this.formMovie = new FormGroup({
      title: new FormControl(movie.title, [Validators.required]),
      poster: new FormControl(movie.poster, [Validators.required]),
      details: new FormControl(movie.detail, [Validators.required]),
      movieStatus: new FormControl(movie.movieStatus, [Validators.required]),
      linkTrailer: new FormControl(movie.linkTrailer),
      linkMovie: new FormControl(movie.linkMovie, [Validators.required]),
      releaseDate: new FormControl(new Date(movie.releaseDate), [
        Validators.required,
      ]),
    });
  }

  public getAllMovie(): Observable<any> {
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieDetailAll'
    );
  }

  public getMovieByTitle(title: string): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieDetailByTitle/' + title,
      this.httpOptions
    );
  }
  public addInfoBill(billInfo: any): Observable<any> {
    return this.httpClient.post<any>(
      'http://localhost:8080/api/movieDetail/addInfoBill',
      JSON.stringify(billInfo)
    );
  }
  public getMovieBySlug(slug: any): Observable<any> {
    let formData: FormData = new FormData();
    formData.append('slug', slug);
    return this.httpClient.post<any>(
      'http://localhost:8080/api/movieDetail/getMovieDetailBySlug',
      formData
    );
  }

  public getMovieById(id: number): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieDetail/' + id,
      this.httpOptions
    );
  }
  public getPromoCode(code: any): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getPromo/' + code,
      this.httpOptions
    );
  }

  public getCountries(): Observable<any> {
    return this.httpClient.get<any>('https://restcountries.com/v2/all');
  }
  public getCountryByAlphaCode(alphaCode: any): Observable<any> {
    return this.httpClient.get<any>(
      'https://restcountries.com/v2/alpha/' + alphaCode
    );
  }
  public getGenreOfMovie(movieId: any): Observable<any> {
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getGenreByMovieId/' + movieId
    );
  }

  public getRateMovie(id: number): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieRate/' + id,
      this.httpOptions
    );
  }

  public addMovie(movie: any) {
    if (sessionStorage.getItem('token')) {
      this.headers = 'Bearer ' + sessionStorage.getItem('token');
    }
    return this.httpClient.post(
      `http://localhost:8080/api/movieDetail/addMovie`,
      JSON.stringify(movie),
      this.httpOptions
    );
  }

  public editMovie(movie: any) {
    if (sessionStorage.getItem('token')) {
      this.headers = sessionStorage.getItem('token');
    }
    return this.httpClient.put(
      `http://localhost:8080/api/movieDetail/editMovieDetail`,
      JSON.stringify(movie),
      this.httpOptions
    );
  }

  public addGenretoMovie(fkGenre: any) {
    if (sessionStorage.getItem('token')) {
      this.headers = sessionStorage.getItem('token');
    }
    return this.httpClient.post(
      `http://localhost:8080/api/movieDetail/addMovie`,
      JSON.stringify(fkGenre),
      this.httpOptions
    );
  }

  public deleteMovie(id: number) {
    if (sessionStorage.getItem('token')) {
      this.headers = sessionStorage.getItem('token');
    }
    return this.httpClient.delete(
      `http://localhost:8080/api/movieDetail/remove/` + id,
      this.httpOptions
    );
  }
}
