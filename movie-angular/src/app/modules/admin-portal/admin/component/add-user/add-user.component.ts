import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from "@angular/material/dialog";
import { AccountService } from 'src/app/service/shared/account.service';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  maxDate: any;
  infoAccount: any;
  addAccountForm!: FormGroup;
  submitted = false;
  statuses: any;
  roles: any;
  roleSelect: any = [];
  roleOfAcc: any = [];
  statusSelect: any;
  roleSelected: any = [];
  genderSelect: any;
  status = [{ id: true, name: 'Active' }, { id: false, name: 'Inactive' }]
  roleFormControl = new FormControl(-1);
  statusFormControl = new FormControl(-1);
  genders = [
    { id: true, name: 'Male' },
    { id: false, name: 'Female' },
  ];

  constructor(private accountService: AccountService,
    public dialogRef: MatDialogRef<AddUserComponent>) {
    this.addAccountForm = new FormGroup({
      userName: new FormControl(null, Validators.required),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      gender: new FormControl(null, Validators.required),
      status: new FormControl(null, Validators.required),
      roles: new FormControl(null, Validators.required),
    });
  }

  ngOnInit() {
    this.getRoles();
  }

  onNoClick() {
    this.dialogRef.close(true);
  }

  async saveUser() {
    this.submitted = true;
    this.setRoleForAccount();
    this.setAddAcc();
    if (this.addAccountForm.valid) {
      await this.accountService.addAccount(this.infoAccount).toPromise().then((value: any) => {
        if (value.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_ADD_ACCOUNT);
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, value.message);
        }
      });
    }
  }
  setAddAcc() {
    this.infoAccount = {
      "username": this.addAccountForm.value.userName,
      "enable": this.addAccountForm.value.status,
      "email": this.addAccountForm.value.email,
      "lastname": this.addAccountForm.value.lastName,
      "firstname": this.addAccountForm.value.firstName,
      "gender": this.addAccountForm.value.gender,
      "groupOfRolesDtos": this.roleSelected
    }
  }

  setRoleForAccount() {
    this.roleSelected = [];
    for (const item of this.addAccountForm.value.roles) {
      let role = {
        accountRole: item
      }
      this.roleSelected.push(role);
    }
  }
  jsUcfirst(name: string) {
    name = name.slice(5);
    name = name.toLowerCase();
    return name.charAt(0).toUpperCase() + name.slice(1);
  }

  async getRoles() {
    await this.accountService.getAllRole().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.roles = data;
      }
    });
    for (const role of this.roles) {
      role.name = this.jsUcfirst(role.name);
    }
  }
}
