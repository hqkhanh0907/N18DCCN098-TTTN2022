import { Component, OnInit } from '@angular/core';
import { MovieGenreService } from 'src/app/modules/admin-portal/service/movie-genre.service';
import { MovieService } from 'src/app/service/shared/movie.service';

@Component({
  selector: 'app-content-movie',
  templateUrl: './content-movie.component.html',
  styleUrls: ['./content-movie.component.css']
})
export class ContentMovieComponent implements OnInit {
  moviesAll: any;
  moviesCartoon: any;
  moviesCreepy: any;
  genre: any;
  genres: any[] = [];
  country: any;
  countries: any[] = [];
  allCountries: any[] = [];
  countriesCode: any[] = [];
  showListMovie: any;
  isLoading = false;
  constructor(private movieService: MovieService,
    private genreService: MovieGenreService) { }

  async ngOnInit() {
    this.setActiveItem('all');
    await this.getMovies();
    await this.getGenres();
    await this.getAllCountries();
    await this.getCountriesInMovie();
    if (this.moviesAll) {
      this.setShowListMovie(this.moviesAll);
    }
    this.setCountries();
    this.isLoading = true;
  }
  setCountries() {
    this.countries = [];
    for (let countryCode of this.countriesCode) {
      for (let country of this.allCountries) {
        if (countryCode === country.alpha3Code) {
          this.countries.push(country);
        }
      }
    }
  }
  async getCountriesInMovie() {
    await this.movieService.getCountriesInMovie().toPromise().then((data: any) => {
      if (data) {
        this.countriesCode = data;
      }
    });
  }
  async getAllCountries() {
    await this.movieService.getCountries().toPromise().then((data: any) => {
      if (data) {
        this.allCountries = data;
      }
    });
  }
  async getGenres() {
    await this.genreService.getGenre().toPromise().then((data: any) => {
      if (data?.statusCode === undefined) {
        this.genres = data;
      }
    });
  }
  async showListGenre(genre: any) {
    if (!this.genre) {
      this.genre = genre;
    }
    this.removeItem();
    if (this.country) {
      await this.movieService.getMovieByGenreIdAndCountryCode(this.genre.id, this.country.alpha3Code)
        .toPromise().then((data: any) => {
          if (data) {
            this.showListMovie = [];
            this.showListMovie = data;
          }
        });
    } else {
      await this.movieService.getMovieByGenreId(genre.id).toPromise().then((data: any) => {
        if (data) {
          this.showListMovie = [];
          this.showListMovie = data;
        }
      });
    }
  }
  async showListCountry(country: any) {
    if (!this.country) {
      this.country = country;
    }
    this.removeItem();
    if (this.genre) {
      await this.movieService.getMovieByGenreIdAndCountryCode(this.genre.id, this.country.alpha3Code)
        .toPromise().then((data: any) => {
          if (data) {
            this.showListMovie = [];
            this.showListMovie = data;
          }
        });
    }
    else {
      await this.movieService.getMovieByCountryCode(country.alpha3Code).toPromise().then((data: any) => {
        if (data) {
          console.log('data country', data);
          this.showListMovie = [];
          this.showListMovie = data;
        }
      });
    }
  }
  async getMovies() {
    await this.movieService.getAllMovie().toPromise().then((data: any) => {
      if (!data.statusCode) {
        this.moviesAll = data;
        this.moviesCreepy = data;
        this.moviesCartoon = data;
      }
    });
  }
  setActiveItem(item: string) {
    this.removeItem();
    sessionStorage.setItem('movie-bar-item', item);
  }
  removeItem() {
    sessionStorage.removeItem('movie-bar-item');
  }
  getActive(active: string): string {
    let item = sessionStorage.getItem('movie-bar-item');
    return (item && active === String(item)) ? 'active' : '';
  }
  getActiveGenre(): string {
    return this.genre ? 'active-btn' : '';
  }
  getActiveCountry(): string {
    return this.country ? 'active-btn' : '';
  }
  getActiveGenreBtn(item: any): string {
    if (this.genre) {
      let btn = (Number(this.genre.id) === Number(item.id)) ? 'active-btn' : null;
      return String(btn);
    } else {
      return '';
    }
  }
  getActiveCountryBtn(item: any): string {
    if (this.country) {
      let btn = (String(this.country.alpha3Code) === String(item.alpha3Code)) ? 'active-btn' : null;
      return String(btn);
    } else {
      return '';
    }
  }
  removeGenre() {
    this.genre = null;
    if (this.country) {
      this.showListCountry(this.country);
    }
    else {
      this.setActiveItem('all');
      this.showList('all');
    }
  }
  removeCountry() {
    this.country = null;
    if (this.genre) {
      this.showListGenre(this.genre);
    }
    else {
      this.setActiveItem('all');
      this.showList('all');
    }
  }
  setShowListMovie(movies: any) {
    this.showListMovie = [];
    for (let movie of movies) {
      this.showListMovie.push(movie);
    }
  }
  refreshGenreAndCountry() {
    this.genre = null;
    this.country = null;
  }
  async showList(item: string) {
    this.setActiveItem(item);
    this.refreshGenreAndCountry();
    switch (item) {
      case 'all':
        {
          this.setShowListMovie(this.moviesAll);
          break;
        }
      case 'cartoon':
        {
          this.setShowListMovie(this.moviesCartoon);
          break;
        }
      case 'creepy':
        {
          this.setShowListMovie(this.moviesCreepy);
          break;
        }
    }
  }
}
