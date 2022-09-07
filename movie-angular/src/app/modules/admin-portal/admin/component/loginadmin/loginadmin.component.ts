import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';
import { LoginForm } from '../../../model/login';
import { LoginResponse } from '../../../model/loginRespone';

@Component({
  selector: 'app-loginadmin',
  templateUrl: './loginadmin.component.html',
  styleUrls: ['./loginadmin.component.css']
})
export class LoginadminComponent implements OnInit {
  submitted = false;
  loginRequest!: LoginForm;
  loginResponse: any;
  errorMessage!: string;
  public loginForm: FormGroup = new FormGroup({});

  constructor(
    private router: Router,
    private loginAdminService: LoginServiceService
  ) {
  }

  ngOnInit() {
    this.loginAdminService.logOut();
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  async checkLogin() {
    this.submitted = true;
    this.loginRequest = new LoginForm(this.loginForm.value.username, this.loginForm.value.password);
    await this.loginAdminService.loginAdmin(this.loginRequest).toPromise().then((data: any) => {
      if (data.statusCode != null) {
        this.errorMessage = data.message;
      } else {
        this.loginResponse = data;
        if (this.checkRole(this.loginResponse.accountRoleDtos)) {
          sessionStorage.setItem('idAcc', this.loginResponse.accId.toString());
          sessionStorage.setItem('token', 'Bearer ' + this.loginResponse.authenticationToken);
          sessionStorage.setItem('username', this.loginResponse.username);
          sessionStorage.setItem('rolename', 'ROLE_ADMIN');
          this.router.navigate(['/admin/pages/movie']).then(r => {
            location.reload();
          });
        } else {
          this.errorMessage = 'Account does not have access permission';
        }
      }
    }, error => {
    });
  }
  checkRole(roles: any): boolean {
    for (let item of roles) {
      if (item.name === 'ROLE_ADMIN') {
        return true;
      }
    }
    return false;
  }
}
