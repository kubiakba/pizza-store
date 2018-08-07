import {Component} from '@angular/core';
import {UserService} from "../user/user.service";
import {JwtAuthenticationRequest} from "../authentication/jwt-authentication-request";
import {JwtConsts} from "../authentication/jwt-consts";

@Component({
  selector: 'app-login-view',
  template: `
    <div style="background-color: #c3e6cb">
      <div *ngIf="!userHasBeenLoggedSuccessfully">
        <div class="register">
          Login
        </div>
        <br>
        <form>
          <div class="form-group">
            <label for="emailId">Email address</label>
            <input type="email" [(ngModel)]="email" name="email" class="form-control" id="emailId" placeholder="Enter email">
          </div>
          <div class="form-group">
            <label for="passId">Password</label>
            <input type="password" [(ngModel)]="password" name="password" class="form-control" id="passId" placeholder="Password">
          </div>
          <div *ngIf="errorMessage.size > 0">
            <div *ngFor="let message of errorMessage">
              {{message}}
            </div>
          </div>
          <button (click)="login()" type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
      <div *ngIf="userHasBeenLoggedSuccessfully">
        <app-login-confirmed-view></app-login-confirmed-view>
      </div>
    </div>`
})
export class LoginViewComponent {

  email: String = '';
  password: String = '';
  errorMessage: Set<String> = new Set<String>();
  userHasBeenLoggedSuccessfully = false;

  constructor(private userService: UserService) {
  }

  login() {
    this.userService.login(new JwtAuthenticationRequest(this.email, this.password)).subscribe(token => {
        localStorage.setItem(JwtConsts.localStorageTokenKey, token.token);
        this.userHasBeenLoggedSuccessfully = true;
      }
      , error => this.errorMessage.add(error.error.errorCode));
  }

}
