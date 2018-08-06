import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {DeliveryInfoDTO} from "./deliveryInfoDTO";
import {NewOrderDTO} from "./newOrderDTO";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  order_url = "/orders";
  empty_body: any = {};

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }
  public startOrder(order: NewOrderDTO): Observable<any> {
    return this.http.post<any>(this.order_url, order, this.httpOptions);
  }

  public addProductToOrder(orderId: String, productId: String): Observable<any> {
    return this.http.put<any>(this.order_url + `/${orderId}/${productId}`, this.empty_body, this.httpOptions);
  }

  public setOrderToRealization(orderId: String): Observable<any> {
    return this.http.put<any>(this.order_url + `/${orderId}/` + "to-realization", this.empty_body, this.httpOptions);
  }
}
