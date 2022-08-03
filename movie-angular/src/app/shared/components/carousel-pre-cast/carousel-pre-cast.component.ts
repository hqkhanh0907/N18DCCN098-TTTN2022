import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel-pre-cast',
  templateUrl: './carousel-pre-cast.component.html',
  styleUrls: ['./carousel-pre-cast.component.css'],
})
export class CarouselPreCastComponent implements OnInit {
  responsiveOptions: any;
  constructor() {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 5,
        numScroll: 3,
      },
      {
        breakpoint: '768px',
        numVisible: 3,
        numScroll: 2,
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }

  ngOnInit() {}
}
