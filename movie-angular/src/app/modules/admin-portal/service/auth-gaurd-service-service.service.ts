import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from '@angular/router';
import {LoginAdminService} from './login-admin.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService {
  constructor(private router: Router, private authService: LoginAdminService) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn() === false) {
      this.router.navigate(['admin/login']).then(() => {});
      return false;
    } else {
      return true;
    }
  }
}
