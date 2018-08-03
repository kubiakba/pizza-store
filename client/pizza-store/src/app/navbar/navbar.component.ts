import {Component} from '@angular/core';

@Component({
  selector: 'app-navbar',
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #d43f3a">
      <div class="navbar-collapse collapse w-100 dual-collapse2 order-1 order-md-0">
        <ul class="navbar-nav ml-auto text-center">
          <li class="nav-item active">
            <a style="font-size:30px" class="nav-link" routerLink="view-products">Menu</a>
          </li>
        </ul>
      </div>
      <div class="navbar-collapse collapse w-100 dual-collapse2 order-2 order-md-2">
        <ul class="navbar-nav mr-auto text-center">
          <li class="nav-item active">
            <a style="font-size: 30px" class="nav-link" routerLink="view-contact">Contact</a>
          </li>
        </ul>
        <app-login-user-view></app-login-user-view>
        <app-register-user-view></app-register-user-view>
      </div>
    </nav>
  `
})
export class NavbarComponent {
}
