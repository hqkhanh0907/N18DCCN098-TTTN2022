import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-movie-data',
  templateUrl: './list-movie-data.component.html',
  styleUrls: ['./list-movie-data.component.css']
})
export class ListMovieDataComponent implements OnInit {
  @Input() movies: any;
  constructor() { }

  ngOnInit(): void {
  }
  checkMovie(): boolean {
    if (this.movies) {
      if (this.movies.length === 0) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
}
