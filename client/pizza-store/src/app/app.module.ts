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


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UserComponent,
    OrderComponent,
    ProductComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot([
      { path:'users', component: UserComponent },
      { path:'orders', component: OrderComponent },
      { path:'products', component: ProductComponent }
    ])
  ],
  providers: [UserService, OrderService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
