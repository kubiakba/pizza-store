import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  pizza_url = "/products/pizzas";
  pizzaTopping_url = "/products/pizzaToppings";
  kebab_url = "/products/kebabs";

  constructor(private http: HttpClient) { }

  public getPizzas(): Observable<any[]> {
    return this.http.get<any[]>(this.pizza_url);
  }

  public getPizzaToppings(): Observable<any[]> {
    return this.http.get<any[]>(this.pizzaTopping_url);
  }

  public getKebabs(): Observable<any[]> {
    return this.http.get<any[]>(this.kebab_url);
  }
}
