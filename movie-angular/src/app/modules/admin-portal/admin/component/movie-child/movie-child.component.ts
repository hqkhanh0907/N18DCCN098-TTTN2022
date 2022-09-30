import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { Movie } from '../../../model/movie';
import {DetailMovieComponent} from '../detail-movie/detail-movie.component';

@Component({
  selector: 'app-movie-child',
  templateUrl: './movie-child.component.html',
  styleUrls: ['./movie-child.component.css']
})
export class MovieChildComponent implements OnInit {
  @Input() movie: any;
  @Output() deleteMovieEvent = new EventEmitter<Movie>();
  @Output() editMovieEvent = new EventEmitter<Boolean>();
  posterModel: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_MOVIE_POSTER_URL);
  rate: any;

  constructor(private movieService: MovieService, private loginService: LoginServiceService, private matDialog: MatDialog) {
  }

  ngOnInit() {
    this.getRates(this.movie.id);
  }

  async getPoster() {
    await this.loginService.getAccImage(this.movie.poster).toPromise().then((result: any) => {
      this.posterModel = result;
    });
  }

  deleteMovie(id: number) {
    UtilClass.showRequestDeleteMovie(UTIL.ALERT_MESSAGE_DELETE_MOVIE).then((result) => {
      if (result) {
        this.movieService.deleteMovie(id).subscribe((data: any) => {
          if (data.statusCode === undefined) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_REMOVE_MOVIE);
            this.deleteMovieEvent.emit(this.movie);
          } else {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
          }
        });
      }
    });
  }

  async getRates(id: number) {
    await this.movieService.getRateMovie(id).toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.rate = data.rate;
      } else {
        this.rate = 0;
      }
    });
  }

  detailMovie(id: number) {
    const dialogRef = this.matDialog.open(DetailMovieComponent, {
      data: this.movie,
      disableClose: true
    });
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value !== undefined) {
        this.editMovieEvent.emit(true);
      } else {
        this.editMovieEvent.emit(false);
      }
    });
  }
}
