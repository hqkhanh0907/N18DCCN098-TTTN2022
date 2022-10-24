import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApexAxisChartSeries, ApexChart, ApexDataLabels, ApexNonAxisChartSeries, ApexTitleSubtitle } from 'ng-apexcharts';
import { AccountService } from 'src/app/service/shared/account.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { MovieCastService } from '../../../service/movie-cast.service';
import { MovieGenreService } from '../../../service/movie-genre.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public movies: any;
  public accounts: any;
  public casts: any;
  public genres: any;
  public moviePie: any;
  public accountPie: any;
  public genreBar: any;
  //Account chart
  chartSeriesAcc: ApexNonAxisChartSeries = [10, 30];
  chartDetailsAcc: ApexChart = {
    type: 'pie',
    toolbar: {
      show: false
    }
  };
  chartLabelsAcc = ["Active", "Inactive"];
  chartTitleAcc: ApexTitleSubtitle = {
    text: 'Accounts',
    align: 'center'
  };
  //Movies chart
  chartSeriesMovie: ApexNonAxisChartSeries = [10, 30];
  chartDetailsMovie: ApexChart = {
    type: 'pie',
    toolbar: {
      show: false
    }
  };
  chartLabelsMovie = ["Premiered", "Comming"];
  chartTitleMovie: ApexTitleSubtitle = {
    text: 'Movies',
    align: 'center'
  };
  //Genre chart

  chartSeriesGenre: ApexAxisChartSeries = [{
    name: "distibuted",
    data: [10, 30]
  }];
  chartDetailsGenre: ApexChart = {
    type: 'bar',
    toolbar: {
      show: false
    }
  };
  chartLabelsGenre = ["Premiered", "Comming"];
  chartTitleGenre: ApexTitleSubtitle = {
    text: 'Genres',
    align: 'center'
  };
  chartDataLabels: ApexDataLabels = {
    enabled: true
  };
  constructor(private movieService: MovieService,
    private accountService: AccountService,
    private genreService: MovieGenreService,
    private castService: MovieCastService,
    private router: Router) { }

  async ngOnInit() {
    await this.movieService.getNumMovies().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.movies = data;
      }
    })
    await this.accountService.getNumAccount().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.accounts = data;
      }
    });
    await this.genreService.getNumGenre().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.genres = data;
      }
    });
    await this.castService.getNumCast().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.casts = data;
      }
    });
    await this.movieService.getMoviePie().toPromise().then((data: any) => {
      if(data.statusCode === undefined) {
        this.moviePie = data;
      }
    });
    await this.accountService.getAccPie().toPromise().then((data: any) => {
      if(data.statusCode === undefined) {
        this.accountPie = data;
      }
    });
    await this.genreService.getGenreBar().toPromise().then((data: any) => {
      if(data.statusCode === undefined) {
        this.genreBar = data;
      }
    });
    if(this.moviePie) {
      this.chartSeriesMovie = this.moviePie.data;
      this.chartLabelsMovie = this.moviePie.labels;
    }
    if(this.accountPie) {
      this.chartSeriesAcc = this.accountPie.data;
      this.chartLabelsAcc = this.accountPie.labels;
    }
    if(this.genreBar) {
      this.chartLabelsGenre = this.genreBar.labels;
      this.chartSeriesGenre[0].data = this.genreBar.data;
    }
  }
  goMovie() {
    this.router.navigate(['/admin/pages/movie']);
  }
  goAccount() {
    this.router.navigate(['/admin/pages/user']);
  }
  goGenre() {
    this.router.navigate(['/admin/pages/genre']);
  }
  goCast() {
    this.router.navigate(['/admin/pages/cast']);
  }
}
