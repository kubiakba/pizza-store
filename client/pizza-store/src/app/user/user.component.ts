import { Component, OnInit } from '@angular/core';
import {UserService} from "../user-service/user.service";
import {User} from "../user-service/user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user : User;

  constructor(private userService : UserService) { }

  onKey(event) {
    this.userService.getUser(event.target.value).subscribe(data => {
      this.user = data;
    })
  }

  ngOnInit() {

  }

}
