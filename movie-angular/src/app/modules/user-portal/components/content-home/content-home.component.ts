import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/service/shared/movie.service';

@Component({
  selector: 'app-content-home',
  templateUrl: './content-home.component.html',
  styleUrls: ['./content-home.component.css'],
})
export class ContentHomeComponent implements OnInit {
  public movies: any[] = [];
  public popularMovies: any[] = [];
  constructor(private movieService: MovieService) { }

  async ngOnInit() {
    await this.getMovies();
    await this.getPopularMovies();
    
  }
  async getPopularMovies() {
    await this.movieService
      .getPopularMovie()
      .toPromise()
      .then((data: []) => {
        for (let item of data) {
          this.popularMovies.push(item);
        }
      });
  }
  async getMovies() {
    await this.movieService
      .getAllMovie()
      .toPromise()
      .then((data: []) => {
        for (let item of data) {
          this.movies.push(item);
        }
      });
  }
  checkMovie(): boolean {
    if (this.movies === null) {
      return false;
    } else {
      if (this.movies.length === 0) {
        return false;
      } else {
        return true;
      }
    }
  }
  checkPopularMovie(): boolean {
    if (this.popularMovies === null) {
      return false;
    } else {
      if (this.popularMovies.length === 0) {
        return false;
      } else {
        return true;
      }
    }
  }
  sendMovies() {
    let listMovie = [];
    for (let movie of this.movies) {
      listMovie.push(this.setMovie(movie));
    }
    return listMovie;
  }
  setMovie(movie: any) {
    let reMovie = {
      "id": null,
      "name": null,
      "image_showing": null,
      "poster": null,
      "slug": null,
      "description": null,
      "quality": null,
      "movieStatus": null,
      "linkTrailer": null,
      "linkMovie": null,
      "releaseDate": null,
      "movieDuration": null,
      "viewNumber": null,
      "translationStatus": null,
      "countryCode": null,
      "moviePrice": null
    }
    reMovie.id = movie.id;
    reMovie.name = movie.name;
    reMovie.image_showing = movie.image_showing;
    reMovie.poster = movie.poster;
    reMovie.slug = movie.slug;
    reMovie.description = movie.description;
    reMovie.quality = movie.quality;
    reMovie.movieStatus = movie.movieStatus;
    reMovie.linkTrailer = movie.linkTrailer;
    reMovie.linkMovie = movie.linkMovie;
    reMovie.releaseDate = movie.releaseDate;
    reMovie.movieDuration = movie.movieDuration;
    reMovie.viewNumber = movie.viewNumber;
    reMovie.translationStatus = movie.id;
    reMovie.countryCode = movie.countryCode;
    reMovie.moviePrice = movie.moviePrice;
    return reMovie;
  }
}
