import {Component, OnInit} from '@angular/core';
import {HardcodedAuthenticationService} from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  // isUserLogged = false;

  constructor(private hardcodedAuthenticationService: HardcodedAuthenticationService) {
  }

  ngOnInit() {
    // this.isUserLogged = this.hardcodedAuthenticationService.isUserLoggedIn();
  }

}
