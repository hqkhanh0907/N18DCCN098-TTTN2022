import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  currentChoice: any;
  constructor(
    private route: Router,
    private loginService: LoginServiceService
  ) {}

  async ngOnInit() {
    window.scrollTo(0, 0);
  }
  getActive(choice: string): string {
    this.currentChoice = sessionStorage.getItem('item-account');
    if (this.currentChoice == choice) return 'active';
    else return '';
  }
  goLink(symbol: any) {
    sessionStorage.removeItem('item-account');
    sessionStorage.setItem('item-account', symbol);
    this.route.navigate(['/mp/account/' + symbol]).then(() => {
      location.reload();
    });
  }
  logout() {
    this.loginService.logOut();
  }
}
