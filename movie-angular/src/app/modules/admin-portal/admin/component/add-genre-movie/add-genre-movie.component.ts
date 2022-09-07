import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieGenre } from '../../../model/MovieGenre';
import { MovieGenreService } from '../../../service/movie-genre.service';
import { MyErrorStateMatcher } from '../../../util/MyErrorStateMatcher';

@Component({
  selector: 'app-add-genre-movie',
  templateUrl: './add-genre-movie.component.html',
  styleUrls: ['./add-genre-movie.component.css']
})
export class AddGenreMovieComponent implements OnInit {
  genreList: MovieGenre[] = [];
  genreForm!: FormGroup;
  matcher = new MyErrorStateMatcher();
  genre!: MovieGenre;
  checkAdd = false;

  constructor(private movieGenre: MovieGenreService,
              public dialogRef: MatDialogRef<AddGenreMovieComponent>,
              @Inject(MAT_DIALOG_DATA) public data: MovieGenre[]) {
  }

  ngOnInit() {
    this.genreForm = new FormGroup({
      genreName: new FormControl('', [Validators.required])
    });
    this.genreList = this.data;
  }

  async saveGene() {
    if (this.genreForm.valid) {
      this.genre = new MovieGenre(Number(), this.genreForm.value.genreName);
      this.movieGenre.addGenre(this.genre).subscribe((data: any) => {
          if (data.statusCode === undefined) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_GENRE);
          } else {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
          }
        },
        (error => {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, error.message);
        }));
    }
  }

  onNoClick() {
    this.movieGenre.getGenre().subscribe((data: any) => {
      this.genreList = data;
      this.dialogRef.close(this.genreList);
    });
  }
}
