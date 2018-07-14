import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = "/users/";

  constructor(private http: HttpClient) {
  }

  public getUser(id: String): Observable<User> {
    return this.http.get<User>(this.url + id);
  }

  public createUser(user: User): Observable<User> {
    return this.http.post<User>(this.url, user);
  }
}
