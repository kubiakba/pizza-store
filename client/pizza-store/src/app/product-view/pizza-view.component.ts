import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
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
            <th></th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let pizza of pizzas">
            <td>{{pizza.size}}</td>
            <td>{{pizza.dough}}</td>
            <td>{{pizza.price}}</td>
            <td><button class="btn light" (click)="addPizzaToOrder(pizza.id)">add</button></td>
          </tr>
          </tbody>
        </table>
    `,
  styleUrls: ['./product-view.css']
})
export class PizzaViewComponent{

  @Input() pizzas:Pizza[];
  @Output() order = new EventEmitter<String>();

  addPizzaToOrder(id:String){
    this.order.emit(id);
  }
}
