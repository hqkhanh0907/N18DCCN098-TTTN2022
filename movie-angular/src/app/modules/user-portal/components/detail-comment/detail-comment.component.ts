import { Component, Input, OnInit } from '@angular/core';
import { AccountService } from 'src/app/service/shared/account.service';
import { ImageService } from 'src/app/service/shared/upload-image.service';
import { ImageModel } from 'src/app/shared/model/ImageModel';
import { UTIL } from 'src/app/shared/util/util';
@Component({
  selector: 'app-detail-comment',
  templateUrl: './detail-comment.component.html',
  styleUrls: ['./detail-comment.component.css'],
})
export class DetailCommentComponent implements OnInit {
  @Input() evaluate: any;
  avatar: ImageModel = new ImageModel(
    UTIL.DEFAULT_ACCOUNT_IMAGE_NAME_MALE,
    UTIL.DEFAULT_ACCOUNT_IMAGE_URL_MALE
  );
  accInf: any;
  constructor(private accountService: AccountService,
    private imageService: ImageService) { }

  async ngOnInit() {
    if (this.evaluate) {
      await this.getAccInfo();
      if (this.accInf) {
        await this.getAvatar();
      }
    }
  }
  async getAccInfo() {
    await this.accountService.getAccount(this.evaluate.id.userId).toPromise().then((data: any) => {
      this.accInf = data;
    });
  }
  getFullName(): string {
    return this.accInf ? `${this.accInf.firstname} ${this.accInf.lastname}` : '';
  }
  async getAvatar() {
    await this.imageService
      .getAccImage(this.accInf.avatar)
      .toPromise()
      .then((data: any) => {
        if (data) {
          this.avatar = new ImageModel(data.name, data.url);
        }
      });
  }
}
