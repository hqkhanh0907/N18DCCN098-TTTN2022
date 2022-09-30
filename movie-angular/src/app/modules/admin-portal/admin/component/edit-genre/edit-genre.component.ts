import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieGenreService } from '../../../service/movie-genre.service';
import { MyErrorStateMatcher } from '../../../util/MyErrorStateMatcher';
import { AddGenreMovieComponent } from '../add-genre-movie/add-genre-movie.component';

@Component({
  selector: 'app-edit-genre',
  templateUrl: './edit-genre.component.html',
  styleUrls: ['./edit-genre.component.css']
})
export class EditGenreComponent implements OnInit {
  genreList: any;
  genreForm!: FormGroup;
  matcher = new MyErrorStateMatcher();
  genre: any;
  checkEdit = false;

  constructor(private movieGenre: MovieGenreService,
    public dialogRef: MatDialogRef<AddGenreMovieComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.genreForm = new FormGroup({
      genreName: new FormControl(this.data.name, [Validators.required])
    });
    this.genre = {
      id: this.data.id,
      name: this.data.name
    };
  }

  async saveGene() {
    if (this.genreForm.valid) {
      this.genre.name = this.genreForm.value.genreName;
      this.movieGenre.editGenre(this.genre).subscribe((data: any) => {
        if (data.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_GENRE);
          this.dialogRef.close(true);
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
    this.dialogRef.close(false);
  }
}
