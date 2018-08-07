import {Component} from '@angular/core';
import {UserService} from "../user/user.service";
import {NewUserDTO} from "../user/newUserDTO";

@Component({
  selector: 'app-register-view',
  template: `
    <div style="background-color: #c3e6cb">
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
      </form>
    </div>`
})
export class RegisterViewComponent {

  email: String = '';
  password: String = '';

  constructor(private userService: UserService) {
  }

  registerUser() {
    let user = new NewUserDTO(this.email, this.password);
    console.log(this.email, this.password);
    this.userService.register(user).subscribe();
  }

}
