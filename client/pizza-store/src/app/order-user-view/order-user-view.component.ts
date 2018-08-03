import {Component, Input} from '@angular/core';
import {NewOrder} from "../order/newOrder";

@Component({
  selector: 'app-order-user-view',
  template: `
    <div style="background-color: #93B874">
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
