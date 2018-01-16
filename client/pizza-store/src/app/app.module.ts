import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {NavbarComponent} from "./navbar/navbar.component";
import {UserComponent} from "./user/user.component";
import {UserService} from "./user-service/user.service";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { OrderComponent } from './order/order.component';
import { ProductComponent } from './product/product.component';
import {OrderService} from "./order-service/order.service";
import {ProductService} from "./product-service/product.service";
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientXsrfModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UserComponent,
    OrderComponent,
    ProductComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    HttpClientXsrfModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path:'offer', component: ProductComponent },
      { path:'register', component: RegistrationComponent }
    ])
  ],
  providers: [UserService, OrderService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
