import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UTIL } from '../../util/util';
import { UtilClass } from '../../util/utilClass';

import {
  NgbActiveModal,
  NgbDateAdapter,
  NgbDateParserFormatter,
} from '@ng-bootstrap/ng-bootstrap';
import { AccountService } from 'src/app/service/shared/account.service';
import { CustomAdapter } from '../../util/custom-adapter ';
import { CustomDateParserFormatter } from '../../util/custom-date-parser-formatter ';
import { ImageModel } from '../../model/ImageModel';
import { ImageService } from 'src/app/service/shared/upload-image.service';

@Component({
  selector: 'app-modal-adjust-personal-info',
  templateUrl: './modal-adjust-personal-info.component.html',
  styleUrls: ['./modal-adjust-personal-info.component.css'],
  providers: [
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
  ],
})
export class ModalAdjustPersonalInfoComponent implements OnInit {
  @Input() accountInf: any;
  @Input() provinces: any;
  @Input() districts: any;
  @Input() wards: any;
  @Input() inputDistrict: any;
  @Input() inputWard: any;
  @Input() wardId: any;
  maxDate: any;
  pId = null;
  dId = null;
  wId = null;
  changeAvatar = false;
  dList: any = [];
  wList: any = [];
  adjustAccount!: FormGroup;
  submitted = false;
  isLoading = true;
  fileToUpload!: File;
  accImage: ImageModel = new ImageModel(
    UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE,
    UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE
  );
  genders = [
    { id: true, name: 'Male' },
    { id: false, name: 'Female' },
  ];

  constructor(
    public activeModal: NgbActiveModal,
    private accService: AccountService,
    private imageService: ImageService
  ) { }

  async ngOnInit() {
    this.isLoading = true;
    this.maxDate = { year: new Date().getFullYear(), month: new Date().getMonth(), day: new Date().getDate() };
    this.adjustAccount = new FormGroup({
      province: new FormControl(null),
      district: new FormControl(null),
      ward: new FormControl(null),
      gender: new FormControl(null, Validators.required),
      username: new FormControl(this.accountInf.username),
      email: new FormControl(this.accountInf.email, [
        Validators.required,
        Validators.email,
      ]),
      firstname: new FormControl(
        this.accountInf.firstname,
        Validators.required
      ),
      lastname: new FormControl(this.accountInf.lastname, Validators.required),
      phoneNumber: new FormControl(
        this.accountInf.phoneNumber,
        Validators.pattern(UTIL.NUMBER_PHONE_PATTERN)
      ),
      birthday: new FormControl(this.getFullDate(), [
        Validators.required,
        Validators.pattern(UTIL.DATE_PATTERN),
      ]),
      addressDetails: new FormControl(this.accountInf.addressDetails),
    });
    if (this.accountInf.avatar) {
      await this.getAvatar();
    }
    if (this.accountInf.wardId !== null) {
      this.wId = this.wardId;
      await this.getAddress();
    }
    this.isLoading = false;
  }
  async getAvatar() {
    await this.imageService
      .getAccImage(this.accountInf.avatar)
      .toPromise()
      .then((data: any) => {
        this.accImage = new ImageModel(data.name, data.url);
      });
  }
  async getAddress() {
    await this.accService
      .getWardByCode(this.wId)
      .toPromise()
      .then((data: any) => {
        this.dId = data.district_code;
      });
    await this.accService
      .getDistrictByCode(this.dId)
      .toPromise()
      .then((data: any) => {
        this.pId = data.province_code;
      });
    for (const d of this.districts) {
      if (d.province_code === this.pId) {
        this.dList.push(d);
      }
    }
    for (const w of this.wards) {
      if (w.district_code === this.dId) {
        this.wList.push(w);
      }
    }
  }
  getWId(): number {
    return Number(this.wId);
  }
  showDList() {
    this.inputDistrict = [];
    this.inputWard = [];
    for (const d of this.districts) {
      if (d.province_code === this.pId) {
        this.inputDistrict.push(d);
      }
    }
  }
  showWList() {
    this.inputWard = [];
    for (const w of this.wards) {
      if (w.district_code === this.dId) {
        this.inputWard.push(w);
      }
    }
  }
  clearOnProvince() {
    this.inputDistrict = [];
    this.inputWard = [];
    this.dId = null;
    this.wId = null;
  }
  clearOnDistrict() {
    this.inputWard = [];
    this.dId = null;
    this.wId = null;
  }
  async nextProcess() {
    this.submitted = true;
    if (this.submitted && this.adjustAccount.valid) {
      if (this.changeAvatar === true) {
        await this.imageService
          .uploadImageAll(this.fileToUpload, UTIL.DEFAULT_IMAGE_ACCOUNTS)
          .toPromise()
          .then((data: any) => {
            if (data !== null && data !== undefined) {
              this.accountInf.avatar = data.path;
            }
          });
      }
      this.accountInf.email = this.adjustAccount.value.email;
      this.accountInf.firstname = this.adjustAccount.value.firstname;
      this.accountInf.lastname = this.adjustAccount.value.lastname;
      this.accountInf.phoneNumber = this.adjustAccount.value.phoneNumber;
      this.accountInf.gender = this.adjustAccount.value.gender;
      this.accountInf.wardId = this.adjustAccount.value.ward;
      this.accountInf.birthday = new Date(UtilClass.mapDate(this.adjustAccount.value.birthday)).getTime();
      this.accountInf.addressDetails = this.adjustAccount.value.addressDetails;
      console.log('account', this.accountInf);
      this.accService
        .updateAccount(this.accountInf)
        .toPromise()
        .then((data: any) => {
          if (data.statusCode !== undefined) {
            UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
          } else {
            this.activeModal.close(this.accountInf);
            UtilClass.showMessageAlert(
              UTIL.ICON_SUCCESS,
              UTIL.ALERT_MESSAGE_SUCCESS_DETAIL_ACCOUNT
            );
          }
        });
    }
  }
  getFullDate(): string {
    if (this.accountInf !== undefined) {
      let fullDate = new Date(this.accountInf.birthday);
      let d = String(fullDate.getDate());
      let m = String(fullDate.getMonth() + 1);
      let y = String(fullDate.getFullYear());
      if (d.length < 2) {
        d = '0' + d;
        if (m.length < 2) {
          m = '0' + m;
        }
      }
      return d + '-' + m + '-' + y;
    } else {
      return '';
    }
  }
  getWID(): boolean {
    if (this.wId !== null) {
      return false;
    } else {
      return true;
    }
  }
  handleFileUpload(files: any) {
    this.fileToUpload = files.files.item(0) as File;
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.accImage = new ImageModel(
        this.fileToUpload.name,
        event.target.result
      );
    };
    reader.readAsDataURL(this.fileToUpload);
    this.changeAvatar = true;
  }
  getImage(): string {
    return `url("${this.accImage.url}")`;
  }
}
