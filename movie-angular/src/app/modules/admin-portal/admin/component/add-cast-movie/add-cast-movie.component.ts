import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DATE_FORMATS} from '@angular/material/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieCast } from '../../../model/MovieCast';
import { MovieCastService } from '../../../service/movie-cast.service';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { MyErrorStateMatcher } from '../../../util/MyErrorStateMatcher';

@Component({
  selector: 'app-add-cast-movie',
  templateUrl: './add-cast-movie.component.html',
  styleUrls: ['./add-cast-movie.component.css'],
  providers: [
    {provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS}
  ]
})
export class AddCastMovieComponent implements OnInit {
  castForm!: FormGroup;
  maxDate!: Date;
  matcher = new MyErrorStateMatcher();
  cast!: MovieCast;
  castList: MovieCast[] = [];
  castImage: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  fileToUpload!: File;
  submitted = false;

  constructor(private castService: MovieCastService,
              public dialogRef: MatDialogRef<AddCastMovieComponent>,
              private uploadImage: ImageService,
              @Inject(MAT_DIALOG_DATA) public data: MovieCast[]) {
  }

  ngOnInit() {
    this.maxDate = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
    this.castForm = new FormGroup({
      avatar: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      birthday: new FormControl('', [Validators.required]),
      story: new FormControl('')
    });
  }

  async saveCast() {
    this.submitted = true;
    if (this.castForm.valid) {
      this.cast = new MovieCast(
        Number(),
        this.castForm.value.avatar,
        this.castForm.value.name,
        this.castForm.value.story,
        this.castForm.value.birthday);
      await this.uploadImage.uploadImageAll(this.fileToUpload,  UTIL.DEFAULT_IMAGE_CAST).toPromise().then(((result: any) => {
        this.cast.avatar = result.path;
      }));
      await this.castService.addCast(this.cast).toPromise().then((data: any) => {
          if (data.statusCode === undefined) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_CAST);
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
    this.castService.getCast().subscribe(((data: any) => {
      this.castList = data;
      this.dialogRef.close(this.castList);
    }));
  }

  handleFileUpload(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.fileToUpload = files.item(0) as File;
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.castImage = new ImageModel(this.fileToUpload.name, event.target.result);
    };
    reader.readAsDataURL(this.fileToUpload);
  }
}
