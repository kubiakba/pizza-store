import {Component} from '@angular/core';
import {UserService} from "../user/user.service";
import {NewUserDTO} from "../user/newUserDTO";
import {AutoRefreshingComponent} from "../utils/auto-refreshing-component";

@Component({
  selector: 'app-register-view',
  template: `
    <div style="background-color: #c3e6cb">
      <div *ngIf="!userHasBeenCreatedSuccessfully">
        <div class="register">
          Register
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
          <button (click)="registerUser()" class="btn btn-primary">Submit</button>
          <div *ngIf="errorMessage.size > 0">
            <div *ngFor="let message of errorMessage">
              {{message}}
            </div>
          </div>
        </form>
      </div>
      <div *ngIf="userHasBeenCreatedSuccessfully">
        <app-registration-confirmed-view></app-registration-confirmed-view>
      </div>
    </div>`
})
export class RegisterViewComponent extends AutoRefreshingComponent {

  email: String = '';
  password: String = '';
  errorMessage: Set<String> = new Set<String>();
  userHasBeenCreatedSuccessfully = false;

  constructor(private userService: UserService) {
    super()
  }

  registerUser() {
    let user = new NewUserDTO(this.email, this.password);
    console.log(this.email, this.password);
    this.userService.register(user).subscribe(user => {
      this.userHasBeenCreatedSuccessfully = true
    }, error => this.errorMessage.add(error.error.errorCode));
  }

  initialize(): void {
    this.email = '';
    this.password = '';
    this.errorMessage = new Set<String>();
    this.userHasBeenCreatedSuccessfully = false;
  }
}
