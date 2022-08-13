import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DashboardComponent} from './admin/component/dashboard/dashboard.component';
import {AppAdminComponent} from './admin/component/app-admin/app-admin.component';
import {LoginadminComponent} from './admin/component/loginadmin/loginadmin.component';
import {PageNotFoundComponent} from './admin/util/page-not-found/page-not-found.component';
import {MovieComponent} from './admin/component/movie/movie.component';
import {AddMovieComponent} from './admin/component/add-movie/add-movie.component';
import {ProfilePageComponent} from './admin/component/profile-page/profile-page.component';
import {UserComponent} from './admin/component/user/user.component';
import { AuthGaurdService } from 'src/app/service/admin/auth-gaurd-service-service.service';

const routes: Routes = [
  {
    path: 'admin',
    children: [
      {path: 'login', component: LoginadminComponent},
      {
        path: 'pages', component: AppAdminComponent,
        children: [
          {path: 'profile', component: ProfilePageComponent},
          {path: 'movie', component: MovieComponent},
          {path: 'dashboard', component: DashboardComponent},
          {path: 'user', component: UserComponent}
        ],
        canActivate: [AuthGaurdService]
      },
      {path: '**', component: PageNotFoundComponent}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdminPortalRoutingModule { }
