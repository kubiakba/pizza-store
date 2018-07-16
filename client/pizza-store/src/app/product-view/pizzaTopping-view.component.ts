import {Component, Input, OnInit} from "@angular/core";
import {PizzaTopping} from "../product/pizza-topping";

@Component({
  selector: 'app-pizzaTopping-view',
  template: `
        <table class="table">
          <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let pizzaTopping of pizzaToppings">
            <td>{{pizzaTopping.name}}</td>
            <td>{{pizzaTopping.price}}</td>
          </tr>
          </tbody>
        </table>
    `,
  styleUrls: ['./product-view.css']
})
export class PizzaToppingViewComponent{

  @Input() pizzaToppings:PizzaTopping[];
}
