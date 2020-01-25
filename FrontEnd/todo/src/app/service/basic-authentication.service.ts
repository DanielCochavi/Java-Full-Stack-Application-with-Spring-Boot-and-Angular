import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {API_URL} from '../app.constants';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticatedUser';

@Injectable({
  providedIn: 'root'
})

/* We created this service in order to add a dynamic authentication for the login page. */
export class BasicAuthenticationService {

  constructor(private http: HttpClient) {
  }

  // to connect the JWT authentication to the client side, we need to edit the post request as we see in the RESTlet.
  executeJWTAuthenticationService(username, password) {
    return this.http.post<any>(`${API_URL}/authenticate`, {username, password}
    ).pipe(
      map(
        data => {
          sessionStorage.setItem(AUTHENTICATED_USER, username);
          sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
          return data;
        }
      )
    );
  }

  executeAuthenticationService(username, password) {

    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({Authorization: basicAuthHeaderString});

    // pipe - if there is a proper response coming back, then map it and get the data back successfully.
    return this.http.get<AuthenticationBean>(`${API_URL}/basicauth`, {headers}).pipe(
      map(
        data => {
          sessionStorage.setItem(AUTHENTICATED_USER, username);
          sessionStorage.setItem(TOKEN, basicAuthHeaderString);
          return data;
        }
      )
    );
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER);
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUser()) {
      return sessionStorage.getItem(TOKEN);
    }
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER);
    return !(user == null);
  }

  logout() {
    sessionStorage.removeItem(AUTHENTICATED_USER);
    sessionStorage.removeItem(TOKEN);
  }
}

export class AuthenticationBean { // defining the structure of the response
  constructor(public message: string) {
  }
}
