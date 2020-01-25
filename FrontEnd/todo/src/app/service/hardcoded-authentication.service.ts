import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() {
  }

  authenticate(username, password) {
    sessionStorage.setItem('authenticatedUser', username);
    if (username === 'daniel' && password === 'daniel') {
      return true;
    }
    return false;
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('authenticatedUser');
    return !(user == null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
  }

}
