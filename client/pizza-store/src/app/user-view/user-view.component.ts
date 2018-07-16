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
        <app-user-table [user]="user"></app-user-table>
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
