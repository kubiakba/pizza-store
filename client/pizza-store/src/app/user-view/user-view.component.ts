import {Component, OnInit} from '@angular/core';
import {UserService} from "../user/user.service";
import {User} from "../user/user";

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  user: User;
  sth = "Something something bad";

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUser("1232@gmail.com")
      .subscribe((result: User) => this.user = result);
  }
}
