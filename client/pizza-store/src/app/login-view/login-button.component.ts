import {Component} from '@angular/core';

@Component({
  selector: 'app-login-user-view',
  template: `
    <div class="float-sm-right">
      <a routerLink="/login">
        <button class="btn btn-primary"> Login</button>
      </a>
    </div>`
})
export class LoginButtonComponent {
}
