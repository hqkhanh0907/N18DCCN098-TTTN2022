import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AccountService } from 'src/app/service/shared/account.service';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
  isLoading = false;
  showBills: any[] = [];
  accId: any;
  bills: any[] = [];
  status = [{ id: 1, name: 'Paid' }, { id: 0, name: 'Unpaid' }]
  selectForm = new FormGroup({
    status: new FormControl(1)
  });
  constructor(private accountService: AccountService) { }

  async ngOnInit() {
    this.accId = Number(sessionStorage.getItem('idAcc'));
    if (this.accId) {
      await this.getBillByAccId();
    }
    this.setBillShow(Number(this.selectForm.value.status));
    setTimeout(() => { this.isLoading = true; }, 1500);
  }
  async getBillByAccId() {
    await this.accountService.getBillByAccId(this.accId).toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.bills = data;
      }
    });
  }
  setBillShow(status: number) {
    this.showBills = [];
    for (let item of this.bills) {
      if (item.status === status) {
        this.showBills.push(item);
      }
    }
  }
  showBill() {
    this.isLoading = false;
    this.setBillShow(Number(this.selectForm.value.status));
    setTimeout(() => {
      this.isLoading = true;
    }, 1000);
  }
  checkBill() {
    if (this.showBills) {
      if (this.showBills.length === 0) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
}
