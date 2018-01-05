import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ProductService {

  url ="/products/";
  pizzaUrl ="pizzas";
  kebabUrl ="kebabs";
  pizzaToppingUrl ="pizzaToppings";

  constructor(private http: HttpClient) { }

  public getPizzas(): Observable<any[]> {
    return this.http.get<any[]>(this.url + this.pizzaUrl);
  }

  public getKebabs(): Observable<any[]> {
    return this.http.get<any[]>(this.url + this.kebabUrl);
  }

  public getPizzaToppings(): Observable<any[]> {
    return this.http.get<any[]>(this.url + this.pizzaToppingUrl);
  }

}
