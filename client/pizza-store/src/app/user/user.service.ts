import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {NewUserDTO} from "./newUserDTO";
import {JwtAuthenticationRequest} from "../authentication/jwt-authentication-request";

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

  public register(user: NewUserDTO): Observable<any> {
    return this.http.post(this.url, user);
  }

  public login(auth: JwtAuthenticationRequest): Observable<any> {
    return this.http.post("/auth", auth);
  }

}
