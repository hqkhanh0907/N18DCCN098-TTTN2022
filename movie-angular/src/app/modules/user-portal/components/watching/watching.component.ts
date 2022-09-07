import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/service/shared/account.service';
import { LoginServiceService } from 'src/app/service/shared/login-service.service';

@Component({
  selector: 'app-watching',
  templateUrl: './watching.component.html',
  styleUrls: ['./watching.component.css']
})
export class WatchingComponent implements OnInit {
  accId: any;
  movies: any[] = [];
  isLoading = false;

  constructor(private accountService: AccountService,
    private loginService: LoginServiceService) { }
  async ngOnInit() {
    if (this.loginService.isUserLoggedIn()) {
      this.accId = Number(sessionStorage.getItem('idAcc'));
    }
    await this.getMovies();
    setTimeout(() => {
      this.isLoading = true;
    }, 1500);
  }
  checkMovie() {
    if(this.movies) {
      if (this.movies.length === 0) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
  async getMovies() {
    await this.accountService.getMovieHistoryByAccId(this.accId).toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.movies = data;
      }
    });
  }

}
