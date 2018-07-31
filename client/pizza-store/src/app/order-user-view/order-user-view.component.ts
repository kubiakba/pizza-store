import {Component, Input} from '@angular/core';
import {NewOrder} from "../order/newOrder";

@Component({
  selector: 'app-order-user-view',
  template: `
    <div style="background-color: #93B874">
      <app-login-user-view></app-login-user-view>
      <app-register-user-view></app-register-user-view>
      <br>
      <br>
      <app-add-user-view [order]="order"></app-add-user-view>
    </div>`

})
export class OrderUserViewComponent {

  @Input() order: NewOrder;

  constructor() {
  }
}
