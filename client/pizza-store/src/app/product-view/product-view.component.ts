import {Component} from '@angular/core';
import {ProductService} from "../product/product.service";
import {Pizza} from "../product/pizza";
import {PizzaTopping} from "../product/pizzaTopping";
import {Kebab} from "../product/kebab";
import {NewOrder} from "../order/newOrder";
import {AutoRefreshingComponent} from "../utils/auto-refreshing-component";

@Component({
  selector: 'app-product-view',
  template: `
    <div class="view" *ngIf="!orderView">
      <div *ngIf="pizzas.length > 0">
        <h3 align="center">Pizza</h3>
        <app-pizza-view [pizzas]="pizzas" (order)="addPizzaToOrder($event)"></app-pizza-view>
      </div>
      <hr>
      <div *ngIf="pizzaToppings.length > 0">
        <h3 align="center">PizzaTopping</h3>
        <app-pizzaTopping-view [pizzaToppings]="pizzaToppings" (order)="addPizzaToppingToOrder($event)"></app-pizzaTopping-view>
      </div>
      <hr>
      <div *ngIf="kebabs.length > 0">
        <h3 align="center">Kebab</h3>
        <app-kebab-view [kebabs]="kebabs" (order)="addKebabToOrder($event)"></app-kebab-view>
      </div>
      <div *ngIf="order.productsId.length >0" class="form-row text-center">
        <div class="col-12">
          <button type="submit" (click)="createOrder()" class="btn btn-primary">Order</button>
        </div>
      </div>
    </div>
    <div *ngIf="orderView">
      <app-order-user-view [order]="order"></app-order-user-view>
    </div>
  `,
  styleUrls: ['./product-view.css']
})
export class ProductViewComponent extends AutoRefreshingComponent{

  pizzas: Pizza[] = [];
  pizzaToppings: PizzaTopping[] = [];
  kebabs: Kebab[] = [];
  order: NewOrder = new NewOrder();
  orderView = false;

  constructor(private productService: ProductService) {
    super()
  }

  initialize() {
    this.pizzas = [];
    this.pizzaToppings = [];
    this.kebabs = [];
    this.order = new NewOrder();
    this.orderView = false;

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

  addPizzaToOrder(id: String) {
    this.order.productsId.push(id)
  }

  addPizzaToppingToOrder(id: String) {
    this.order.productsId.push(id)
  }

  addKebabToOrder(id: String) {
    this.order.productsId.push(id)
  }

  createOrder() {
    this.orderView = true;
  }

}
