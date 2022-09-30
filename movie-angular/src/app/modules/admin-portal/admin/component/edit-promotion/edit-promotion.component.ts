import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MoviePromotionService } from '../../../service/movie-promotion.service';
import { MY_DATE_FORMATS } from '../../../util/FORMAT_DATE';
import { AddPromotionComponent } from '../add-promotion/add-promotion.component';

@Component({
  selector: 'app-edit-promotion',
  templateUrl: './edit-promotion.component.html',
  styleUrls: ['./edit-promotion.component.css'],
  providers: [{
    provide: DateAdapter,
    useClass: MomentDateAdapter,
    deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
  },
  { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }]
})
export class EditPromotionComponent implements OnInit {
  promoForm = new FormGroup({
    code_name: new FormControl(null, [Validators.required]),
    description: new FormControl(null, [Validators.required]),
    start_date: new FormControl(null, [Validators.required]),
    end_date: new FormControl(null, [Validators.required]),
    percent_discount: new FormControl(null, [Validators.required])
  });
  maxDate!: Date;
  codeName = '';
  promo = {
    id: null,
    code_name: '',
    description: '',
    start_date: new Date(),
    end_date: new Date(),
    percent_discount: 0,
  }

  constructor(private promoService: MoviePromotionService,
    public dialogRef: MatDialogRef<AddPromotionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.setPromo();
    this.promoForm = new FormGroup({
      code_name: new FormControl(this.promo.code_name, [Validators.required]),
      description: new FormControl(this.promo.description, [Validators.required]),
      start_date: new FormControl(this.promo.start_date, [Validators.required]),
      end_date: new FormControl(this.promo.end_date, [Validators.required]),
      percent_discount: new FormControl(this.promo.percent_discount, [Validators.required])
    });
    this.maxDate = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
  }
  setPromo() {
    this.promo.id = this.data.id;
    this.promo.code_name = this.data.code_name;
    this.promo.description = this.data.description;
    this.promo.start_date = new Date(this.data.start_date);
    this.promo.end_date = new Date(this.data.end_date);
    this.promo.percent_discount = this.data.percent_discount;
    this.codeName = this.promo.code_name;
  }
  savePromo() {
    this.promo.code_name = this.promoForm.value.code_name;
    this.promo.description = this.promoForm.value.description;
    this.promo.start_date = new Date(this.promoForm.value.start_date);
    this.promo.end_date = new Date(this.promoForm.value.end_date);
    this.promo.percent_discount = this.promoForm.value.percent_discount;
    if (this.promoForm.valid) {
      this.promoService.editPromotions(this.promo).toPromise().then((data: any) => {
        if (data.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_EDIT_PROMO);
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
