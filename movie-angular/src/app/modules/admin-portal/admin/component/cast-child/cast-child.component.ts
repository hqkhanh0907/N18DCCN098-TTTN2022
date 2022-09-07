import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { Movie } from '../../../model/movie';
import { MovieCastService } from '../../../service/movie-cast.service';
import { EditCastComponent } from '../edit-cast/edit-cast.component';

@Component({
  selector: 'app-cast-child',
  templateUrl: './cast-child.component.html',
  styleUrls: ['./cast-child.component.css']
})
export class CastChildComponent implements OnInit {
  @Input() cast: any;
  @Output() deleteMovieEvent = new EventEmitter<Movie>();
  @Output() editMovieEvent = new EventEmitter<Boolean>();
  avatarModel: ImageModel = new ImageModel(UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  rate: any;

  constructor(private castService: MovieCastService, private loginService: LoginServiceService, private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAvatar();
  }

  async getAvatar() {
    await this.loginService.getAccImage(this.cast.avatar).toPromise().then((result: any) => {
      this.avatarModel = result;
    });
  }

  deleteCast(id: number) {
    UtilClass.showRequestDeleteMovie(UTIL.ALERT_MESSAGE_DELETE_CAST).then((result) => {
      if (result) {
        this.castService.deleteCast(id).subscribe((data: any) => {
          if (data === true) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_REMOVE_CAST);
            this.deleteMovieEvent.emit(this.cast);
          } else {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
          }
        });
      }
    });
  }

  detailCast(id: number) {
    const dialogRef = this.matDialog.open(EditCastComponent, {
      data: this.cast,
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
