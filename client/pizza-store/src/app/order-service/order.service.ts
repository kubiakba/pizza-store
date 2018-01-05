import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class OrderService {

  url ="/orders/"
  constructor(private http: HttpClient) { }

  public getOrder(id :String): Observable<any> {
    return this.http.get<any>(this.url + id);
  }

}
