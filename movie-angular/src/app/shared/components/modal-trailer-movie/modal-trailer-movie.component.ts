import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
@Component({
  selector: 'app-modal-trailer-movie',
  templateUrl: './modal-trailer-movie.component.html',
  styleUrls: ['./modal-trailer-movie.component.css']
})
export class ModalTrailerMovieComponent implements OnInit {
  @Input() linkTrailer: any;
  urlSafe: SafeResourceUrl | undefined;
  width = 720;
  height = (this.width * 9) / 16;

  constructor(public sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.linkTrailer);
  }

}
