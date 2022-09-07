import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MovieCastService } from '../../../service/movie-cast.service';
import { AddCastMovieComponent } from '../add-cast-movie/add-cast-movie.component';

@Component({
  selector: 'app-page-cast',
  templateUrl: './page-cast.component.html',
  styleUrls: ['./page-cast.component.css']
})
export class PageCastComponent implements OnInit {
  p: any;
  casts: any;
  searchCast = '';

  constructor(private castService: MovieCastService, private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAllCast();
  }
  async getAllCast() {
    await this.castService.getCast().toPromise().then(
      (value: any) => {
        this.casts = value;
      }
    );
  }
  onCreate() {
    const dialogRef = this.matDialog.open(AddCastMovieComponent, { disableClose: true });
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value) {
        this.casts = value;
      }
    });
  }

  async updateAfterEdit(event: any) {
    console.log(event);
    if (event) {
      await this.getAllCast();
    }
  }
  updateAfterDelete($event: { id: number; }) {
    let id;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.casts.length; i++) {
      if (this.casts[i].id === $event.id) {
        id = i;
      }
    }
    this.casts.splice(Number(id), 1);
  }

}
