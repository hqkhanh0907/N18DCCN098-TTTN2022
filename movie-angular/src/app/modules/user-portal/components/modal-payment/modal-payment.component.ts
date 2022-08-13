import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { MovieService } from 'src/app/service/shared/movie.service';
import { UTIL } from 'src/app/shared/util/util';

@Component({
  selector: 'app-modal-payment',
  templateUrl: './modal-payment.component.html',
  styleUrls: ['./modal-payment.component.css'],
})
export class ModalPaymentComponent implements OnInit {
  @Input() billInfo: any;
  @Input() movie: any;
  @Input() amount: any;
  public payPalConfig?: IPayPalConfig;

  constructor(private movieService: MovieService,
    public activeModal: NgbActiveModal) {}

  ngOnInit() {
    this.initConfig();
  }
  private initConfig(): void {
    this.payPalConfig = {
      currency: 'USD',
      clientId: String(UTIL.CLIENT_ID),
      createOrderOnClient: (data) =>
        <ICreateOrderRequest>{
          intent: 'CAPTURE',
          purchase_units: [
            {
              amount: {
                currency_code: 'USD',
                value: this.amount,
                breakdown: {
                  item_total: {
                    currency_code: 'USD',
                    value: this.amount,
                  },
                },
              },
              items: [
                {
                  name: this.movie.name,
                  quantity: '1',
                  unit_amount: {
                    currency_code: 'USD',
                    value: this.movie.moviePrice,
                  },
                },
              ],
            },
          ],
        },
      advanced: {
        commit: 'true',
      },
      style: {
        label: 'paypal',
        layout: 'vertical',
      },
      onApprove: (data, actions) => {
        console.log(
          'onApprove - transaction was approved, but not authorized',
          data,
          actions
        );
        actions.order.get().then((details: any) => {
          console.log(
            'onApprove - you can get full order details inside onApprove: ',
            details
          );
        });
      },
      onClientAuthorization: (data) => {
        console.log(
          'onClientAuthorization - you should probably inform your server about completed transaction at this point',
          data
        );
        if (data.status === UTIL.COMPLETE_PAY_STATUS) {
          setTimeout(async () => {
            this.billInfo.status = UTIL.PAYED;
            console.log(this.billInfo);
            await this.movieService
              .addInfoBill(this.billInfo)
              .toPromise()
              .then((data: any) => {
                this.activeModal.close(data);
              });
          });
        }
        // this.showSuccess = true;
      },
      onCancel: (data, actions) => {
        console.log('OnCancel', data, actions);
        // this.showCancel = true;
      },
      onError: (err) => {
        console.log('OnError', err);
        // this.showError = true;
      },
      onClick: (data, actions) => {
        console.log('onClick', data, actions);
        // this.resetStatus();
      },
    };
  }
}
