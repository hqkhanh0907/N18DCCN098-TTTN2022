import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/service/shared/movie.service';

@Component({
  selector: 'app-search-movie',
  templateUrl: './search-movie.component.html',
  styleUrls: ['./search-movie.component.css']
})
export class SearchMovieComponent implements OnInit {
  search: any;
  movies: any[] = [];

  constructor(private movieService: MovieService) { }

  async ngOnInit() {
    this.search = sessionStorage.getItem('movie-search');
    await this.searchMovie();
  }
  async searchMovie() {
    await this.movieService.searchMovie(this.search).toPromise().then((data: any[]) => {
      if(data && data.length > 0) {
        for(let item of data) {
          this.movies.push(item);
        }
      }
    });
  }
  checkMovie(): boolean {
    if(this.movies) {
      if(this.movies.length > 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
