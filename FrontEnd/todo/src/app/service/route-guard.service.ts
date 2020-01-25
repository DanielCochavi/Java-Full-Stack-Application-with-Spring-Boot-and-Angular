import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {HardcodedAuthenticationService} from './hardcoded-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate { // CanActivate - for using this service in app-routing

  constructor(private hardcodedAuthenticationService: HardcodedAuthenticationService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.hardcodedAuthenticationService.isUserLoggedIn()) {
      return true;
    }

    this.router.navigate(['login']);
    return false;
  }
}
