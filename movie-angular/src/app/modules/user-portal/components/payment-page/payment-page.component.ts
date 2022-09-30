import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountService } from 'src/app/service/shared/account.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
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
  checkExitPay = false;
  checkPay = false;
  isLoading = false;
  constructor(
    private modalService: NgbModal,
    private activeRouter: ActivatedRoute,
    private router: Router,
    private movieService: MovieService,
    private accService: AccountService
  ) { }

  async ngOnInit() {
    this.slug = this.activeRouter.snapshot.params['slug'];
    await this.getMovieDetail();
    await this.getAcc();
    await this.checkExitPayment();
    await this.checkPayment();
    await this.setPay();
    setTimeout(() => {
      this.isLoading = true;
    }, 1000);
  }
  async setPay() {
    if (this.checkExitPay) {
      await this.movieService.getBill(this.accountInf.id, this.movie.id).toPromise()
        .then(
          (data: any) => {
            if (data && data.promotion.id !== UTIL.DEFAULT_PROMO) {
              this.promo = data.promotion;
              this.promoCode = new FormControl(data.promotion.code_name);
              this.checkPromo = true;
            } else {
              this.promo = null;
              this.checkPromo = false;
            }
          },
          (err) => {
            this.checkPromo = false;
          });
    }
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
          if (data.statusCode === undefined) {
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
  changeInputPromo() {
    this.promo = undefined;
  }
  getAmount(): string {
    let amount = (this.promo && this.promoCode.value)
      ? (this.movie.moviePrice - Number(this.getDiscountPromo())).toFixed(2)
      : this.movie.moviePrice.toFixed(2);
    return Number(amount)<0?'0.0':amount
  }
  applyPromoCode() {
    setTimeout(async () => {
      await this.getPromo();
    }, 0);
  }
  getDiscount(): string {
    return this.promo
      ? (Number(this.getDiscountPromo())).toFixed(2)
      : this.movie.moviePrice.toFixed(2);
  }
  goPaypalModal() {
    const billInfo = {
      billingInformationKey: {
        accountId: this.accountInf.id,
        movieId: this.movie.id,
      },
      promotion: {
        id:
          this.promo !== null && this.promo !== undefined
            ? this.promo.id
            : UTIL.DEFAULT_PROMO
      },
      status: UTIL.NOT_PAY,
    };
    this.movieService
      .addInfoBill(billInfo).subscribe();
    const modalRef = this.modalService.open(ModalPaymentComponent);
    modalRef.componentInstance.billInfo = billInfo;
    modalRef.componentInstance.movie = this.movie;
    modalRef.componentInstance.amount = this.getAmount();
    modalRef.componentInstance.discount = this.getDiscount();
    modalRef.closed.subscribe((data: any) => {
      if (data !== undefined && data === true) {
        UtilClass.showMessageAlert(
          UTIL.ICON_SUCCESS,
          UTIL.PAY_SUCCESSFULLY
        );
        this.router.navigate(['/mp/movie/' + this.slug]);
      } else {
        UtilClass.showRequestDeleteMovie(UTIL.PAY_NOT_SUCCESSFULLY);
      }

    });
    modalRef.dismissed.subscribe(() => {
      UtilClass.showMessageAlert(
        UTIL.ICON_WARNING, UTIL.PAY_NOT_SUCCESSFULLY);
    });
  }

  async checkExitPayment() {
    await this.movieService.checkExitPay(this.accountInf.id, this.movie.id).toPromise().then((data: any) => {
      this.checkExitPay = data;
    });
  }
  async checkPayment() {
    await this.movieService.checkPay(this.accountInf.id, this.movie.id).toPromise().then((data: any) => {
      this.checkPay = data;
    });
  }
}
