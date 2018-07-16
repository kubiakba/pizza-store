import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {UserViewComponent} from './user-view/user-view.component';
import {RouterModule} from "@angular/router";
import {NavbarComponent} from "./navbar/navbar.component";
import { ProductViewComponent } from './product-view/product-view.component';
import {FormsModule} from "@angular/forms";
import {PizzaViewComponent} from "./product-view/pizza-view.component";
import {ReactiveFormsModule} from "@angular/forms";
import {KebabViewComponent} from "./product-view/kebab-view.component";
import {PizzaToppingViewComponent} from "./product-view/pizzaTopping-view.component";

@NgModule({
  declarations: [
    AppComponent,
    UserViewComponent,
    NavbarComponent,
    ProductViewComponent,
    PizzaViewComponent,
    KebabViewComponent,
    PizzaToppingViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([{
      path: 'view-users',
      component: UserViewComponent
    },{
      path: 'view-products',
      component: ProductViewComponent
    }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
