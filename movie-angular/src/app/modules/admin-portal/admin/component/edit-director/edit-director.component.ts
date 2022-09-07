import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieDirectorService } from '../../../service/movie-director.service';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { MyErrorStateMatcher } from '../../../util/MyErrorStateMatcher';
import { AddCastMovieComponent } from '../add-cast-movie/add-cast-movie.component';

@Component({
  selector: 'app-edit-director',
  templateUrl: './edit-director.component.html',
  styleUrls: ['./edit-director.component.css'],
  providers: [{
    provide: DateAdapter,
    useClass: MomentDateAdapter,
    deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
  },
  { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }]
})
export class EditDirectorComponent implements OnInit {
  directorForm = new FormGroup({
    avatar: new FormControl(null),
    name: new FormControl(null, [Validators.required]),
    birthday: new FormControl(null, [Validators.required]),
    story: new FormControl(null)
  });
  maxDate!: Date;
  matcher = new MyErrorStateMatcher();
  director = {
    "id": null,
    "avatar": '',
    "name": null,
    "story": null,
    "birthday": new Date()
  };
  directorList: any;
  directorImage: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  fileToUpload!: File;
  submitted = false;

  constructor(private directorService: MovieDirectorService,
    public dialogRef: MatDialogRef<AddCastMovieComponent>,
    private uploadImage: ImageService,
    private loginService: LoginServiceService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  async ngOnInit() {
    this.maxDate = new Date((new Date().getFullYear() - 8), new Date().getMonth(), new Date().getDate());
    this.setDirector();
    this.directorForm = new FormGroup({
      avatar: new FormControl(null),
      name: new FormControl(this.director.name, [Validators.required]),
      birthday: new FormControl(this.director.birthday, [Validators.required]),
      story: new FormControl(this.director.story)
    });
    await this.getAvatar();
  }
  setDirector() {
    this.director.id = this.data.id;
    this.director.avatar = this.data.avatar;
    this.director.name = this.data.name;
    this.director.birthday = new Date(this.data.birthday);
    this.director.story = this.data.story;
  }

  async getAvatar() {
    await this.loginService.getAccImage(this.director.avatar).toPromise().then((result: any) => {
      this.directorImage = result;
    });
  }

  async saveDirector() {
    this.submitted = true;
    this.setEditDirector();
    if (this.directorForm.valid) {
      if (this.directorForm.value.avatar) {
        await this.uploadImage.uploadImageAll(this.fileToUpload, UTIL.DEFAULT_IMAGE_DIRECTOR).toPromise().then(((result: any) => {
          this.director.avatar = result.path;
        }));
      }
      await this.directorService.editCast(this.director).toPromise().then((data: any) => {
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
  setEditDirector() {
    this.director.name = this.directorForm.value.name;
    this.director.story = this.directorForm.value.story;
    this.director.birthday = this.directorForm.value.birthday;
  }

  onNoClick() {
    this.directorService.getDirector().subscribe(((data: any) => {
      this.directorList = data;
      this.dialogRef.close(this.directorList);
    }));
  }

  handleFileUpload(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.fileToUpload = files.item(0) as File;
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.directorImage = new ImageModel(this.fileToUpload.name, event.target.result);
    };
    reader.readAsDataURL(this.fileToUpload);
  }
}
