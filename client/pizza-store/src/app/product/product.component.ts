import { Component, OnInit } from '@angular/core';
import {ProductService} from "../product-service/product.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  pizzas :any[];
  kebabs :any[];
  pizzaToppings :any[];

  constructor(private productService : ProductService) { }

  ngOnInit() {
    this.productService.getPizzas()
      .subscribe(data =>{
        this.pizzas = data;
    })

    this.productService.getKebabs()
      .subscribe(data =>{
        this.kebabs = data;
      })

    this.productService.getPizzaToppings()
      .subscribe(data =>{
        this.pizzaToppings = data;
      })
  }

  addToBasket(price) {
    console.log(price);
  }

}
