import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';

@Component({
  selector: 'app-bill-item',
  templateUrl: './bill-item.component.html',
  styleUrls: ['./bill-item.component.css']
})
export class BillItemComponent implements OnInit {
  @Input() bill: any;

  imageMovie: ImageModel = new ImageModel(
    UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE,
    UTIL.DEFAULT_MOVIE_POSTER_URL
  );
  constructor(private router: Router) { }

  ngOnInit() {
    console.log('Bill:', this.bill);
  }
  goToBillDetail() {
    this.router.navigate(['/mp/service/payment/' + this.bill.movie.slug]);
  }
  getImage(): string {
    return this.bill.movie?this.bill.movie.poster:this.imageMovie.url;
  }
  getStatus(): boolean {
    return Boolean(this.bill.status);
  }
  getPricePaid(): string{
    let paid = this.bill.movie.moviePrice - Number(this.getDiscountPromo());
    return (Number(paid) < 0)? '0.0': String(paid.toFixed(2));
  }
  getDiscountPromo(): string {
    return (
      (Number(this.bill.movie.moviePrice) * Number(this.bill.promotion.percent_discount)) /
      100
    ).toFixed(2);
  }
}
