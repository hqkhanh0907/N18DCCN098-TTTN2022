import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { LoginServiceService } from './shared/login-service.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGaurdServiceService {
  constructor(
    private router: Router,
    private authService: LoginServiceService
  ) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn() === false) {
      this.router.navigate(['admin/login']).then(() => {});
      return false;
    } else {
      return true;
    }
  }
}
