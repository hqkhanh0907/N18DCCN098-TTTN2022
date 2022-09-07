import { Component, DoCheck, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlyrComponent } from 'ngx-plyr';
import * as Plyr from 'plyr';
import { AccountService } from 'src/app/service/shared/account.service';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css'],
})
export class MovieDetailsComponent implements OnInit {

  // get the component instance to have access to plyr instance
  @ViewChild(PlyrComponent, { static: true })
  plyr!: PlyrComponent;

  // or get it from plyrInit event
  player: Plyr = new Plyr('#player-movie', {
    quality: { default: 1080, options: [4320, 2880, 2160, 1440, 1080, 720, 576, 480, 360, 240] },
    controls: ['play-large', 'play', 'progress', 'current-time', 'duration', 'mute', 'volume', 'captions', 'settings', 'pip', 'airplay', 'fullscreen'
    ],
    settings: ['captions', 'quality', 'speed', 'loop'],
    seekTime: 10
  });;
  public height = '100px';
  public flagContentHeight = false;
  flagFollow = false;
  genreOfMovie: any;
  castOfMovie: any;
  directorOfMovie: any;
  slug: any;
  movie: any;
  countryName: any;
  releaseDate: Date = new Date();
  evaluate = { rate: 0.0, votes: 0 };
  checkPay: any = false;
  accountId: any = null;
  isLoading = false;
  historyTime = 0;
  currentTime = 0;
  uriMovie = ``;
  videoSources!: Plyr.Source[];
  constructor(
    private movieService: MovieService,
    private activeRouter: ActivatedRoute,
    public loginService: LoginServiceService,
    private router: Router, private accountService: AccountService
  ) {
  }
  async ngOnInit() {
    window.scrollTo(0, 0);
    this.slug = this.activeRouter.snapshot.params['slug'];
    this.accountId = this.loginService.isUserLoggedIn() ? Number(sessionStorage.getItem('idAcc')) : null;
    await this.getMovieDetail();
    if (this.movie) {
      await this.getGenreOfMovie(this.movie.id);
      await this.getCastOfMovie(this.movie.id);
      await this.getDirectorOfMovie(this.movie.id);
    }
    if (this.accountId) {
      await this.getFollow();
      await this.checkPayment();
      await this.getHistory();
    }
    await this.getRate();
    this.releaseDate = new Date(this.movie.releaseDate);
    this.uriMovie = `${this.movie.linkMovie}#t=${this.historyTime}`;
    this.videoSources = [
      {
        src: this.getUrlMovie(this.uriMovie, '360'),
        provider: 'html5',
        size: 360

      },
      {
        src: this.getUrlMovie(this.uriMovie, '720'),
        provider: 'html5',
        size: 720

      },
      {
        src: this.getUrlMovie(this.uriMovie, '1080'),
        provider: 'html5',
        size: 1080

      },
    ];
    if (!this.showVideo()) {
      this.isLoading = true;
    }
  }
  getUrlMovie(uriMovie: string, size: string): string {
    var DELIMITER = "/";
    var parts = uriMovie.split(DELIMITER);
    parts[4] = size;
    uriMovie = parts.join(DELIMITER); this.showVideo;
    return uriMovie;
  }
  getDescription(): string {
    return this.movie ? this.movie.description : '';
  }
  loadMovieSuccess() {
    this.isLoading = true;
    setTimeout(() => {
      this.isLoading = true;
    }, 1000);
  }
  async getHistory() {
    const historyKey = {
      "accountId": this.accountId,
      "movieId": this.movie.id
    }
    await this.accountService.getHistory(historyKey).toPromise().then((data: any) => {
      if (data) {
        this.historyTime = data.time_watched;
      }
    });
  }
  option: Plyr.Options = {
    quality: { default: 720, forced: false, options: [1080, 720, 480, 360] },
    controls: ['play-large', 'play', 'rewind', 'fastForward', 'progress', 'current-time', 'duration', 'mute', 'volume', 'settings', 'airplay', 'fullscreen'],
    settings: ['quality', 'speed'],
  }
  async getFollow() {
    const favoriteId = {
      accountId: this.accountId,
      movieId: this.movie.id
    };
    await this.movieService.getFollow(favoriteId).toPromise().then((data: any) => {
      this.flagFollow = data;
    });
  }
  checkDirectors(): boolean {
    if (this.directorOfMovie) {
      if (this.directorOfMovie.length === 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }
  checkCasts(): boolean {
    if (this.castOfMovie) {
      if (this.castOfMovie.length === 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }
  checkGenre(): boolean {
    if (this.genreOfMovie) {
      if (this.genreOfMovie.length === 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }
  async checkPayment() {
    if (this.loginService.isUserLoggedIn()) {
      await this.movieService.checkPay(this.accountId, this.movie.id).toPromise().then((data: any) => {
        this.checkPay = data;
      });
    }
  }
  async getGenreOfMovie(movieId: any) {
    await this.movieService
      .getGenreOfMovie(movieId)
      .toPromise()
      .then((data: any) => {
        this.genreOfMovie = data;
      });
  }
  async getCastOfMovie(movieId: any) {
    await this.movieService
      .getCastOfMovie(movieId)
      .toPromise()
      .then((data: any) => {
        this.castOfMovie = data;
      });
  }
  async getDirectorOfMovie(movieId: any) {
    await this.movieService
      .getDirectorOfMovie(movieId)
      .toPromise()
      .then((data: any) => {
        this.directorOfMovie = data;
      });
  }
  async getMovieDetail() {
    await this.movieService
      .getMovieBySlug(this.slug)
      .toPromise()
      .then((data: any) => {
        this.movie = data;
      });
    await this.movieService
      .getCountryByAlphaCode(this.movie.countryCode)
      .toPromise()
      .then((data: any) => {
        this.countryName = data.name;
      });
  }
  showRate(): string {
    return this.evaluate.rate.toFixed(1);
  }
  async getRate() {
    await this.movieService
      .getRateMovie(this.movie.id)
      .toPromise()
      .then((data: any) => {
        this.evaluate = data;
      });
  }
  onTimeUpdate(event: any) {
    this.currentTime = event.detail.plyr.media.currentTime;
    if (this.accountId) {
      const history = {
        "accountHistoryKey": {
          "accountId": this.accountId,
          "movieId": this.movie.id
        },
        "date": new Date().getTime(),
        "time_watched": this.currentTime
      }
      this.accountService.saveHistory(history).subscribe();
    }
  }
  changeFlagFollow() {
    const favorite = {
      id: {
        accountId: this.accountId,
        movieId: this.movie.id
      },
      date: Number(new Date())
    }
    if (this.loginService.isUserLoggedIn()) {
      this.flagFollow = !this.flagFollow;
      if (this.flagFollow === true) {
        this.movieService.follow(favorite).subscribe();
      } else {
        this.movieService.unFollow(favorite.id).subscribe();
      }
    } else {
      UtilClass.showMessBasic(UTIL.WARNING_LOGIN);
    }
  }
  changeFlag() {
    this.flagContentHeight = !this.flagContentHeight;
    this.changeHeight();
  }
  changeHeight() {
    if (this.flagContentHeight) {
      this.height = 'fit-content';
    } else {
      this.height = '100px';
    }
  }
  checkOverflow(element: {
    offsetHeight: number;
    scrollHeight: number;
    offsetWidth: number;
    scrollWidth: number;
  }) {
    return (
      element.offsetHeight < element.scrollHeight ||
      element.offsetWidth < element.scrollWidth
    );
  }
  checkMoviePrice(): boolean {
    if (this.movie) {
      if (this.movie.moviePrice === 0) {
        return false;
      } else {
        return !this.checkPay;
      }
    } else {
      return false;
    }
  }
  showVideo(): boolean {
    let check = false;
    if (this.loginService.isUserLoggedIn()) {
      return !this.checkMoviePrice();
    } else {
      check = this.movie.moviePrice === 0 ? true : false;
      return check;
    }
  }
  checkLoginInPay() {
    if (this.loginService.isUserLoggedIn()) {
      this.goPay();
    } else {
      UtilClass.showMessBasic(UTIL.WARNING_LOGIN);
    }
  }
  goPay() {
    this.router.navigate(['/mp/service/payment/' + this.slug]);
  }
  goSignUp() {
    this.router.navigate(['/mp/signup']);
  }
  goLogin() {
    this.router.navigate(['/mp/login']);
  }
}