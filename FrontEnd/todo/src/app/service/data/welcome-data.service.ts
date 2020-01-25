import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BasicAuthenticationService} from '../basic-authentication.service';

export class HelloWorldBean { // defining the structure of the response
  constructor(public message: string) { // need to match the attribute name in the
    // Bean at the backend
  }
}

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(private http: HttpClient, private basicAuthenticationService: BasicAuthenticationService) {
  }

  executeHelloWorldBeanService() { // defining the response will be of type 'HelloWorldBean'
    return this.http.get<HelloWorldBean>('http://localhost:8080/hello-world-bean'); // returning observable
    // console.log('execute Hello World Bean Service');
  }

  executeHelloWorldBeanServiceWithParameters(name) { // using ` for parsing parameter 'name'

    let basicAuthHeaderString = this.createBasicAuthenticationHttpHeader();

    let headers = new HttpHeaders({Authorization: basicAuthHeaderString});

    // options (the second argument)- adding a basic authentication header for the request so it will know how to deal
    // with the basic authentication changes we've made in the back-end
    return this.http.get<HelloWorldBean>(`http://localhost:8080/hello-world/path-variable/${name}`, {headers});
  }

  createBasicAuthenticationHttpHeader() {
    // let username = 'daniel';
    // let password = 'daniel';

    // as we see in the format of the request Authorization header in the Restlet API
    // let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    let basicAuthHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser();

    return basicAuthHeaderString;
  }
}

