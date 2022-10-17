import { ImageService } from 'src/app/service/shared/upload-image.service';
import Swal from 'sweetalert2';

export class UtilClass {
  static DELIMITER = '-';
  static mapDate(birthday: any): string {
    const date = birthday.split(this.DELIMITER);
    return `${parseInt(date[2], 10)}${this.DELIMITER}${parseInt(date[1], 10)}${this.DELIMITER}${parseInt(date[0], 10)}`
  }
  static showMessageAlert(icon: any, title: any) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 4000,
      timerProgressBar: false,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer);
        toast.addEventListener('mouseleave', Swal.resumeTimer);
      },
    });

    Toast.fire({
      icon,
      title,
    });
  }
  static showMessBasic(message: any) {
    Swal.fire({
      title: "Notify",
      text: message
    });
  }
  static async showMessSuccessfully(title: any, message: any, icon: any) {
    let resultSf = false;
    await Swal.fire(title, message, icon).then((result: any) => {
      if (result.isConfirmed) {
        resultSf = true;
       }
    });
    return resultSf;
  }
  static async showFailedPayment(titleText: string) {
    await Swal.fire({
      text: titleText,
      icon: 'warning',
    });
  }
  static async showRequestDeleteMovie(titleText: string) {
    let resultDelete = false;
    await Swal.fire({
      title: 'Are you sure?',
      text: titleText,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        resultDelete = true;
      }
    });
    return resultDelete;
  }
  jsUcfirst(name: any) {
    name = name.slice(5);
    name = name.toLowerCase();
    return name.charAt(0).toUpperCase() + name.slice(1);
  }
  static stringToDate(_date: any, _format: any, _delimiter: any): Date {
    var formatLowerCase = _format.toLowerCase();
    var formatItems = formatLowerCase.split(_delimiter);
    var dateItems = _date.split(_delimiter);
    var monthIndex = formatItems.indexOf('mm');
    var dayIndex = formatItems.indexOf('dd');
    var yearIndex = formatItems.indexOf('yyyy');
    var month = parseInt(dateItems[monthIndex]);
    month -= 1;
    var formatedDate = new Date(
      dateItems[yearIndex],
      month,
      dateItems[dayIndex]
    );
    return formatedDate;
  }
}
