import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CarouselMovieComponent } from './components/carousel-movie/carousel-movie.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarouselModule } from 'primeng/carousel';
import { MovieBoxSlideComponent } from './components/movie-box-slide/movie-box-slide.component';
import { RouterModule } from '@angular/router';
import { CarouselPreCastComponent } from './components/carousel-pre-cast/carousel-pre-cast.component';
import { PreCastComponent } from './components/pre-cast/pre-cast.component';
import { ModalChangePasswordComponent } from './components/modal-change-password/modal-change-password.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ModalAdjustPersonalInfoComponent } from './components/modal-adjust-personal-info/modal-adjust-personal-info.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ModalTrailerMovieComponent } from './components/modal-trailer-movie/modal-trailer-movie.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    CarouselMovieComponent,
    MovieBoxSlideComponent,
    CarouselPreCastComponent,
    PreCastComponent,
    ModalChangePasswordComponent,
    ModalAdjustPersonalInfoComponent,
    ModalTrailerMovieComponent,
  ],
  imports: [
    CommonModule,
    CarouselModule,
    NgbModule,
    RouterModule,
    ReactiveFormsModule,
    NgSelectModule,
    ProgressSpinnerModule,
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    CarouselMovieComponent,
    MovieBoxSlideComponent,
    CarouselPreCastComponent,
    ModalChangePasswordComponent,
    FormsModule,
    ReactiveFormsModule,
    ModalTrailerMovieComponent
  ],
})
export class SharedModule {}
