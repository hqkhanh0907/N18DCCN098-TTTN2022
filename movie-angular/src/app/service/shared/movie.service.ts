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
    name: new FormControl(null, Validators.required),
    poster: new FormControl(null, Validators.required),
    slug: new FormControl(null, Validators.required),
    image_showing: new FormControl(null, Validators.required),
    description: new FormControl(null, Validators.required),
    movieStatus: new FormControl(null, Validators.required),
    quality: new FormControl(null, Validators.required),
    linkTrailer: new FormControl(null),
    linkMovie: new FormControl(null, Validators.required),
    movieDuration: new FormControl(null, Validators.required),
    translationStatus: new FormControl(null, Validators.required),
    countryCode: new FormControl(null, Validators.required),
    moviePrice: new FormControl(null, Validators.required),
    releaseDate: new FormControl(null, Validators.required),
  });
  private httpOptionsBasic = {
    headers: new HttpHeaders().set('Content-Type', 'application/json'),
  };

  constructor(private httpClient: HttpClient) { }

  initializeFormGroup() {
    this.formMovie = new FormGroup({
      name: new FormControl(null, Validators.required),
      poster: new FormControl(null, Validators.required),
      image_showing: new FormControl(null, Validators.required),
      slug: new FormControl(null, Validators.required),
      description: new FormControl(null, Validators.required),
      movieStatus: new FormControl(null, Validators.required),
      quality: new FormControl(null, Validators.required),
      linkTrailer: new FormControl(null),
      linkMovie: new FormControl(null, Validators.required),
      movieDuration: new FormControl(null, Validators.required),
      translationStatus: new FormControl(null, Validators.required),
      countryCode: new FormControl(null, Validators.required),
      moviePrice: new FormControl(null, Validators.required),
      releaseDate: new FormControl(null, Validators.required),
    });
  }
  editFormGroup(movie: any) {
    this.formMovie = new FormGroup({
      name: new FormControl(movie.name, Validators.required),
      poster: new FormControl(movie.poster, Validators.required),
      image_showing: new FormControl(movie.image_showing, Validators.required),
      slug: new FormControl(movie.slug, Validators.required),
      description: new FormControl(movie.description, Validators.required),
      movieStatus: new FormControl(movie.movieStatus, Validators.required),
      quality: new FormControl(movie.quality, Validators.required),
      linkTrailer: new FormControl(movie.linkTrailer),
      linkMovie: new FormControl(movie.linkMovie, Validators.required),
      movieDuration: new FormControl(movie.movieDuration, Validators.required),
      translationStatus: new FormControl(movie.translationStatus, Validators.required),
      countryCode: new FormControl(movie.countryCode, Validators.required),
      moviePrice: new FormControl(movie.moviePrice, Validators.required),
      releaseDate: new FormControl(new Date(movie.releaseDate), Validators.required),
    });
  }

  public getMovie(): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieDetailAll',
      this.httpOptions
    );
  }
  public getAllMovie(): Observable<any> {
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieDetailAll'
    );
  }

  public getMovieByName(name: string): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/movieDetail/getMovieDetailByName?name=${name}`,
      this.httpOptions
    );
  }
  public getFollow(favoriteId: any): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.post<any>(
      'http://localhost:8080/api/acc/getFollow', favoriteId,
      this.httpOptions
    );
  }
  public addInfoBill(billInfo: any): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.post<any>(
      'http://localhost:8080/api/movieDetail/addInfoBill',
      JSON.stringify(billInfo), this.httpOptions
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
    this.httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      'http://localhost:8080/api/movieDetail/getMovieRate/' + id,
      this.httpOptions
    );
  }
  public checkPay(accountId: any, movieId: any): Observable<any> {
    this.headers = sessionStorage.getItem('token');
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: this.headers,
      }).set('Content-Type', 'application/json'),
    };
    return this.httpClient.get<any>(
      `http://localhost:8080/api/bill/checkpay/${accountId}/${movieId}`,
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
  public follow(favorite: any) {
    if (sessionStorage.getItem('token')) {
      this.headers = 'Bearer ' + sessionStorage.getItem('token');
    }
    return this.httpClient.post(
      `http://localhost:8080/api/acc/follow`,
      JSON.stringify(favorite),
      this.httpOptions
    );
  }
  public unFollow(favoriteId: any) {
    if (sessionStorage.getItem('token')) {
      this.headers = 'Bearer ' + sessionStorage.getItem('token');
    }
    return this.httpClient.post(
      `http://localhost:8080/api/acc/un-follow`,
      JSON.stringify(favoriteId),
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

  public addGenreToMovie(fkGenre: any) {
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
