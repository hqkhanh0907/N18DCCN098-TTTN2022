import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';
import { MoviePromotionService } from '../../../service/movie-promotion.service';
import { AddPromotionComponent } from '../add-promotion/add-promotion.component';
import { EditPromotionComponent } from '../edit-promotion/edit-promotion.component';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.css']
})
export class PromotionComponent implements OnInit {
  submitted = true;
  promotions: any[] = [];
  searchPromotion = '';
  entries = 10;
  sortedData!: any[];
  p: any;

  constructor(private promotionService: MoviePromotionService,
    private matDialog: MatDialog) {
  }

  async ngOnInit() {
    await this.getAllPromotion();
  }

  async getAllPromotion() {
    await this.promotionService.getPromotions().toPromise().then((value: any) => {
      if (value.statusCode === undefined) {
        console.log(value);
        this.promotions = value;
        this.sortedData = this.promotions.slice();
      }
    });
  }
  sortData(sort: Sort) {
    const data = this.promotions.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'code_name':
          return this.compare(a.code_name, b.code_name, isAsc);
        case 'start_date':
          return this.compare(a.start_date, b.start_date, isAsc);
        case 'end_date':
          return this.compare(a.end_date, b.end_date, isAsc);
        case 'discount':
          return this.compare(a.percent_discount, b.percent_discount, isAsc);
        default:
          return 0;
      }
    });
  }

  public compare(a: any, b: any, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  reloadAccList() {
    this.getAllPromotion().then(() => {
    });
  }

  removePromo(id: any) {
    UtilClass.showRequestDeleteMovie(UTIL.ALERT_MESSAGE_DELETE_GENRE).then((result: any) => {
      if (result) {
        this.promotionService.deleteGenre(id).toPromise().then((value: any) => {
          console.log(value);
          if (value.statusCode === undefined) {
            this.reloadAccList();
            UtilClass.showMessageAlert(UTIL.ICON_SUCCESS, UTIL.ALERT_MESSAGE_SUCCESS_REMOVE_ACCOUNT);
          }
        });
      }
    });
  }

  openEditPromo(promo: any) {
    const dialogRef = this.matDialog.open(EditPromotionComponent, { data: promo, disableClose: true });
    dialogRef.afterClosed().subscribe(() => {
      this.reloadAccList();
    });
  }
  asDate(value: any) {
    let date = new Date(value);
    let d = date.getDate();
    let m = date.getMonth() + 1;
    let y = date.getFullYear();
    return `${d}-${m}-${y}`;

  }
  checkPromoDis(promo: any): boolean {
    if (promo.code_name === 'DEFAULT') {
      return true;
    } else {
      return false;
    }
  }
  addPromo() {
    const dialogRef = this.matDialog.open(AddPromotionComponent, {});
    dialogRef.afterClosed().subscribe(() => {
      this.reloadAccList();
    });
  }
}