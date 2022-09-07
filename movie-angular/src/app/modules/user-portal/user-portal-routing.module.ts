import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGaurdUserService } from 'src/app/service/customer/auth-gaurd-user.service';
import { AccountProfileComponent } from './components/account-profile/account-profile.component';
import { AccountComponent } from './components/account/account.component';
import { FavoriteComponent } from './components/favourite/favourite.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { MoviesComponent } from './components/movies/movies.component';
import { PaymentPageComponent } from './components/payment-page/payment-page.component';
import { SearchMovieComponent } from './components/search-movie/search-movie.component';
import { SignupComponent } from './components/signup/signup.component';
import { TransactionHistoryComponent } from './components/transaction-history/transaction-history.component';
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
        path: 'search/movie/:searchmovie',
        component: SearchMovieComponent,
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
            path: 'favorite',
            component: FavoriteComponent,
          },
          {
            path: 'transaction-history',
            component: TransactionHistoryComponent,
          },
        ],
        canActivate: [AuthGaurdUserService]
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
