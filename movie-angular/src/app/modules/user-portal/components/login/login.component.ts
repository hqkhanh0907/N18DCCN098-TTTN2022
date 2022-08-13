import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup = new FormGroup({});
  submitted = false;
  loginResponse: any = null;
  errorMessage: string = '';
  constructor(
    private router: Router,
    private loginService: LoginServiceService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.loginForm = new FormGroup({
      username: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
    });
  }
  goSignUp() {
    this.router.navigate(['/mp/signup']);
  }
  async checkLogin() {
    this.submitted = true;
    const loginRequest = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password,
    };
    await this.loginService
      .loginAdmin(loginRequest)
      .toPromise()
      .then((data: any) => {
        console.log(data);
        if (data.statusCode !== undefined) {
          this.errorMessage = data.message;
        } else {
          this.loginResponse = data;
          this.loginService.logOut();
          this.loginService.setSessionLogin(this.loginResponse);
          if (sessionStorage.getItem('token')) {
            window.location.href = '/mp/home';
          }
        }
      });
      console.log(this.errorMessage);
  }
}
