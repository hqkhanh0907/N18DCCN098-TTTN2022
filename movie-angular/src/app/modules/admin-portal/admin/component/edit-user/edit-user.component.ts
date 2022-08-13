import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AccountService } from 'src/app/service/shared/account.service';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { Account } from '../../../model/Account';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  acc: any;
  infoAccount!: Account;
  updateAccountForm!: FormGroup;
  submitted = false;
  statuses: any;
  roles: any;
  roleSelect: any = [];
  roleOfAcc: any = [];
  statusSelect: any;
  roleSelected: any = [];
  genderSelect: any;
  status = [{ id: true, name: 'Active' }, { id: false, name: 'Inactive' }];
  genders = [
    { id: true, name: 'Male' },
    { id: false, name: 'Female' },
  ];
  constructor(private accountService: AccountService,
    public dialogRef: MatDialogRef<EditUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.updateAccountForm = new FormGroup({
      userName: new FormControl({value: null, disabled: true}, Validators.required),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      gender: new FormControl(null, Validators.required),
      status: new FormControl(null, Validators.required),
      roles: new FormControl(null, Validators.required),
    });
  }

  async ngOnInit() {
    await this.getRoles();
    await this.getUsers();
    this.updateAccountForm = new FormGroup({
      userName: new FormControl({value: this.acc.username, disabled: true}, Validators.required),
      firstName: new FormControl(this.acc.firstname, Validators.required),
      lastName: new FormControl(this.acc.lastname, Validators.required),
      email: new FormControl(this.acc.email, [Validators.required, Validators.email]),
      gender: new FormControl(this.acc.gender, Validators.required),
      status: new FormControl(this.acc.enable, Validators.required),
      roles: new FormControl(this.roleOfAcc, Validators.required),
    });
    console.log(this.updateAccountForm.value);
  }

  async getUsers() {
    await this.accountService.getAccount(this.data).toPromise().then((value: any) => {
      this.acc = value;
    });
    if (this.acc.groupOfRolesDtos) {
      for (const item of this.acc.groupOfRolesDtos) {
        item.accountRole.name = this.jsUcfirst(item.accountRole.name);
        this.roleOfAcc.push(item.accountRole);
      }
    } else {
      this.roleOfAcc = null;
    }
  }

  onNoClick() {
    this.dialogRef.close(true);
  }

  async saveUser() {
    this.submitted = true;
    this.setRoleForAccount();
    this.setUpdateAcc();
    if (this.updateAccountForm.valid) {
      await this.accountService.updateAccount(this.acc).toPromise().then((value: any) => {
        if (value.statusCode === undefined) {
          UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_EDIT_ACCOUNT);
          this.dialogRef.close(true);
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, value.message);
        }
      });
    }
  }
  setUpdateAcc() {
    this.acc.firstname = this.updateAccountForm.value.firstName;
    this.acc.lastname = this.updateAccountForm.value.lastName;
    this.acc.email = this.updateAccountForm.value.email;
    this.acc.gender = this.updateAccountForm.value.gender;
    this.acc.enable = this.updateAccountForm.value.status;
    this.acc.groupOfRolesDtos = this.roleSelected;
  }
  setRoleForAccount() {
    this.roleSelected = [];
    for (const item of this.updateAccountForm.value.roles) {
      let roleId = {
        accountId: this.acc.id,
        roleId: item.id
      }
      let role = {
        id: roleId
      }
      this.roleSelected.push(role);
    }
  }

  getIndexRoles(id: any): number {
    let index: number = 0;
    for (let i = 0; i < this.roles.length; i++) {
      if (Number(this.roles[i].id) === Number(id)) {
        index = i;
        break;
      }
    }
    return index;
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
