import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { MovieService } from 'src/app/service/shared/movie.service';

@Component({
  selector: 'app-movie-slide',
  templateUrl: './movie-slide.component.html',
  styleUrls: ['./movie-slide.component.css'],
})
export class MovieSlideComponent implements OnInit {
  showNavigationArrows = false;
  showNavigationIndicators = false;
  movieList = [];
  slideConfig = {
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2111000,
  };
  constructor(private movieService: MovieService) {}

  async ngOnInit() {
    await this.movieService
      .getAllMovie()
      .toPromise()
      .then((data: []) => {
        for (let item of data) {
          this.movieList.push(item);
        }
      });
  }
}
