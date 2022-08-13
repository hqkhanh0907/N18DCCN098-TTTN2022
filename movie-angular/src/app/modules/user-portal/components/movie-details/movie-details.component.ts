import { Component, DoCheck, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as Plyr from 'plyr';
import { MovieCastService } from 'src/app/modules/admin-portal/service/movie-cast.service';
import { MovieDirectorService } from 'src/app/modules/admin-portal/service/movie-director.service';
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
  public height = '100px';
  public flagContentHeight = false;
  flagFollow = false;
  genreOfMovie: any;
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
  player: any;
  directors: any = [];
  casts: any = [];
  constructor(
    private movieService: MovieService,
    private activeRouter: ActivatedRoute,
    public loginService: LoginServiceService,
    private movieCastService: MovieCastService,
    private movieDirectorService: MovieDirectorService,
    private router: Router, private accountService: AccountService
  ) {
    window.scrollTo(0, 0);
  }
  async ngOnInit() {
    this.slug = this.activeRouter.snapshot.params['slug'];
    this.accountId = this.loginService.isUserLoggedIn() ? Number(sessionStorage.getItem('idAcc')) : null;
    await this.getMovieDetail();
    await this.getGenreOfMovie(this.movie.id);
    if (this.accountId) {
      await this.getFollow();
      await this.checkPayment();
      await this.getHistory();
    }
    await this.getRate();
    await this.getDirector();
    await this.getCast();
    this.releaseDate = new Date(this.movie.releaseDate);
    this.uriMovie = `${this.movie.linkMovie}#t=${this.historyTime}`;
    setTimeout(() => {
      this.isLoading = true;
    }, 2000);
  }
  async getCast() {
    await this.movieCastService.getCastByMovieId(this.movie.id).toPromise().then((data: any) => {
      this.casts = data;
    });
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
    if (this.directors === null) {
      if (this.directors.length === 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false
    }
  }
  checkCasts(): boolean {
    if (this.casts === null) {
      if (this.casts.length === 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false
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
  async getRate() {
    await this.movieService
      .getRateMovie(this.movie.id)
      .toPromise()
      .then((data: any) => {
        this.evaluate = data;
      });
  }
  onTimeUpdate(value: any) {
    this.currentTime = value.target.currentTime;
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
    if (this.movie.moviePrice === 0) {
      return false;
    } else {
      return !this.checkPay;
    }
  }
  showVideo(): boolean {
    let check = false;
    this.player = new Plyr('#player-movie');
    if (this.loginService.isUserLoggedIn()) {
      return !this.checkMoviePrice();
    } else {
      check = this.movie.moviePrice === 0 ? true : false;
      return check;
    }
  }
  async getDirector() {
    await this.movieDirectorService.getDirectorByMovieId(this.movie.id).toPromise().then((data: any) => {
      this.directors = data;
    });
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