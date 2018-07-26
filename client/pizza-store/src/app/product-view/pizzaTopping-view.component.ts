import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {PizzaTopping} from "../product/pizzaTopping";

@Component({
  selector: 'app-pizzaTopping-view',
  template: `
        <table class="spacing-table" align="center">
          <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let pizzaTopping of pizzaToppings">
            <td>{{pizzaTopping.name}}</td>
            <td>{{pizzaTopping.price}}</td>
            <td><button class="btn light" (click)="addPizzaToppingToOrder(pizzaTopping.id)">add</button></td>
          </tr>
          </tbody>
        </table>
    `,
  styleUrls: ['./product-view.css']
})
export class PizzaToppingViewComponent{

  @Input() pizzaToppings:PizzaTopping[];
  @Output() order = new EventEmitter<String>();

  addPizzaToppingToOrder(id:String){
    this.order.emit(id);
  }
}
