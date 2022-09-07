import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AddGenreMovieComponent } from '../add-genre-movie/add-genre-movie.component';
import { AddCastMovieComponent } from '../add-cast-movie/add-cast-movie.component';
import { AddDirectorMovieComponent } from '../add-director-movie/add-director-movie.component';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ListCastMovieComponent } from '../list-cast-movie/list-cast-movie.component';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { MovieGenre } from '../../../model/MovieGenre';
import { MovieCast } from '../../../model/MovieCast';
import { MovieDirector } from '../../../model/MovieDirector';
import { FKCast } from '../../../model/FKCast';
import { FKGenre } from '../../../model/FKGenre';
import { FKDirector } from '../../../model/FKDirector';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
import { MovieService } from 'src/app/service/shared/movie.service';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { MovieDirectorService } from '../../../service/movie-director.service';
import { MovieCastService } from '../../../service/movie-cast.service';
import { MovieGenreService } from '../../../service/movie-genre.service';
import { UtilClass } from 'src/app/shared/util/utilClass';
import slugify from 'slugify';
import * as Plyr from 'plyr';
import { MAT_DATE_FORMATS } from '@angular/material/core';

@Component({
  selector: 'app-detail-movie',
  templateUrl: './detail-movie.component.html',
  styleUrls: ['./detail-movie.component.css'],
  providers: [
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }
  ]
})
export class DetailMovieComponent implements OnInit {

  submitted = false;
  maxDate!: Date;
  movieGenre = new FormControl();
  movieDirector = new FormControl();
  genreList: MovieGenre[] = [];
  castList: MovieCast[] = [];
  directorList: MovieDirector[] = [];
  fkCast: FKCast[] = [];
  fkGenre: FKGenre[] = [];
  fkDirector: FKDirector[] = [];
  fileToUpload!: File;
  movieImage: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_MOVIE_POSTER_URL);
  castListChoosed: any = [];
  countEdit = 0;
  moviePoster: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_MOVIE_POSTER_URL);
  moviePImage: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_MOVIE_SHOWING_URL);
  countries: any;
  movieStatusList = [{ name: 'Premiered', value: 1 }, { name: 'Comming soon', value: 0 }];
  qualities = [{ value: '1080' }, { value: '720' }, { value: '360' }, { value: '240' }];
  translationStatusList = [{ value: 0, name: 'default' }, { value: 1, name: 'dub movie' }, { value: 2, name: 'narration movie' }, { value: 3, name: 'vietsub' }];
  player: any;
  movieDuration: any;
  linkMovie: any;
  checkLink = false;

  constructor(public movieService: MovieService,
    private loginService: LoginServiceService,
    private movieDirectorService: MovieDirectorService,
    private movieCastService: MovieCastService,
    private movieGenreService: MovieGenreService,
    private matDialog: MatDialog,
    public dialogRef: MatDialogRef<DetailMovieComponent>,
    @Inject(MAT_DIALOG_DATA) public movie: any) {
  }

  async ngOnInit() {
    this.movieService.editFormGroup(this.movie);
    this.linkMovie = this.movieService.formMovie.value.linkMovie;
    await this.getImage();
    await this.getAllCountries();
    await this.getGenreList();
    await this.getDirectorList();
    await this.getGenreOfMovie();
    await this.getDirectorOfMovie();
    await this.getCastOfMovie();
    console.log(this.castListChoosed);
  }

  getImage(): string {
    if (this.movieService.formMovie.value.image_showing) {
      return this.movieService.formMovie.value.image_showing;
    } else {
      return this.moviePImage.url;
    }
  }
  getDuration(): any {
    if (this.linkMovie) {
      return this.movieDuration === null ? null : Math.floor(this.movieDuration / 60);
    } else {
      return null;
    }
  }
  async getAllCountries() {
    await this.movieService.getCountries().toPromise().then((data: any) => {
      if (data) {
        this.countries = data;
      }
    });
  }
  getPoster(): string {
    if (this.movieService.formMovie.value.poster) {
      return this.movieService.formMovie.value.poster;
    } else {
      return this.moviePoster.url;
    }
  }

  genreId(id: number): any {
    for (const genre of this.genreList) {
      if (genre.id === id) {
        return genre;
      }
    }
    return null;
  }

  directorId(id: number): any {
    for (const director of this.directorList) {
      if (director.id === id) {
        return director;
      }
    }
    return null;
  }

  async onNoClick() {
    if (this.countEdit === 0) {
      this.dialogRef.close();
    } else if (this.countEdit > 0) {
      await this.movieService.getMovieById(this.movie.id).toPromise().then((data: any) => {
        if (data.statusCode === undefined) {
          this.dialogRef.close(data);
        }
      });
    }
  }

  openCastList() {
    const dialogRef = this.matDialog.open(ListCastMovieComponent, {
      data: this.castListChoosed,
      disableClose: true
    });
    dialogRef.afterClosed().toPromise().then((result: any) => {
      if (result) {
        this.castListChoosed = result;
      }
    });
  }

  removeGenre(id: any) {
    const index = this.movieGenre.value as number[];
    this.removeFirst(index, id);
    this.movieGenre.setValue(index); // To trigger change detection
  }

  removeDirector(id: any) {
    const index = this.movieDirector.value as number[];
    this.removeFirst(index, id);
    this.movieDirector.setValue(index); // To trigger change detection
  }

  removeFirst<T>(array: T[], toRemove: T): void {
    const index = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

  onCreateGenre() {
    const dialogRef = this.matDialog.open(AddGenreMovieComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.genreList = result;
      }
    });
  }

  onCreateCast() {
    const dialogRef = this.matDialog.open(AddCastMovieComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.castList = result;
      }
    });
  }

  onCreateDirector() {
    const dialogRef = this.matDialog.open(AddDirectorMovieComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.directorList = result;
      }
    });
  }

  async getGenreList() {
    await this.movieGenreService.getGenre().toPromise().then((data: any) => {
      if (data) {
        this.genreList = data;
      }
    });
  }

  async getDirectorList() {
    await this.movieDirectorService.getDirector().toPromise().then(async (data: any) => {
      if (data) {
        this.directorList = data;
        // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < this.directorList.length; i++) {
          await this.getAvatarDirector(this.directorList[i].avatar).then((value: any) => {
            this.directorList[i].avatar = value;
          });
        }
      }
    });
  }

  async getGenreOfMovie() {
    await this.movieGenreService.getAllGenreByMovieId(this.movie.id).toPromise().then((data: any) => {
      const result: number[] = [];
      for (const datum of data) {
        result.push(datum.id);
      }
      this.movieGenre = new FormControl(result);
    });
  }

  async getDirectorOfMovie() {
    await this.movieDirectorService.getDirectorByMovieId(this.movie.id).toPromise().then((data: any) => {
      const result: number[] = [];
      for (const datum of data) {
        result.push(datum.id);
      }
      this.movieDirector = new FormControl(result);
    });
  }

  async getCastOfMovie() {
    await this.movieCastService.getCastByMovieId(this.movie.id).toPromise().then((data: any) => {
      for (const datum of data) {
        this.getCastById(Number(datum.id.castId)).then((value: any) => {
          this.castListChoosed.push({
            castDetail: value,
            character: datum.castCharacter
          });
        });
      }
    });
  }

  async getCastById(id: number) {
    let cast: any = null;
    await this.movieCastService.getCastById(id).toPromise().then((data: any) => {
      cast = data;
    });
    return cast;
  }

  async editMovie() {
    this.submitted = true;
    this.setUpdateMovie();
    if (!this.checkDuration) {
      UtilClass.showMessageAlert(UTIL.ICON_ERROR, UTIL.SHOW_LOAD_LINK_MOVIE_FAILED);
    }
    if (this.movieService.formMovie.valid && this.submitted && this.checkDuration()) {
      this.getMovieFK();
      console.log(JSON.stringify(this.movie));
      await this.movieService.editMovie(this.movie).toPromise().then((value: any) => {
        if (value.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_EDIT_MOVIE);
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, value.message);
        }
      });
      this.countEdit++;
    }
  }
  setUpdateMovie() {
    this.movie = {
      "id": this.movie.id,
      "name": this.movieService.formMovie.value.name,
      "poster": this.movieService.formMovie.value.poster,
      "description": this.movieService.formMovie.value.description,
      "slug": this.movieService.formMovie.value.slug,
      "image_showing": this.movieService.formMovie.value.image_showing,
      "movieStatus": this.movieService.formMovie.value.movieStatus,
      "quality": this.movieService.formMovie.value.quality,
      "linkTrailer": this.movieService.formMovie.value.linkTrailer,
      "linkMovie": this.movieService.formMovie.value.linkMovie,
      "releaseDate": this.movieService.formMovie.value.releaseDate,
      "movieDuration": this.movieService.formMovie.value.movieDuration,
      "translationStatus": this.movieService.formMovie.value.translationStatus,
      "countryCode": this.movieService.formMovie.value.countryCode,
      "moviePrice": this.movieService.formMovie.value.moviePrice,
      "directorOfMovies": [],
      "favoriteMovies": [],
      "genreOfMovies": [],
      "accountHistories": [],
      "movieEvaluates": [],
      "castOfMovies": [],
      "billingInformations": []
    }
  }

  getMovieFK() {
    // add genre
    if (this.movieGenre.value === null) {
      this.fkGenre = [];
    } else {
      this.fkGenre = [];
      for (const genre of this.movieGenre.value) {
        const id = {
          genreId: genre,
          movieId: this.movie.id
        };
        this.fkGenre.push(new FKGenre(id, null, null));
      }
      this.movie.genreOfMovies = this.fkGenre;
    }
    // add cast
    if (this.castListChoosed === null) {
      this.fkCast = [];
    } else {
      this.fkCast = [];
      for (const cast of this.castListChoosed) {
        const id = {
          castId: cast.castDetail.id,
          movieId: this.movie.id
        };
        this.fkCast.push(new FKCast(id, cast.character, null, null));
      }
      this.movie.castOfMovies = this.fkCast;
    }
    // add director
    if (this.movieDirector.value === null) {
      this.fkDirector = [];
    } else {
      this.fkDirector = [];
      for (const director of this.movieDirector.value) {
        const id = {
          directorId: director,
          movieId: this.movie.id
        };
        this.fkDirector.push(new FKDirector(id, null, null));
      }
      this.movie.directorOfMovies = this.fkDirector;
    }
  }
  checkDuration(): boolean {
    return this.movieDuration ? true : false;
  }
  async getAvatarDirector(url: any) {
    let ava;
    await this.loginService.getAccImage(url).toPromise().then((result: any) => {
      ava = result;
    });
    return ava;
  }
  convertSlug(): string {
    let str = this.movieService.formMovie.value.name ? this.movieService.formMovie.value.name : '';
    return slugify(str, {
      replacement: '-',
      remove: undefined,
      lower: true,
      strict: true,
      locale: 'vi',
      trim: true
    });
  }
  onMetadataTrailer() {
    if (this.movieService.formMovie.value.linkTrailer) {
      this.player = new Plyr('#player-trailer', { captions: { active: true } });
    }
  }
  onMetadata(video: any) {
    this.movieDuration = null;
    if (this.movieService.formMovie.value.linkMovie) {
      this.player = new Plyr('#player-movie', { captions: { active: true } });
      if (video.duration === undefined) {
        this.movieDuration = null;
      } else {
        this.movieDuration = video.duration;
      }
    }
  }
  changeLinkMovie() {
    this.movieDuration = null;
    if (this.linkMovie) {
      setTimeout(() => {
        this.checkLink = false;
      }, 500);
      this.checkLink = true;
    }
  }

  getLinkTrailer() {
    return this.movieService.formMovie.value.linkTrailer;
  }

  getLinkMovie() {
    return this.movieService.formMovie.value.linkMovie;
  }
}
