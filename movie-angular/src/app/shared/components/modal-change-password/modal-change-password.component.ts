import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { AccountService } from 'src/app/service/shared/account.service';
import { UTIL } from '../../util/util';
import { UtilClass } from '../../util/utilClass';

@Component({
  selector: 'app-modal-change-password',
  templateUrl: './modal-change-password.component.html',
  styleUrls: ['./modal-change-password.component.css'],
})
export class ModalChangePasswordComponent implements OnInit {
  @Input() accountInf: any;
  checkOldPassUrl = false;
  adjustPassword!: FormGroup;
  submitted = false;
  constructor(
    public activeModal: NgbActiveModal,
    private accService: AccountService
  ) {}

  ngOnInit() {
    this.adjustPassword = new FormGroup({
      oldPass: new FormControl('', Validators.required),
      newPass: new FormControl('', Validators.required),
      rePass: new FormControl('', Validators.required),
    });
  }
  async checkOldPass() {
    const password = {
      password: this.adjustPassword.value.oldPass,
    };
    await this.accService
      .checkOldPass(password, this.accountInf.username)
      .toPromise()
      .then((data: any) => {
        this.checkOldPassUrl = data;
      });
  }
  changePassword(): Observable<any> {
    const password = {
      password: this.adjustPassword.value.newPass,
    };
    return this.accService.changePass(password, this.accountInf.username);
  }
  checkMatchPass(): boolean {
    if (
      this.adjustPassword.value.newPass === this.adjustPassword.value.rePass
    ) {
      return true;
    } else {
      return false;
    }
  }
  checkMatchOldPass(): boolean {
    if (
      this.adjustPassword.value.oldPass === this.adjustPassword.value.newPass
    ) {
      return true;
    } else {
      return false;
    }
  }
  async nextProcess() {
    this.submitted = true;
    if (
      this.submitted &&
      !this.adjustPassword.invalid &&
      !this.checkMatchOldPass() &&
      this.checkMatchPass()
    ) {
      await this.checkOldPass();
      if (this.checkOldPassUrl) {
        await this.changePassword()
          .toPromise()
          .then((data: any) => {
            if (data === true) {
              this.activeModal.close('Close click');
              UtilClass.showMessageAlert(
                UTIL.ICON_SUCCESS,
                UTIL.CHANGE_PASSWORD_SUCCESSFULLY
              );
            }
          });
      }
    }
  }
}
