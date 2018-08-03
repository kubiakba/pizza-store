import { Component} from '@angular/core';

@Component({
  selector: 'app-login-view',
  template:` <div style="background-color: #c3e6cb">
    <div class="register">
      Login
    </div>
    <br>
    <form>
      <div class="form-group">
        <label for="emailId">Email address</label>
        <input type="email" class="form-control" id="emailId" placeholder="Enter email">
      </div>
      <div class="form-group">
        <label for="passId">Password</label>
        <input type="password" class="form-control" id="passId" placeholder="Password">
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>`
})
export class LoginViewComponent{
}
