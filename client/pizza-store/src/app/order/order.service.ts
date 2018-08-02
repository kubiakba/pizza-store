import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";

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

  public startOrder(): Observable<any> {
    return this.http.post<any>(this.order_url, this.empty_body, this.httpOptions);
  }

  public startOrderWithEmail(email:String): Observable<any> {
    return this.http.post<any>(this.order_url, JSON.stringify({email:email}), this.httpOptions);
  }

  public addProductToOrder(orderId: String, productId: String): Observable<any> {
    return this.http.put<any>(this.order_url + `/${orderId}/${productId}`, this.empty_body, this.httpOptions);
  }
}
