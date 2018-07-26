import {Component} from '@angular/core';

@Component({
  selector: 'app-order-user-view',
  template: `
    <app-login-user-view></app-login-user-view>
    <app-register-user-view></app-register-user-view>`
})
export class OrderUserViewComponent {

  constructor() {
  }
}
