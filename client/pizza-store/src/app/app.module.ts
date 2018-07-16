import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {UserViewComponent} from './user-view/user-view.component';
import {RouterModule} from "@angular/router";
import {NavbarComponent} from "./navbar/navbar.component";

@NgModule({
  declarations: [
    AppComponent,
    UserViewComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([{
      path: 'user',
      component: UserViewComponent
    },{
      path: 'products',
      component: UserViewComponent
    }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
