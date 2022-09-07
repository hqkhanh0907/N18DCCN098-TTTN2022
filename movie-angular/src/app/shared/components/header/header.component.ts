import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/service/shared/account.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from '../../model/ImageModel';
import { UTIL } from '../../util/util';
import slugify from 'slugify';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  currentChoice: any;
  itemSearchBox = ["Movies", "Cast", "Director"];
  accImg = '../../../../assets/img/avatar-default.png';
  accountInf: any;
  search = new FormGroup({
    type: new FormControl(this.itemSearchBox[0]),
    content: new FormControl('')
  });
  accImage: ImageModel = new ImageModel(
    UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE,
    UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE
  );
  constructor(
    public route: Router,
    private accService: AccountService,
    private imageService: ImageService,
  ) {
    this.currentChoice = sessionStorage.getItem('item-header');
  }
  async ngOnInit() {
    await this.getAccount();
    await this.getAvatar();
  }
  showSearch() {
    if (this.search.value.content) {
      let slug = slugify(this.search.value.content, {
        replacement: '-',
        remove: undefined,
        lower: true,
        strict: true,
        locale: 'vi',
        trim: true
      });;
      this.setMovieSearch();
      this.route.navigate([`/mp/search/movie/${slug}`]).then(()=>{
        this.goLink()
        location.reload();
      });
    }
  }
  setMovieSearch() {
    sessionStorage.removeItem('movie-search');
    sessionStorage.setItem('movie-search', this.search.value.content);
  }
  public changeLink(link: any) {
    this.route.navigate(['/mp/' + link]);
  }
  async getAvatar() {
    if (this.accountInf && this.accountInf.avatar) {
      await this.imageService
        .getAccImage(this.accountInf.avatar)
        .toPromise()
        .then((data: any) => {
          this.accImage = new ImageModel(data.name, data.url);
        });
    }
  }
  async getAccount() {
    let accId = sessionStorage['idAcc'];
    if (accId !== undefined) {
      await this.accService
        .getAccount(accId)
        .toPromise()
        .then((data: any) => {
          this.accountInf = data;
        });
    }
  }
  setActive(choice: string) {
    sessionStorage.setItem('item-header', choice);
  }

  getActive(choice: string): string {
    this.currentChoice = sessionStorage.getItem('item-header');
    if (this.currentChoice == choice) return 'active';
    else return '';
  }
  goLink() {
    sessionStorage.removeItem('item-header');
  }
  goLogin() {
    sessionStorage.removeItem('item-header');
    this.route.navigate(['/mp/login']).then(() => {
      location.reload();
    });
  }
  goAccountProfile() {
    sessionStorage.removeItem('item-header');
    this.route.navigate(['/mp/account/profile']);
  }
}
