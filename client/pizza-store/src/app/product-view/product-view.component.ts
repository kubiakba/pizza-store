import {Component, OnInit} from '@angular/core';
import {ProductService} from "../product/product.service";
import {Pizza} from "../product/pizza";

@Component({
  selector: 'app-product-view',
  template: `<ul>
    <li *ngFor="let pizza of pizzas">
      Pizza
      {{pizza.price}}
      {{pizza.size}}
      {{pizza.dough}}
    </li>
  </ul>`
})
export class ProductViewComponent implements OnInit {

  pizzas: Pizza[] = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.getPizzas().subscribe(pizzaArray => {
      pizzaArray.forEach(pizza => this.pizzas.push(new Pizza(pizza)))
    })
  }

}
