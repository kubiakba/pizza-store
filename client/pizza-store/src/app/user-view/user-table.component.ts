import {Component, Input} from "@angular/core";
import {User} from "../user/user";

@Component({
  selector: 'app-user-table',
  template: `
    <div style="background-color: #93B874;">
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
  `
})
export class UserTableComponent{
  @Input() user: User;
}
