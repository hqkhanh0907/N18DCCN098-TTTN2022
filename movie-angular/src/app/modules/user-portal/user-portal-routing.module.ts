import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountProfileComponent } from './components/account-profile/account-profile.component';
import { AccountComponent } from './components/account/account.component';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { MoviesComponent } from './components/movies/movies.component';
import { PaymentPageComponent } from './components/payment-page/payment-page.component';
import { SignupComponent } from './components/signup/signup.component';
import { WatchingComponent } from './components/watching/watching.component';
import { UserPortalComponent } from './user-portal.component';

const routes: Routes = [
  {
    path: 'mp',
    component: UserPortalComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'movies',
        component: MoviesComponent,
      },
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'signup',
        component: SignupComponent,
      },
      {
        path: 'movie/:slug',
        component: MovieDetailsComponent,
      },
      {
        path: 'account',
        component: AccountComponent,
        children: [
          {
            path: 'profile',
            component: AccountProfileComponent,
          },
          {
            path: 'watching',
            component: WatchingComponent,
          },
          {
            path: 'favourite',
            component: FavouriteComponent,
          },
        ],
      },
      {
        path: 'service',
        children: [
          {
            path: 'payment/:slug',
            component: PaymentPageComponent,
          },
        ],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserPortalRoutingModule {}
