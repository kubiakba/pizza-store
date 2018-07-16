import {Component, OnInit} from '@angular/core';
import {ProductService} from "../product/product.service";
import {Pizza} from "../product/pizza";
import {PizzaTopping} from "../product/pizza-topping";
import {Kebab} from "../product/kebab";

@Component({
  selector: 'app-product-view',
  template: `
    <div style="background-color: #93B874;">
      <div *ngIf="pizzas.length > 0">
        <h3 align="center">Pizza</h3>
        <app-pizza-view [pizzas]="pizzas"></app-pizza-view>
      </div>
        <hr>
      <div *ngIf="pizzaToppings.length > 0">
        <h3 align="center">PizzaTopping</h3>
        <app-pizzaTopping-view [pizzaToppings]="pizzaToppings"></app-pizzaTopping-view>
      </div>
      <hr>
      <div *ngIf="kebabs.length > 0">
        <h3 align="center">Kebab</h3>
        <app-kebab-view [kebabs]="kebabs"></app-kebab-view>
      </div>

    </div>`,
  styleUrls: ['./product-view.css']
})
export class ProductViewComponent implements OnInit {

  pizzas: Pizza[] = [];
  pizzaToppings: PizzaTopping[] = [];
  kebabs: Kebab[] = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.getPizzas().subscribe(pizzaArray => {
      pizzaArray.forEach(pizza => this.pizzas.push(new Pizza(pizza)))
    });

    this.productService.getPizzaToppings().subscribe(pizzaToppingArray => {
      pizzaToppingArray.forEach(pizzaTopping => this.pizzaToppings.push(new PizzaTopping(pizzaTopping)))
    });

    this.productService.getKebabs().subscribe(kebabArray => {
      kebabArray.forEach(kebab => this.kebabs.push(new Kebab(kebab)))
    });
  }

}
