import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MovieDirectorService } from '../../../service/movie-director.service';
import { AddCastMovieComponent } from '../add-cast-movie/add-cast-movie.component';
import { AddDirectorMovieComponent } from '../add-director-movie/add-director-movie.component';

@Component({
  selector: 'app-director',
  templateUrl: './director.component.html',
  styleUrls: ['./director.component.css']
})
export class DirectorComponent implements OnInit {
  p: any;
  directors: any;
  searchDirector = '';

  constructor(private directorService: MovieDirectorService, private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAllCast();
  }
  async getAllCast() {
    await this.directorService.getDirector().toPromise().then(
      (value: any) => {
        this.directors = value;
      }
    );
  }
  onCreate() {
    const dialogRef = this.matDialog.open(AddDirectorMovieComponent, { disableClose: true });
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value) {
        this.directors = value;
      }
    });
  }

  async updateAfterEdit(event: any) {
    if (event) {
      await this.getAllCast();
    }
  }
  updateAfterDelete($event: { id: number; }) {
    let id;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.directors.length; i++) {
      if (this.directors[i].id === $event.id) {
        id = i;
      }
    }
    this.directors.splice(Number(id), 1);
  }

}
