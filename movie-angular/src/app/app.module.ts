import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserPortalModule } from './modules/user-portal/user-portal.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminPortalModule } from './modules/admin-portal/admin-portal.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    UserPortalModule,
    ReactiveFormsModule,
    AdminPortalModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
