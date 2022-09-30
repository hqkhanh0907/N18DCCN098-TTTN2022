import {Component, Input, OnInit} from '@angular/core';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { MovieCast } from '../../../model/MovieCast';
import { MovieCastService } from '../../../service/movie-cast.service';

@Component({
  selector: 'app-cast',
  templateUrl: './cast.component.html',
  styleUrls: ['./cast.component.css']
})
export class CastComponent implements OnInit {
  @Input() cast: any;
  castInHere: any;
  date!: string;
  imageCast: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);

  constructor(private loginService: LoginServiceService,
              private castService: MovieCastService) {
  }

  async ngOnInit() {
    await this.getCast();
    await this.getAvatar();
  }

  async getCast() {
    await this.castService.getCastById(this.cast).toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.castInHere = data;
      }
    });
  }

  async getAvatar() {
    await this.loginService.getAccImage(this.castInHere.avatar).toPromise().then((result: any) => {
      this.imageCast = result;
    });
  }
}
