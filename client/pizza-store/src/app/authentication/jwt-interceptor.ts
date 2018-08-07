import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {JwtConsts} from "./jwt-consts";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!req.url.includes("auth")) {
      if (localStorage.getItem(JwtConsts.localStorageTokenKey) != null) {
        let token = localStorage.getItem(JwtConsts.localStorageTokenKey);
        let bearer: String = JwtConsts.bearer_prefix;
        const modified = req.clone({setHeaders: {'Authorization': bearer + token}});
        return next.handle(modified);
      }
    }
    return next.handle(req);
  }

}
