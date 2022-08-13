import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountService } from 'src/app/service/shared/account.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ModalAdjustPersonalInfoComponent } from 'src/app/shared/components/modal-adjust-personal-info/modal-adjust-personal-info.component';
import { ModalChangePasswordComponent } from 'src/app/shared/components/modal-change-password/modal-change-password.component';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css'],
})
export class AccountProfileComponent implements OnInit {
  accountInf: any;
  pList: any = [];
  dList: any = [];
  wList: any = [];
  inputDistrict: any = [];
  inputWard: any = [];
  isLoading = true;
  mapAddress = '';
  image = '../../../../assets/img/avatar-default.png';
  accImage: ImageModel = new ImageModel(
    UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE,
    UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE
  );
  constructor(
    private accService: AccountService,
    private modalService: NgbModal,
    private imageService: ImageService
  ) {
    sessionStorage.setItem('item-account', 'profile');
  }

  async ngOnInit() {
    window.scrollTo(0, 0);
    this.isLoading = true;
    await this.getAllAddress();
    await this.getAcc();
    if (this.accountInf.avatar) {
      await this.getAvatar();
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
  async getAcc() {
    let accId = sessionStorage['idAcc'];
    if (accId !== undefined) {
      await this.accService
        .getAccount(accId)
        .toPromise()
        .then((data: any) => {
          this.accountInf = data;
        });
      await this.getFullAddress();
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
  getGender(): string {
    if (this.accountInf.gender) {
      return 'Male';
    } else return 'Female';
  }
  async getFullAddress() {
    if (this.accountInf.wardId) {
      let p: any;
      let d: any;
      let w: any;
      await this.accService
        .getWardByCode(this.accountInf.wardId)
        .toPromise()
        .then((data: any) => {
          w = data;
        });
      await this.accService
        .getDistrictByCode(w.district_code)
        .toPromise()
        .then((data: any) => {
          d = data;
        });
      await this.accService
        .getProvinceByCode(d.province_code)
        .toPromise()
        .then((data: any) => {
          p = data;
        });
      for (const w of this.wList) {
        if (w.district_code === d.code) {
          this.inputWard.push(w);
        }
      }
      for (const d of this.dList) {
        if (d.province_code === p.code) {
          this.inputDistrict.push(d);
        }
      }
      this.mapAddress = w.name + ', ' + d.name + ', ' + p.name;
    }
  }
  async getAllAddress() {
    await this.accService
      .getAllProvince()
      .toPromise()
      .then((data: any) => {
        this.pList = data;
      });
    await this.accService
      .getAllDistrict()
      .toPromise()
      .then((data: any) => {
        this.dList = data;
      });
    await this.accService
      .getAllWard()
      .toPromise()
      .then((data: any) => {
        this.wList = data;
      });
  }
  getAddressDetails() { }
  changePass() {
    const modalRef = this.modalService.open(ModalChangePasswordComponent);
    modalRef.componentInstance.accountInf = this.accountInf;
  }
  adjustInfo() {
    const modalRef = this.modalService.open(ModalAdjustPersonalInfoComponent);
    if (this.accountInf.wardId !== null) {
      modalRef.componentInstance.wardId = Number(this.accountInf.wardId);
    } else {
      modalRef.componentInstance.wardId = null;
    }
    modalRef.componentInstance.accountInf = this.accountInf;
    modalRef.componentInstance.provinces = this.pList;
    modalRef.componentInstance.districts = this.dList;
    modalRef.componentInstance.wards = this.wList;
    modalRef.componentInstance.inputDistrict = this.inputDistrict;
    modalRef.componentInstance.inputWard = this.inputWard;
  }
}
