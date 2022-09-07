import { Component, Input, OnInit } from '@angular/core';
import { AccountService } from 'src/app/service/shared/account.service';
import { MovieService } from 'src/app/service/shared/movie.service';
import { UTIL } from 'src/app/shared/util/util';
import { UtilClass } from 'src/app/shared/util/utilClass';

@Component({
  selector: 'app-evaluate-movie',
  templateUrl: './evaluate-movie.component.html',
  styleUrls: ['./evaluate-movie.component.css'],
})
export class EvaluateMovieComponent implements OnInit {
  @Input() movie: any;
  @Input() account: any;
  rate = 0;
  evaComment = '';
  evaluates = [];
  evaluateShow = [];
  numShow = 5;
  constructor(private accountService: AccountService,
    private movieService: MovieService) { }

  async ngOnInit() {
    await this.getEvaluateInMovie();
    this.setEvaluateShow();
  }
  async getEvaluateInMovie() {
    if (this.movie) {
      await this.movieService.getAllEvaluateInMovieId(this.movie.id).toPromise().then((data: any) => {
        if (data.statusCode === undefined) {
          this.evaluates = data;
        }
      });
    }
  }
  checkEvaluate(): boolean {
    if (this.evaluates) {
      if (this.evaluates.length === 0) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
  showMore() {
    this.numShow += 5;
    if (this.evaluates.length <= this.numShow) {
      this.numShow = this.evaluates.length;
      this.setEvaluateShow();
    } else {
      this.setEvaluateShow();
    }
  }
  setEvaluateShow() {
    this.evaluateShow = [];
    if (this.checkEvaluate()) {
      for (let i = 0; i < this.numShow; i++) {
        this.evaluateShow.push(this.evaluates[i]);
      }
    }
  }
  sendEvaluate() {
    if (this.account) {
      if (this.evaComment.length > 10 && this.evaComment.length < 200) {
        const evaluate = {
          "id": {
            "userId": this.account,
            "movieId": this.movie.id
          },
          "rate": this.rate,
          "content": this.evaComment,
          "date": new Date().getTime(),
          "status": 1
        }
        this.accountService.saveEvaluate(evaluate).toPromise().then(async (data: any) => {
          if (data === true) {
            await this.getEvaluateInMovie();
            this.setEvaluateShow();
          }
        });
      } else {
        UtilClass.showMessBasic(UTIL.ALERT_LENGTH_COMMENT);
      }
    } else {
      UtilClass.showMessBasic(UTIL.WARNING_LOGIN);
    }
  }
}
