import {Injectable} from '@angular/core';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';

@Injectable()
export class NgbDateHelper {
  // helper to find out today's year, month, day
  private static currentDate() {
    const todayDate = new Date();

    return {
      year: todayDate.getFullYear(),
      month: todayDate.getMonth() + 1,
      day: todayDate.getDate()
    };
  }

  // helper to find out date value, min and max date
  dateMetaData() {
    const minDate = NgbDateHelper.currentDate();
    const maxDate = NgbDateHelper.currentDate();
    maxDate.year = maxDate.year + 1;

    const value = new NgbDate(NgbDateHelper.currentDate().year,
      NgbDateHelper.currentDate().month + 2,
      NgbDateHelper.currentDate().day);

    return {
      value,
      minDate,
      maxDate
    };
  }
}
