import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  pizza_url = "/products/pizzas";

  constructor(private http: HttpClient) { }

  public getPizzas(): Observable<any[]> {
    return this.http.get<any[]>(this.pizza_url);
  }
}
