import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { MovieCast } from '../../../model/MovieCast';
import { MovieCastService } from '../../../service/movie-cast.service';

@Component({
  selector: 'app-list-cast-movie',
  templateUrl: './list-cast-movie.component.html',
  styleUrls: ['./list-cast-movie.component.css']
})
export class ListCastMovieComponent implements OnInit {
  searchText = '';
  movieCast = new FormControl();
  castList: MovieCast[] = [];
  castChooses: any = [];
  sendCast = false;

  constructor(private movieCastService: MovieCastService,
              public dialogRef: MatDialogRef<ListCastMovieComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    for (const argument of this.data) {
      this.castChooses.push(argument);
    }
    this.getCastList().then(() => {
      this.updateCastList();
    });
  }

  updateCastList() {
    const temp: any = [];
    for (const cast of this.castList) {
      for (const castChoose of this.data) {
        if (cast.id === castChoose.castDetail.id) {
          temp.push(cast);
        }
      }
    }
    for (const tempElement of temp) {
      this.removeCast(tempElement);
    }
  }

  onNoClick() {
    console.log('\n\n Data: ', this.data);
    this.dialogRef.close(this.data);
  }

  sendListCast() {
    this.sendCast = true;
    if (!this.checkCastSelect()) {
      this.dialogRef.close(this.castChooses);
    }
  }

  checkCastSelect(): boolean {
    for (const castChoose of this.castChooses) {
      if (castChoose.character === '') {
        return true;
      }
    }
    return false;
  }


  bindingData(cast: MovieCast) {
    this.castChooses.push({
      castDetail: cast,
      character: ''
    });
    this.removeCast(cast);
    this.searchText = '';
  }

  removeCastChoosed(cast: MovieCast) {
    this.castList.push(cast);
    let index = 0;
    let castTemp: any;
    for (let i = 0; i < this.castChooses.length; i++) {
      if (this.castChooses[i].castDetail.id === cast.id) {
        index = i;
        break;
      }
    }
    for (let j = index; j < this.castChooses.length - 1; j++) {
      castTemp = this.castChooses[j + 1];
      this.castChooses[j + 1] = this.castChooses[j];
      this.castChooses[j] = castTemp;
    }
    this.castChooses.pop();
  }

  async getCastList() {
    await this.movieCastService.getCast().toPromise().then((value: any) => {
      if (value) {
        this.castList = value;
      }
    });
  }

  removeCast(cast: MovieCast) {
    let index = 0;
    let castTemp: MovieCast;
    for (let i = 0; i < this.castList.length; i++) {
      if (this.castList[i].id === cast.id) {
        index = i;
        break;
      }
    }
    for (let j = index; j < this.castList.length - 1; j++) {
      castTemp = this.castList[j + 1];
      this.castList[j + 1] = this.castList[j];
      this.castList[j] = castTemp;
    }
    this.castList.pop();
  }
}
