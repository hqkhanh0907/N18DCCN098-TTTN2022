import { Component, Input, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { UTIL } from 'src/app/shared/util/util';

@Component({
  selector: 'app-movie-detail-on-slide',
  templateUrl: './movie-detail-on-slide.component.html',
  styleUrls: ['./movie-detail-on-slide.component.css'],
})
export class MovieDetailOnSlideComponent implements OnInit {
  @Input() movie: any;
  flagP = false;
  currentRate = 4;
  image = 'url("")';

  constructor(private router: Router) {}

  ngOnInit() {
    this.image = 'url("' + this.movie.poster + '")';
  }

  getQuality(): string {
    if (UTIL.FULL_HD === this.movie.quality) {
      return UTIL.QUALITY_FULL_HD;
    } else if (UTIL.HD === this.movie.quality) {
      return UTIL.QUALITY_HD;
    } else {
      return '';
    }
  }

  checkMoviePrice(): boolean {
    return this.movie.moviePrice === 0 ? true : false;
  }
  getPrice(): string {
    return `${this.movie.moviePrice}$`;
  }

  goToMovieDetail() {
    sessionStorage.removeItem('item-header');
    this.router.navigate(['/mp/movie/' + this.movie.slug]);
  }

  changeFlagP() {
    this.flagP = !this.flagP;
  }
}
