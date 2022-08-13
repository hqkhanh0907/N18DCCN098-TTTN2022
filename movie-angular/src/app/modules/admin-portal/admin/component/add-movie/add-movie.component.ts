import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AddGenreMovieComponent } from '../add-genre-movie/add-genre-movie.component';
import { AddCastMovieComponent } from '../add-cast-movie/add-cast-movie.component';
import { AddDirectorMovieComponent } from '../add-director-movie/add-director-movie.component';
import { ListCastMovieComponent } from '../list-cast-movie/list-cast-movie.component';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { MovieGenre } from '../../../model/MovieGenre';
import { MovieCast } from '../../../model/MovieCast';
import { MovieDirector } from '../../../model/MovieDirector';
import { Movie } from '../../../model/movie';
import { FKCast } from '../../../model/FKCast';
import { FKGenre } from '../../../model/FKGenre';
import { FKDirector } from '../../../model/FKDirector';
import { UTIL } from 'src/app/shared/util/util';
import { MovieService } from 'src/app/service/shared/movie.service';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { MovieGenreService } from '../../../service/movie-genre.service';
import { MovieDirectorService } from '../../../service/movie-director.service';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import slugify from 'slugify';
import * as Plyr from 'plyr';


@Component({
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css'],
  providers: [
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }
  ]
})
export class AddMovieComponent implements OnInit {
  countSave = 0;
  submitted = false;
  linkMovie: any;
  maxDate!: Date;
  hours = 0;
  minutes = 0;
  second = 0;
  movieGenre = new FormControl();
  movieDirector = new FormControl();
  genreList: MovieGenre[] = [];
  castList: MovieCast[] = [];
  directorList: MovieDirector[] = [];
  movieAdd: any;
  movieList: Movie[] = [];
  fkCast: FKCast[] = [];
  fkGenre: FKGenre[] = [];
  fkDirector: FKDirector[] = [];
  movieGetByName!: any;
  fileToUpload!: File;
  moviePoster: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_MOVIE_POSTER_URL);
  moviePImage: ImageModel = new ImageModel(UTIL.DEFAULT_MOVIE_POSTER_NAME, UTIL.DEFAULT_MOVIE_SHOWING_URL);
  countries: any;
  castListChoosed: any = [];
  movieStatusList = [{ name: 'Premiered', value: 1 }, { name: 'Comming soon', value: 0 }];
  qualities = [{ value: '1080' }, { value: '720' }, { value: '360' }, { value: '240' }];
  translationStatusList = [{ value: 0, name: 'default' }, { value: 1, name: 'dub movie' }, { value: 2, name: 'narration movie' }, { value: 3, name: 'vietsub' }];
  movieDuration: any;
  player: any;
  checkLink = false;

  constructor(public movieService: MovieService,
    private loginService: LoginServiceService,
    private movieGenreService: MovieGenreService,
    private movieDirectorService: MovieDirectorService,
    public dialogRef: MatDialogRef<AddMovieComponent>,
    private matDialog: MatDialog) {
  }

  async ngOnInit() {
    this.maxDate = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
    this.movieService.initializeFormGroup();
    await this.getGenreList();
    await this.getDirectorList();
    await this.getAllCountries();
  }
  async getAllCountries() {
    await this.movieService.getCountries().toPromise().then((data: any) => {
      if (data) {
        this.countries = data;
      }
    });
  }

  eventChangeSecond(target: number) {
    if (target === null) {
      this.second = 0;
    }
    if (target >= 60) {
      const mod = Math.floor(target / 60);
      this.second = target - mod * 60;
      this.minutes = this.minutes + mod;
    }
    if (this.minutes >= 60) {
      const modMN = Math.floor(this.minutes / 60);
      this.minutes = this.minutes - modMN * 60;
      this.hours = this.hours + modMN;
    }
  }
  getImage(): string {
    if (this.movieService.formMovie.value.image_showing) {
      return this.movieService.formMovie.value.image_showing;
    } else {
      return this.moviePImage.url;
    }
  }
  getPoster(): string {
    if (this.movieService.formMovie.value.poster) {
      return this.movieService.formMovie.value.poster;
    } else {
      return this.moviePoster.url;
    }
  }

  eventChangeMinutes(target: number) {
    if (target === null) {
      this.minutes = 0;
    }
    if (target >= 60) {
      const modHour = Math.floor(target / 60);
      this.minutes = target - modHour * 60;
      this.hours = this.hours + modHour;
    }
  }

  eventChangeHours(target: number) {
    if (target === null) {
      this.hours = 0;
    }
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

  async saveMovie() {
    this.submitted = true;
    this.movieAdd = {
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
    if (this.movieService.formMovie.valid && this.submitted) {
      await this.movieService.addMovie(this.movieAdd).toPromise().then((value: any) => {
        if (value.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_MOVIE);
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, value.message);
        }
      });
      await this.getMovie(this.movieAdd.name);
      console.log(this.movieGetByName);
      await this.movieService.editMovie(this.movieGetByName).subscribe((data: any) => {
        console.log(data);
      });
      this.countSave++;
    }
  }

  async getMovie(name: string) {
    await this.movieService.getMovieByName(name).toPromise().then((movieValue: any) => {
      if (movieValue) {
        this.movieGetByName = movieValue;
        // add genre
        if (this.movieGenre.value === null) {
          this.fkGenre = [];
        } else {
          this.fkGenre = [];
          for (const genre of this.movieGenre.value) {
            const id = {
              genreId: genre,
              movieId: movieValue.id
            };
            this.fkGenre.push(new FKGenre(id, null, null));
          }
          this.movieGetByName.genreOfMovies = this.fkGenre;
        }
        // add cast
        if (this.castListChoosed === null) {
          this.fkCast = [];
        } else {
          this.fkCast = [];
          for (const cast of this.castListChoosed) {
            const id = {
              castId: cast.castDetail.id,
              movieId: movieValue.id
            };
            this.fkCast.push(new FKCast(id, cast.character, null, null));
          }
          this.movieGetByName.castOfMovies = this.fkCast;
        }
        // add director
        if (this.movieDirector.value === null) {
          this.fkDirector = [];
        } else {
          this.fkDirector = [];
          for (const director of this.movieDirector.value) {
            const id = {
              directorId: director,
              movieId: movieValue.id
            };
            this.fkDirector.push(new FKDirector(id, null, null));
          }
          this.movieGetByName.directorOfMovies = this.fkDirector;
        }
      }
    });
  }
  getDuration(): any {
    if (this.linkMovie) {
      return this.movieDuration === null ? null : Math.floor(this.movieDuration / 60);
    } else {
      return null;
    }
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
    if (this.linkMovie) {
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

  async getAvaDirector(url: any) {
    let ava;
    await this.loginService.getAccImage(url).toPromise().then((result: any) => {
      ava = result;
    });
    return ava;
  }
  onNoClick() {
    if (this.countSave === 0) {
      this.dialogRef.close();
    } else if (this.countSave > 0) {
      this.movieService.getMovie().subscribe(((data: any) => {
        this.movieList = data;
        this.dialogRef.close(this.movieList);
      }));
    }
  }

  handleFileUpload(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.fileToUpload = files.item(0) as File;
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.moviePoster = new ImageModel(this.fileToUpload.name, event.target.result);
    };
    reader.readAsDataURL(this.fileToUpload);
  }

  openCastList() {
    const dialogRef = this.matDialog.open(ListCastMovieComponent, {
      data: this.castListChoosed
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.castListChoosed = result;
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
          await this.getAvaDirector(this.directorList[i].avatar).then((value: any) => {
            this.directorList[i].avatar = value;
          });
        }
      }
    });
  }

  getLinkTrailer() {
    return this.movieService.formMovie.value.linkTrailer;
  }

  getLinkMovie() {
    return this.movieService.formMovie.value.linkMovie;
  }
}
