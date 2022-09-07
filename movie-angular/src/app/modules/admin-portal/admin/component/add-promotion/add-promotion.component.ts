import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MoviePromotionService } from '../../../service/movie-promotion.service';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.css'],
  providers: [{
    provide: DateAdapter,
    useClass: MomentDateAdapter,
    deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
  },
  { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }]
})
export class AddPromotionComponent implements OnInit {
  promoForm!: FormGroup;
  maxDate!: Date;
  codeName = '';

  constructor(private promoService: MoviePromotionService,
    public dialogRef: MatDialogRef<AddPromotionComponent>) { }

  ngOnInit() {
    this.promoForm = new FormGroup({
      code_name: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required]),
      start_date: new FormControl(null, [Validators.required]),
      end_date: new FormControl(null, [Validators.required]),
      percent_discount: new FormControl(null, [Validators.required])
    });
    this.maxDate = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
  }
  savePromo() {
    let promo = {
      code_name: this.promoForm.value.code_name,
      description: this.promoForm.value.description,
      start_date: new Date(this.promoForm.value.start_date).getTime(),
      end_date: new Date(this.promoForm.value.end_date).getTime(),
      percent_discount: this.promoForm.value.percent_discount,
    }
    if(this.promoForm.valid) {
      this.promoService.addPromotions(promo).toPromise().then((data: any) => {
        if (data.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_PROMOTION);
          this.dialogRef.close();
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
        }
      });
    }
  }
  onNoClick() {
    this.dialogRef.close();
  }
  getMaxDate(): Date {
    if (this.promoForm.value.end_date) {
      return this.promoForm.value.end_date;
    } else {
      return this.maxDate;
    }
  }
  getMinDate(): Date {
    let minDate = new Date(this.promoForm.value.start_date);
    return minDate;
  }
  upperCase() {
    this.codeName = this.codeName.toUpperCase();
  }
}
