import { Component, OnInit } from '@angular/core';
import { AddMovieComponent } from '../add-movie/add-movie.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Movie } from '../../../model/movie';
import { MovieService } from 'src/app/service/shared/movie.service';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {
  p: any;
  movies: any;
  searchMovie = '';

  constructor(private movieService: MovieService, private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAllMovie();
  }
  async getAllMovie() {
    await this.movieService.getMovie().toPromise().then(
      (value: any) => {
        this.movies = value;
      }
    );
  }
  onCreate() {
    const dialogRef = this.matDialog.open(AddMovieComponent, { disableClose: true });
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value) {
        this.movies = value;
      }
    });
  }

  async updateAfterEdit(event: any) {
    if(event) {
      await this.getAllMovie();
    }
  }
  updateAfterDelete($event: { id: number; }) {
    let id;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.movies.length; i++) {
      if (this.movies[i].id === $event.id) {
        id = i;
      }
    }
    this.movies.splice(Number(id), 1);
  }
}
