import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-movie-box-slide',
  templateUrl: './movie-box-slide.component.html',
  styleUrls: ['./movie-box-slide.component.css'],
})
export class MovieBoxSlideComponent implements OnInit {
  constructor() {}

  ngOnInit() {}
  goToMovieDetail() {
    sessionStorage.removeItem('item-header');
    window.location.href = '/mp/movie';
  }
}
