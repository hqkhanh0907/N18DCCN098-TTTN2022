import {Component, OnInit} from '@angular/core';
import {Sort} from '@angular/material/sort';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {AddUserComponent} from "../add-user/add-user.component";
import {EditUserComponent} from "../edit-user/edit-user.component";
import { AccountService } from 'src/app/service/shared/account.service';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { UTIL } from 'src/app/shared/util/util';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  submitted = true;
  accounts: any[] = [];
  searchUser = '';
  entries = 10;
  yourUsername: any;
  sortedData!: any[];
  p: any;

  constructor(private accountService: AccountService,
              private router: Router,
              private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAllAccount();
    this.yourUsername = sessionStorage.getItem('username');
  }

  async getAllAccount() {
    await this.accountService.getAllAccount().toPromise().then((value: any) => {
      if (value.statusCode === undefined) {
        this.accounts = value;
        this.sortedData = this.accounts.slice();
      }
    });
  }

  jsUcfirst(name: string) {
    name = name.slice(5);
    name = name.toLowerCase();
    return name.charAt(0).toUpperCase() + name.slice(1);
  }
  sortData(sort: Sort) {
    const data = this.accounts.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'username':
          return this.compare(a.username, b.username, isAsc);
        case 'firstname':
          return this.compare(a.firstname, b.firstname, isAsc);
        case 'lastname':
          return this.compare(a.lastname, b.lastname, isAsc);
        case 'email':
          return this.compare(a.email, b.email, isAsc);
        case 'status':
          return this.compare(a.enabled, b.enabled, isAsc);
        default:
          return 0;
      }
    });
  }

  public compare(a: string, b: string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  reloadAccList() {
    this.getAllAccount().then(() => {
    });
  }

  removeAccount(username: string) {
    UtilClass.showRequestDeleteMovie(UTIL.ALERT_MESSAGE_DELETE_ACCOUNT).then((result: any) => {
      if (result) {
        this.accountService.deleteAccByUsername(username).toPromise().then((value: any) => {
          if (value.statusCode === undefined) {
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_REMOVE_ACCOUNT);
            this.reloadAccList();
          }
        });
      }
    });
  }

  editAccount(id: any) {
    const yourId = Number(sessionStorage.getItem('idAcc'));
    if (yourId !== undefined && yourId === id) {
      this.router.navigate(['admin/pages/profile']).then(() => {
      });
    } else {
      this.openEditAccount(id);
    }
  }

  openEditAccount(id: any) {
    const dialogRef = this.matDialog.open(EditUserComponent, {data: id, disableClose: true});
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value === true) {
        this.reloadAccList();
      }
    });
  }

  addUser() {
    const dialogRef = this.matDialog.open(AddUserComponent, {});
    dialogRef.afterClosed().subscribe((value: any) => {
      if (value === true) {
        this.reloadAccList();
      }
    });
  }
}
