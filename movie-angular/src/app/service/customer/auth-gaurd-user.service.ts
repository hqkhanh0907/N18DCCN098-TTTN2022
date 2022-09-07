import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from '@angular/router';
import { LoginServiceService } from '../shared/login-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdUserService {
  constructor(private router: Router, private authService: LoginServiceService) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn() === false) {
      this.router.navigate(['mp/home']).then(() => {});
      return false;
    } else {
      return true;
    }
  }
}
