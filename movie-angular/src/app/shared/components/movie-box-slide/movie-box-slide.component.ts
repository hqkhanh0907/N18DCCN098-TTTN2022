import { Component, Input, OnInit } from '@angular/core';
import { MovieService } from 'src/app/service/shared/movie.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from '../../model/ImageModel';
import { UTIL } from '../../util/util';

@Component({
  selector: 'app-movie-box-slide',
  templateUrl: './movie-box-slide.component.html',
  styleUrls: ['./movie-box-slide.component.css'],
})
export class MovieBoxSlideComponent implements OnInit {
  @Input() movie: any;
  evaluate = { rate: 0.0, votes: 0 };
  imageMovie: ImageModel = new ImageModel(
    UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE,
    UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE
  );
  constructor(private movieService: MovieService) { }
  async ngOnInit() {
    await this.getRate();
  } 
  getImage(): string {
    return this.movie?this.movie.image_showing:this.imageMovie.url;
  }
  async getRate() {
    await this.movieService
      .getRateMovie(this.movie.id)
      .toPromise()
      .then((data: any) => {
        this.evaluate = data;
      });
  }
  goToMovieDetail() {
    sessionStorage.removeItem('item-header');
    window.location.href = `/mp/movie/${this.movie.slug}`;
  }
}
