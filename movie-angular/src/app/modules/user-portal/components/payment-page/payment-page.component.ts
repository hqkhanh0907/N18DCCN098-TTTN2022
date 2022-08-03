import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountService } from 'src/app/service/shared/account.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { UTIL } from 'src/app/shared/util/util';
import { ModalPaymentComponent } from '../modal-payment/modal-payment.component';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css'],
})
export class PaymentPageComponent implements OnInit {
  promoCode: FormControl = new FormControl();
  logoPaypal = UTIL.LOGO_PAYPAL;
  slug = '';
  movie: any;
  accountInf: any;
  promo: any = undefined;
  checkPromo = false;
  constructor(
    private modalService: NgbModal,
    private activeRouter: ActivatedRoute,
    private movieService: MovieService,
    private accService: AccountService
  ) {}

  async ngOnInit() {
    this.slug = this.activeRouter.snapshot.params['slug'];
    await this.getMovieDetail();
    await this.getAcc();
    console.log(this.movie);
    console.log(this.accountInf);
  }
  async getMovieDetail() {
    await this.movieService
      .getMovieBySlug(this.slug)
      .toPromise()
      .then((data: any) => {
        this.movie = data;
      });
  }
  async getAcc() {
    let accId = sessionStorage['idAcc'];
    if (accId !== undefined) {
      await this.accService
        .getAccount(accId)
        .toPromise()
        .then((data: any) => {
          this.accountInf = data;
        });
    }
  }
  async getPromo() {
    let code: string = this.promoCode.value;
    code = code.toUpperCase();
    await this.movieService
      .getPromoCode(code)
      .toPromise()
      .then(
        (data: any) => {
          console.log(data);
          if (data) {
            this.promo = data;
            this.checkPromo = true;
          } else {
            this.promo = null;
            this.checkPromo = false;
          }
        },
        (err) => {
          this.checkPromo = false;
        }
      );
  }
  getDiscountPromo(): string {
    return (
      (this.movie.moviePrice * this.promo.percent_discount) /
      100
    ).toFixed(2);
  }
  getAmount(): string {
    return this.promo
      ? (this.movie.moviePrice - Number(this.getDiscountPromo())).toFixed(2)
      : this.movie.moviePrice.toFixed(2);
  }
  applyPromoCode() {
    setTimeout(async () => {
      await this.getPromo();
    }, 0);
  }
  goPaypalModal() {
    const billInfo = {
      billingInformationKey: {
        accountId: this.accountInf.id,
        movieId: this.movie.id,
      },
      promotion: {
        id:
          this.promo !== undefined && this.promo !== null
            ? this.promo.id
            : UTIL.DEFAULT_PROMO,
      },
      status: UTIL.NOT_PAY,
    };
    setTimeout(async () => {
      await this.movieService
        .addInfoBill(billInfo)
        .toPromise()
        .then((data: any) => {
          console.log(data);
        });
    });
    const modalRef = this.modalService.open(ModalPaymentComponent);
    modalRef.componentInstance.billInfo = billInfo;
    modalRef.componentInstance.movie = this.movie;
    modalRef.componentInstance.amount = this.getAmount();
  }
}
