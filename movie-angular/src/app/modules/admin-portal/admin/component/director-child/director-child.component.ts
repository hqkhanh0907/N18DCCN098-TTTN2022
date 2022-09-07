import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { Movie } from '../../../model/movie';
import { MovieDirectorService } from '../../../service/movie-director.service';
import { EditCastComponent } from '../edit-cast/edit-cast.component';
import { EditDirectorComponent } from '../edit-director/edit-director.component';

@Component({
  selector: 'app-director-child',
  templateUrl: './director-child.component.html',
  styleUrls: ['./director-child.component.css']
})
export class DirectorChildComponent implements OnInit {
  @Input() director: any;
  @Output() deleteMovieEvent = new EventEmitter<Movie>();
  @Output() editMovieEvent = new EventEmitter<Boolean>();
  avatarModel: ImageModel = new ImageModel(UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  rate: any;

  constructor(private directorService: MovieDirectorService, private loginService: LoginServiceService, private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAvatar();
  }

  async getAvatar() {
    await this.loginService.getAccImage(this.director.avatar).toPromise().then((result: any) => {
      this.avatarModel = result;
    });
  }

  deleteDirector(id: number) {
    UtilClass.showRequestDeleteMovie(UTIL.ALERT_MESSAGE_DELETE_CAST).then((result) => {
      if (result) {
        this.directorService.deleteDirector(id).subscribe((data: any) => {
          if (data === true) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_REMOVE_DIRECTOR);
            this.deleteMovieEvent.emit(this.director);
          } else {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
          }
        });
      }
    });
  }

  detailDirector() {
    const dialogRef = this.matDialog.open(EditDirectorComponent, {
      data: this.director,
      disableClose: true
    });
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value !== undefined) {
        this.editMovieEvent.emit(true);
      } else {
        this.editMovieEvent.emit(false);
      }
    });
  }

}
