import { Component, DoCheck, OnDestroy, OnInit } from '@angular/core';
import {
  ActivatedRoute,
  ActivatedRouteSnapshot,
  Route,
  Router,
} from '@angular/router';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css'],
})
export class MovieDetailsComponent implements OnInit, DoCheck {
  public height = '100px';
  public flagContentHeight = false;
  flagFollow = false;
  genreOfMovie: any;
  slug: any;
  movie: any;
  countryName: any;
  releaseDate: Date = new Date();
  evaluate = { rate: 0.0, votes: 0 };
  public time = 0;

  constructor(
    private movieService: MovieService,
    private activeRouter: ActivatedRoute,
    public loginService: LoginServiceService,
    private router: Router
  ) {}
  ngDoCheck() {
    sessionStorage.setItem('time', String(this.time));
  }
  async ngOnInit() {
    window.scrollTo(0, 0);
    this.slug = this.activeRouter.snapshot.params['slug'];
    await this.getMovieDetail();
    await this.getGenreOfMovie(this.movie.id);
    await this.getRate();
    this.releaseDate = new Date(this.movie.releaseDate);
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
        console.log(this.evaluate);
      });
  }
  onTimeUpdate(value: any) {
    this.time = value.target.currentTime;
  }
  changeFlagFollow() {
    if (this.loginService.isUserLoggedIn()) {
      this.flagFollow = !this.flagFollow;
      if (this.flagFollow === true) {
        console.log('followed');
      } else {
        console.log("don't follow");
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
    return this.movie.moviePrice === 0 ? true : false;
  }
  showVideo(): boolean {
    if (this.loginService.isUserLoggedIn()) {
      return this.checkMoviePrice();
    } else {
      return false;
    }
  }
  goPay() {}
  goSignUp() {
    this.router.navigate(['/mp/signup']);
  }
  goLogin() {
    this.router.navigate(['/mp/login']);
  }
}
