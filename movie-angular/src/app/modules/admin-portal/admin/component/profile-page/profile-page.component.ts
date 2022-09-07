import { Component, DoCheck, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbDateAdapter, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { AccountService } from 'src/app/service/shared/account.service';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { CustomAdapter } from 'src/app/shared/util/custom-adapter ';
import { CustomDateParserFormatter } from 'src/app/shared/util/custom-date-parser-formatter ';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css'],
  providers: [
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
  ],
})

export class ProfilePageComponent implements OnInit {
  accId!: any;
  maxDate: any;
  dayStart: any;
  infoAccount: any;
  locationDetail: any;
  updateAccountForm = new FormGroup({
    province: new FormControl(null),
    district: new FormControl(null),
    ward: new FormControl(null),
    userName: new FormControl(null),
    firstName: new FormControl(null, Validators.required),
    lastName: new FormControl(null, Validators.required),
    email: new FormControl(null, [Validators.required, Validators.email]),
    addressDetails: new FormControl(null),
    gender: new FormControl(true, Validators.required),
    birthday: new FormControl(null, [Validators.required, Validators.pattern(UTIL.DATE_PATTERN)]),
    phoneNumber: new FormControl(null, Validators.pattern(UTIL.NUMBER_PHONE_PATTERN))
  });;
  uploadAccImage!: FormGroup;
  submitted = false;
  uploadImageSubmitted = false;
  city = new FormControl(null);
  district = new FormControl({ value: null, disabled: true });
  town = new FormControl({ value: null, disabled: true });
  accImage: ImageModel = new ImageModel(UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
  fileToUpload!: File;
  provinces: any = [];
  districts: any;
  wards: any;
  inputDistrict: any = [];
  inputWard: any = [];
  isLoading = false;
  pId = null;
  dId = null;
  wId = null;
  genders = [
    { id: true, name: 'Male' },
    { id: false, name: 'Female' },
  ];

  constructor(
    private uploadImageService: ImageService,
    private accountService: AccountService,
    private loginService: LoginServiceService,
    private accService: AccountService) {
  }
  async ngOnInit() {
    this.isLoading = true;
    this.accId = sessionStorage.getItem('idAcc');
    this.uploadAccImage = new FormGroup({
      accImage: new FormControl(null, Validators.required)
    });
    this.maxDate = { year: new Date().getFullYear(), month: new Date().getMonth(), day: new Date().getDate() };
    await this.getAllAddress();
    await this.getAccInfo();
    this.updateAccountForm = new FormGroup({
      province: new FormControl(null),
      district: new FormControl(null),
      ward: new FormControl(null),
      userName: new FormControl(this.infoAccount.username, Validators.required),
      firstName: new FormControl(this.infoAccount.firstname, Validators.required),
      lastName: new FormControl(this.infoAccount.lastname, Validators.required),
      email: new FormControl(this.infoAccount.email, [Validators.required, Validators.email]),
      addressDetails: new FormControl(this.infoAccount.addressDetails),
      gender: new FormControl(this.infoAccount.gender, Validators.required),
      birthday: new FormControl(this.dayStart, [Validators.required, Validators.pattern(UTIL.DATE_PATTERN)]),
      phoneNumber: new FormControl(this.infoAccount.phoneNumber, Validators.pattern(UTIL.NUMBER_PHONE_PATTERN))
    });
    if (this.infoAccount.wardId !== null) {
      this.wId = this.infoAccount.wardId;
      await this.getAddress();
    }
    await this.getImgAcc();
    this.isLoading = false;
  }
  show() {
    console.log(this.updateAccountForm.value);
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
    this.inputDistrict = [];
    for (const d of this.districts) {
      if (d.province_code === this.pId) {
        this.inputDistrict.push(d);
      }
    }
    this.inputWard = [];
    for (const w of this.wards) {
      if (w.district_code === this.dId) {
        this.inputWard.push(w);
      }
    }
  }
  async getAllAddress() {
    await this.accService
      .getAllProvince()
      .toPromise()
      .then((data: any) => {
        this.provinces = data;
      });
    await this.accService
      .getAllDistrict()
      .toPromise()
      .then((data: any) => {
        this.districts = data;
      });
    await this.accService
      .getAllWard()
      .toPromise()
      .then((data: any) => {
        this.wards = data;
      });
  }
  showDList() {
    this.locationDetail = null;
    this.inputDistrict = [];
    this.inputWard = [];
    for (const d of this.districts) {
      if (d.province_code === this.updateAccountForm.value.province) {
        this.inputDistrict.push(d);
      }
    }
  }
  showWList() {
    this.inputWard = [];
    this.locationDetail = null;
    for (const w of this.wards) {
      if (w.district_code === this.updateAccountForm.value.district) {
        this.inputWard.push(w);
      }
    }
  }
  clearOnProvince() {
    this.locationDetail = null;
    this.inputDistrict = [];
    this.inputWard = [];
    this.dId = null;
    this.wId = null;
  }
  clearOnDistrict() {
    this.locationDetail = null;
    this.inputWard = [];
    this.dId = null;
    this.wId = null;
  }
  clearWardId() {
    this.locationDetail = null;
    this.wId = null;
  }


  async getAccInfo() {
    await this.accountService.getAccount(this.accId).toPromise().then((data: any) => {
      this.infoAccount = data;
      const date: Date = new Date(this.infoAccount.birthday);
      const month: number = date.getMonth() + 1;
      this.dayStart = date.getDate() + '-' + month + '-' + date.getFullYear();
    });
  }

  async getImgAcc() {
    await this.loginService.getAccImage(this.infoAccount.avatar).toPromise().then((data: any) => {
      if (data != null) {
        this.accImage = data;
      } else {
        if (this.infoAccount.gender === true) {
          this.accImage = new ImageModel(UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE);
        } else {
          this.accImage = new ImageModel(UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_FEMALE, UTIL.DEFAULT_ACCOUNT_IMAGE_URL_FEMALE);
        }
      }
    });
  }

  async onSubmit() {
    this.submitted = true;
    this.infoAccount.firstname = this.updateAccountForm.value.firstName;
    this.infoAccount.addressDetails = this.updateAccountForm.value.addressDetails;
    this.infoAccount.birthday = new Date(UtilClass.mapDate(this.updateAccountForm.value.birthday)).getTime();
    this.infoAccount.email = this.updateAccountForm.value.email;
    this.infoAccount.gender = this.updateAccountForm.value.gender;
    this.infoAccount.lastname = this.updateAccountForm.value.lastName;
    this.infoAccount.phoneNumber = this.updateAccountForm.value.phoneNumber;
    this.infoAccount.wardId = this.updateAccountForm.value.ward;
    if (!this.updateAccountForm.invalid) {
      console.log(JSON.stringify(this.infoAccount));
      await this.accountService.updateAccount(this.infoAccount).toPromise().then((data: any) => {
        if (data.statusCode !== undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_DETAIL_ACCOUNT);
        }
      });
    }
  }

  handleFileUpload(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.fileToUpload = files.item(0) as File;
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.accImage = new ImageModel(this.fileToUpload.name, event.target.result);
    };
    reader.readAsDataURL(this.fileToUpload);
  }

  getWID(): boolean {
    if (this.wId !== null) {
      return false;
    } else {
      return true;
    }
  }
  async uploadAccImageSubmit() {
    this.uploadImageSubmitted = true;
    if (!this.uploadAccImage.invalid) {
      await this.uploadImageService.uploadImageAcc(this.fileToUpload, this.accId).toPromise().then((data: any) => {
        UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_AVATAR);
        setTimeout(() => {
          location.reload();
        }, 2000);
      });
    }
  }


}
