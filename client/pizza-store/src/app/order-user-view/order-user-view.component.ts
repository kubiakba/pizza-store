import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-order-user-view',
  template: `
    <div style="background-color: #93B874">
      <br>
      <br>
      <app-add-user-view [productIds]="productIds"></app-add-user-view>
    </div>`

})
export class OrderUserViewComponent {

  @Input() productIds: String[];

  constructor() {
  }
}
