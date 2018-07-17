import {Component, Input, OnInit} from "@angular/core";
import {Pizza} from "../product/pizza";

@Component({
  selector: 'app-pizza-view',
  template: `
        <table class="spacing-table" align="center">
          <thead >
          <tr>
            <th>Size</th>
            <th>Dough</th>
            <th>Price</th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let pizza of pizzas">
            <td>{{pizza.size}}</td>
            <td>{{pizza.dough}}</td>
            <td>{{pizza.price}}</td>
          </tr>
          </tbody>
        </table>
    `,
  styleUrls: ['./product-view.css']
})
export class PizzaViewComponent{

  @Input() pizzas:Pizza[];
}
