import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {UserViewComponent} from './user-view/user-view.component';
import {RouterModule} from "@angular/router";
import {NavbarComponent} from "./navbar/navbar.component";
import { ProductViewComponent } from './product-view/product-view.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    UserViewComponent,
    NavbarComponent,
    ProductViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([{
      path: 'users-view',
      component: UserViewComponent
    },{
      path: 'products-view',
      component: ProductViewComponent
    }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
