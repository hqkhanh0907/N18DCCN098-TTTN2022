import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDialogRef} from '@angular/material/dialog';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MovieDirector } from '../../../model/MovieDirector';
import { MovieDirectorService } from '../../../service/movie-director.service';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { MyErrorStateMatcher } from '../../../util/MyErrorStateMatcher';

@Component({
  selector: 'app-add-director-movie',
  templateUrl: './add-director-movie.component.html',
  styleUrls: ['./add-director-movie.component.css'],
  providers: [{
    provide: DateAdapter,
    useClass: MomentDateAdapter,
    deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
  },
  { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }]
})
export class AddDirectorMovieComponent implements OnInit {
  directorForm = new FormGroup({});
  matcher = new MyErrorStateMatcher();
  maxDate!: Date;
  director!: MovieDirector;
  directorList: MovieDirector[] = [];
  directorImage = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  submitted = false;
  fileToUpload!: File;

  constructor(private directorService: MovieDirectorService,
              private uploadImage: ImageService,
              public dialogRef: MatDialogRef<AddDirectorMovieComponent>) {
  }

  ngOnInit() {
    this.maxDate = new Date((new Date().getFullYear() - 8), new Date().getMonth(), new Date().getDate());
    this.directorForm = new FormGroup({
      avatar: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      birthday: new FormControl('', [Validators.required]),
      story: new FormControl('')
    });
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
  async saveDirector() {
    this.submitted = true;
    if (this.directorForm.valid) {
      this.director = new MovieDirector(
        Number(),
        this.directorForm.value.avatar,
        this.directorForm.value.name,
        this.directorForm.value.story,
        this.directorForm.value.birthday);

      await this.uploadImage.uploadImageAll(this.fileToUpload,  UTIL.DEFAULT_IMAGE_DIRECTOR).toPromise().then(((result: any) => {
        this.director.avatar = result.path;
      }));
      this.directorService.addDirector(this.director).subscribe((data: any) => {
          if (data.statusCode === undefined) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_DIRECTOR);
          } else {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
          }
        },
        (error => {
          if (error.statusText === 'OK') {
            console.log(error);
          }
        }));
    }
  }

  onNoClick() {
    this.directorService.getDirector().subscribe(((data: any) => {
      this.directorList = data;
      this.dialogRef.close(this.directorList);
    }));
  }
}
