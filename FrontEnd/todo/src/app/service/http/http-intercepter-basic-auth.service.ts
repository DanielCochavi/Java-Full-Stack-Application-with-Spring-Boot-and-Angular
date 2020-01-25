import {Injectable} from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {BasicAuthenticationService} from '../basic-authentication.service';

@Injectable({
  providedIn: 'root'
})

/* We would need to add Authorization with every subsequent request.
  We created this service in order to automatically append the modified request header to the original request
* and make use of it on the HellowWorld and TodosList components */
export class HttpIntercepterBasicAuthService implements HttpInterceptor {

  constructor(private basicAuthenticationService: BasicAuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    // let username = 'daniel';
    // let password = 'daniel';
    // let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    let basicAuthHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser();
    if (basicAuthHeaderString && username) {
      // because we can't change the original request, we creating copy of the request adding the basic authorization header
      // on top of the original request.
      request = request.clone({setHeaders: {Authorization: basicAuthHeaderString}});
    }
    return next.handle(request); // and sending it to the Http handler
  }
}
