import {Component} from '@angular/core';
import {UserService} from "../user/user.service";
import {User} from "../user/user";

@Component({
  selector: 'app-user-view',
  template: `
    <div style="background-color: #93B874;">
      <form class="form form-horizontal">
        <div class="form-group">
          <div class="col-md-3">
            <input (keydown.enter)="search($event)" class="form-control" placeholder="Provide email address">
          </div>
        </div>
      </form>
      <div *ngIf="user">
        <table class="table">
          <thead>
          <tr>
            <th>Email</th>
            <th>Name</th>
            <th>Surname</th>
            <th>City</th>
            <th>Street</th>
            <th>Street number</th>
            <th>Post code</th>
            <th>Telephone</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>{{user.email}}</td>
            <td>{{user.name}}</td>
            <td>{{user.surname}}</td>
            <td>{{user.address.city}}</td>
            <td>{{user.address.street}}</td>
            <td>{{user.address.streetNumber}}</td>
            <td>{{user.address.postCode}}</td>
            <td>{{user.telephone.number}}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  `
})
export class UserViewComponent {

  user: User;

  constructor(private userService: UserService) {
  }

  search(event: any) {
    this.userService.getUser(event.target.value)
      .subscribe((result: any) => this.user = new User(result));
  }
}
