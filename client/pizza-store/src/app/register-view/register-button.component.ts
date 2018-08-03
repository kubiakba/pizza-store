import {Component} from '@angular/core';

@Component({
  selector: 'app-register-user-view',
  template: `
    <div class="float-sm-right">
      <button type="button" routerLink="/register" class="btn btn-primary">Register</button>
    </div>
  `
})
export class RegisterButtonComponent {}
