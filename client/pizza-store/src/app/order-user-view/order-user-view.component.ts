import {Component} from '@angular/core';

@Component({
  selector: 'app-order-user-view',
  template: `
    <div style="background-color: #93B874">
      <app-login-user-view></app-login-user-view>
      <app-register-user-view></app-register-user-view>
      <br>
      <br>
      <app-add-user-view></app-add-user-view>
    </div>`

})
export class OrderUserViewComponent {

  constructor() {
  }
}
