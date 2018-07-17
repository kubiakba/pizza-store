import {Component, Input, OnInit} from "@angular/core";
import {Kebab} from "../product/kebab";

@Component({
  selector: 'app-kebab-view',
  template: `
        <table class="spacing-table" align="center">
          <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let kebab of kebabs">
            <td>{{kebab.name}}</td>
            <td>{{kebab.description}}</td>
            <td>{{kebab.price}}</td>
          </tr>
          </tbody>
        </table>
    `,
  styleUrls: ['./product-view.css']
})
export class KebabViewComponent{

  @Input() kebabs:Kebab[];
}
