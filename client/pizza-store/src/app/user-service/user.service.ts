import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable()
export class UserService {

  url = "/users/";
  constructor(private http: HttpClient) { }

  public getUser(id :String): Observable<User> {
    return this.http.get<User>(this.url + id);
  }

}
