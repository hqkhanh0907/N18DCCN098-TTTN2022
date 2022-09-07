import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieGenreService } from '../../../service/movie-genre.service';
import { AddGenreMovieComponent } from '../add-genre-movie/add-genre-movie.component';
import { EditGenreComponent } from '../edit-genre/edit-genre.component';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css']
})
export class GenreComponent implements OnInit {
  submitted = true;
  genres: any[] = [];
  searchGenre = '';
  entries = 10;
  sortedData!: any[];
  p: any;

  constructor(private genreService: MovieGenreService,
    private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAllGenre();
  }

  async getAllGenre() {
    await this.genreService.getGenre().toPromise().then((value: any) => {
      if (value.statusCode === undefined) {
        this.genres = value;
        this.sortedData = this.genres.slice();
      }
    });
  }
  sortData(sort: Sort) {
    const data = this.genres.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return this.compare(a.name, b.name, isAsc);
        default:
          return 0;
      }
    });
  }

  public compare(a: string, b: string, isAsc: boolean) {
    a = a.toLocaleLowerCase();
    b = b.toLocaleLowerCase();
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  reloadAccList() {
    this.getAllGenre().then(() => {
    });
  }

  removeGenre(id: any) {
    UtilClass.showRequestDeleteMovie(UTIL.ALERT_MESSAGE_DELETE_GENRE).then((result: any) => {
      if (result) {
        this.genreService.deleteGenre(id).toPromise().then((value: any) => {
          if (value.statusCode === undefined) {
            this.reloadAccList();
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_REMOVE_ACCOUNT);
          } else {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, value.message);
          }
        });
      }
    });
  }

  openEditGenre(genre: any) {
    const dialogRef = this.matDialog.open(EditGenreComponent, { data: genre, disableClose: true });
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value) {
        this.reloadAccList();
      }
    });
  }

  addGenre() {
    const dialogRef = this.matDialog.open(AddGenreMovieComponent, {});
    dialogRef.afterClosed().subscribe((value: any) => {
      this.reloadAccList();
    });
  }
}