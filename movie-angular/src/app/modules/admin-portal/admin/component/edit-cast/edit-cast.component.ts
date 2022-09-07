import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { max } from 'rxjs';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieCast } from '../../../model/MovieCast';
import { MovieCastService } from '../../../service/movie-cast.service';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { MyErrorStateMatcher } from '../../../util/MyErrorStateMatcher';
import { AddCastMovieComponent } from '../add-cast-movie/add-cast-movie.component';

@Component({
  selector: 'app-edit-cast',
  templateUrl: './edit-cast.component.html',
  styleUrls: ['./edit-cast.component.css'],
  providers: [{
    provide: DateAdapter,
    useClass: MomentDateAdapter,
    deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
  },
  { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }]
})
export class EditCastComponent implements OnInit {
  castForm = new FormGroup({
    avatar: new FormControl(null),
    name: new FormControl(null, [Validators.required]),
    birthday: new FormControl(null, [Validators.required]),
    story: new FormControl(null)
  });
  maxDate!: Date;
  matcher = new MyErrorStateMatcher();
  cast = {
    "id": null,
    "avatar": '',
    "name": null,
    "story": null,
    "birthday": new Date()
  };
  castList: MovieCast[] = [];
  castImage: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  fileToUpload!: File;
  submitted = false;

  constructor(private castService: MovieCastService,
    public dialogRef: MatDialogRef<AddCastMovieComponent>,
    private uploadImage: ImageService,
    private loginService: LoginServiceService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  async ngOnInit() {
    this.maxDate = new Date((new Date().getFullYear() - 8), new Date().getMonth(), new Date().getDate());
    this.setCast();
    this.castForm = new FormGroup({
      avatar: new FormControl(null),
      name: new FormControl(this.cast.name, [Validators.required]),
      birthday: new FormControl(this.cast.birthday, [Validators.required]),
      story: new FormControl(this.cast.story)
    });
    await this.getAvatar();
  }
  setCast() {
    this.cast.id = this.data.id;
    this.cast.avatar = this.data.avatar;
    this.cast.name = this.data.name;
    this.cast.birthday = new Date(this.data.birthday);
    this.cast.story = this.data.story;
  }

  async getAvatar() {
    await this.loginService.getAccImage(this.cast.avatar).toPromise().then((result: any) => {
      this.castImage = result;
    });
  }

  async saveCast() {
    this.submitted = true;
    this.setEditCast();
    if (this.castForm.valid) {
      if (this.castForm.value.avatar) {
        await this.uploadImage.uploadImageAll(this.fileToUpload, UTIL.DEFAULT_IMAGE_CAST).toPromise().then(((result: any) => {
          this.cast.avatar = result.path;
        }));
      }
      await this.castService.editCast(this.cast).toPromise().then((data: any) => {
        if (data.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_EDIT_CAST);
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
        }
      },
        (error => {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, error.message);
        }));
    }
  }
  setEditCast() {
    this.cast.name = this.castForm.value.name;
    this.cast.story = this.castForm.value.story;
    this.cast.birthday = this.castForm.value.birthday;
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
