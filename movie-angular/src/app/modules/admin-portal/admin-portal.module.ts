import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HeaderComponent } from './admin/component/header/header.component';
import { SidenavComponent } from './admin/component/sidenav/sidenav.component';
import { MovieComponent } from './admin/component/movie/movie.component';
import { DashboardComponent } from './admin/component/dashboard/dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { LoginadminComponent } from './admin/component/loginadmin/loginadmin.component';
import { AppAdminComponent } from './admin/component/app-admin/app-admin.component';
import { PageNotFoundComponent } from './admin/util/page-not-found/page-not-found.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MovieChildComponent } from './admin/component/movie-child/movie-child.component';
import { AddMovieComponent } from './admin/component/add-movie/add-movie.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { PaginatorComponent } from './admin/util/paginator/paginator.component';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatMomentDateModule, MomentDateModule } from '@angular/material-moment-adapter';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { NgbAlertModule, NgbModule, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule } from '@angular/material/chips';
import { AddGenreMovieComponent } from './admin/component/add-genre-movie/add-genre-movie.component';
import { AddCastMovieComponent } from './admin/component/add-cast-movie/add-cast-movie.component';
import { AddDirectorMovieComponent } from './admin/component/add-director-movie/add-director-movie.component';
import { MatCardModule } from '@angular/material/card';
import { ProfilePageComponent } from './admin/component/profile-page/profile-page.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { UploadImageComponent } from './admin/util/upload-image/upload-image.component';
import { MatRadioModule } from '@angular/material/radio';
import { ListCastMovieComponent } from './admin/component/list-cast-movie/list-cast-movie.component';
import { AccountFilterPipe, CastFilterPipe, GenreFilterPipe, PromoFilterPipe } from './pipe/filter.pipe';
import { HighlightDirective } from './pipe/highlight.pipe';
import { CastComponent } from './admin/component/cast/cast.component';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { NgxPaginationModule } from 'ngx-pagination';
import { FilterDirectorPipe, FilterMoviePipe } from './pipe/filterMovie.pipe';
import { DetailMovieComponent } from './admin/component/detail-movie/detail-movie.component';
import { UserComponent } from './admin/component/user/user.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatSortModule } from '@angular/material/sort';
import { AddUserComponent } from './admin/component/add-user/add-user.component';
import { EditUserComponent } from './admin/component/edit-user/edit-user.component';
import { SafePipe } from './pipe/safe.pipe';
import { AdminPortalComponent } from './admin-portal.component';
import { AdminPortalRoutingModule } from './admin-portal-routing.module';
import { NgSelectModule } from '@ng-select/ng-select';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MultiSelectModule } from 'primeng/multiselect';
import { GenreComponent } from './admin/component/genre/genre.component';
import { EditGenreComponent } from './admin/component/edit-genre/edit-genre.component';
import { DirectorComponent } from './admin/component/director/director.component';
import { PromotionComponent } from './admin/component/promotion/promotion.component';
import { CastPageComponent } from './admin/component/cast-page/cast-page.component';
import { NgbdTooltipCustomclassComponent } from './admin/util/ngbd-tooltip-customclass/ngbd-tooltip-customclass.component';
import { AddPromotionComponent } from './admin/component/add-promotion/add-promotion.component';
import { EditPromotionComponent } from './admin/component/edit-promotion/edit-promotion.component';
import { PageCastComponent } from './admin/component/page-cast/page-cast.component';
import { CastChildComponent } from './admin/component/cast-child/cast-child.component';
import { EditCastComponent } from './admin/component/edit-cast/edit-cast.component';
import { DirectorChildComponent } from './admin/component/director-child/director-child.component';
import { EditDirectorComponent } from './admin/component/edit-director/edit-director.component';


registerLocaleData(en);

@NgModule({
  declarations: [
    AdminPortalComponent,
    HeaderComponent,
    SidenavComponent,
    MovieComponent,
    DashboardComponent,
    LoginadminComponent,
    AppAdminComponent,
    PageNotFoundComponent,
    MovieChildComponent,
    AddMovieComponent,
    PaginatorComponent,
    AddGenreMovieComponent,
    AddCastMovieComponent,
    AddDirectorMovieComponent,
    ProfilePageComponent,
    UploadImageComponent,
    ListCastMovieComponent,
    CastFilterPipe,
    HighlightDirective,
    CastComponent,
    FilterMoviePipe,
    FilterDirectorPipe,
    CastFilterPipe,
    AccountFilterPipe,
    GenreFilterPipe,
    PromoFilterPipe,
    DetailMovieComponent,
    UserComponent,
    AddUserComponent,
    EditUserComponent,
    SafePipe,
    GenreComponent,
    EditGenreComponent,
    DirectorComponent,
    PromotionComponent,
    CastPageComponent,
    NgbdTooltipCustomclassComponent,
    AddPromotionComponent,
    EditPromotionComponent,
    PageCastComponent,
    CastChildComponent,
    EditCastComponent,
    DirectorChildComponent,
    EditDirectorComponent
  ],
  entryComponents: [
    AddMovieComponent,
    AddGenreMovieComponent,
    AddCastMovieComponent,
    AddDirectorMovieComponent,
    ListCastMovieComponent,
    DetailMovieComponent,
    AddUserComponent,
    EditUserComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AdminPortalRoutingModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxMaterialTimepickerModule,
    MatPaginatorModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatMomentDateModule,
    NgMultiSelectDropDownModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MomentDateModule,
    FormsModule,
    NgbModule,
    MatSelectModule,
    MatChipsModule,
    MatCardModule,
    NgbPaginationModule,
    NgbAlertModule,
    MatRadioModule,
    NgxPaginationModule,
    FontAwesomeModule,
    MatSortModule,
    NgSelectModule,
    ProgressSpinnerModule,
    MultiSelectModule,
  ],
  providers: [
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }],
  bootstrap: [AdminPortalComponent],
  exports: [AddMovieComponent],
})
export class AdminPortalModule { }
