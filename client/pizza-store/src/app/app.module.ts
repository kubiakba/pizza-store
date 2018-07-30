import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {UserViewComponent} from './user-view/user-view.component';
import {RouterModule} from "@angular/router";
import {NavbarComponent} from "./navbar/navbar.component";
import {ProductViewComponent} from './product-view/product-view.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PizzaViewComponent} from "./product-view/pizza-view.component";
import {KebabViewComponent} from "./product-view/kebab-view.component";
import {PizzaToppingViewComponent} from "./product-view/pizzaTopping-view.component";
import {UserTableComponent} from "./user-view/user-table.component";
import {ContactComponent} from "./contact/contact.component";
import {RegisterUserViewComponent} from './register-user-view/register-user-view.component';
import {LoginUserViewComponent} from './login-user-view/login-user-view.component';
import {OrderUserViewComponent} from './order-user-view/order-user-view.component';

@NgModule({
  declarations: [
    AppComponent,
    UserViewComponent,
    NavbarComponent,
    ProductViewComponent,
    PizzaViewComponent,
    KebabViewComponent,
    PizzaToppingViewComponent,
    UserTableComponent,
    ContactComponent,
    RegisterUserViewComponent,
    LoginUserViewComponent,
    OrderUserViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([{
      path: 'admin/view-users',
      component: UserViewComponent
    },{
      path: 'view-products',
      component: ProductViewComponent
    },{
      path: 'view-contact',
      component: ContactComponent
    }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
