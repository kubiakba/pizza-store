import {BrowserModule} from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';
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
import {RegisterButtonComponent} from './register-view/register-button.component';
import {LoginButtonComponent} from './login-view/login-button.component';
import {OrderUserViewComponent} from './order-user-view/order-user-view.component';
import {AddUserViewComponent} from './add-user-view/add-user-view.component';
import {OrderConfirmationViewComponent} from './order-confirmation-view/order-confirmation-view.component';
import {setAppInjector} from "./utils/injector";
import { RegisterViewComponent } from './register-view/register-view.component';
import { LoginViewComponent } from './login-view/login-view.component';

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
    RegisterButtonComponent,
    LoginButtonComponent,
    OrderUserViewComponent,
    AddUserViewComponent,
    OrderConfirmationViewComponent,
    RegisterViewComponent,
    LoginViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([{
      path: 'adm-view-users',
      component: UserViewComponent
    }, {
      path: 'view-products',
      component: ProductViewComponent
    }, {
      path: 'view-contact',
      component: ContactComponent
    }, {
      path: 'register',
      component: RegisterViewComponent
    }, {
      path: 'login',
      component: LoginViewComponent
    }
    ], {onSameUrlNavigation: 'reload'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private injector: Injector) {
    setAppInjector(injector);
  }
}
