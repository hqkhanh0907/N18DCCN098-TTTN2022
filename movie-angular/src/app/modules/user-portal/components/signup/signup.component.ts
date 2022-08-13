import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbDateAdapter, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { CustomAdapter } from 'src/app/shared/util/custom-adapter ';
import { CustomDateParserFormatter } from 'src/app/shared/util/custom-date-parser-formatter ';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
  ],
})
export class SignupComponent implements OnInit {
  submitted = false;
  singUpAccount!: FormGroup;
  genders = [
    { id: true, name: 'Male' },
    { id: false, name: 'Female' },
  ];
  gender = true;
  constructor(private signService: LoginServiceService, private router: Router) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.singUpAccount = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
      email: new FormControl(null, [
        Validators.required,
        Validators.email,
      ]),
      firstname: new FormControl(
        null,
        Validators.required
      ),
      lastname: new FormControl(null, Validators.required),
      birthday: new FormControl(null, [
        Validators.required,
        Validators.pattern(UTIL.DATE_PATTERN),
      ]),
      gender: new FormControl(null, Validators.required),
    });
  }
  signUp() {
    this.submitted = true;
    const signUpRequest = {
      username: this.singUpAccount.value.username,
      password: this.singUpAccount.value.password,
      email: this.singUpAccount.value.email,
      firstname: this.singUpAccount.value.firstname,
      lastname: this.singUpAccount.value.lastname,
      birthday: new Date(this.singUpAccount.value.birthday).getTime(),
      gender: this.singUpAccount.value.gender
    }
    if (this.submitted && this.singUpAccount.valid) {
      this.signService.signUp(signUpRequest).subscribe((data: any) => {
        console.log(data);
        if (data.statusCode === undefined) {
          // this.router.navigate(['mp/login']);
          UtilClass.showMessSuccessfully(
            UTIL.TITLE_SUCCESSFULLY,
            UTIL.ALERT_MESSAGE_SUCCESS_SIGNUP,
            UTIL.ICON_SUCCESS).then((result) => {
              if(result) {
                this.router.navigate(['/mp/login']);
              }
            });
        } else {
          UtilClass.showMessageAlert(UTIL.ICON_ERROR, data.message);
        }
      });
    }
  }
}
