import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {User} from "./user";
import {NewUserDTO} from "./newUserDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = "/users";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  public getUser(id: String): Observable<any> {
    return this.http.get(this.url + "/" + id);
  }

  public register(user:NewUserDTO): Observable<any> {
    return this.http.post(this.url, user);
  }
}
