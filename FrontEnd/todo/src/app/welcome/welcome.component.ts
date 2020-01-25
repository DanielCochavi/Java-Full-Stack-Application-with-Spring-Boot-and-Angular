import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WelcomeDataService} from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  name = '';
  welcomeMessageFromService: String;
  errorMessageFromService: String;


  // ActivatedRoute - for handling a routed parameters
  constructor(private route: ActivatedRoute, private service: WelcomeDataService) {
  }

  ngOnInit() {
    this.name = this.route.snapshot.params['name']; // initialization of the data member 'name'.
                                                    // using snapshot param to get the url path.
  }

  getWelcomeMessage() {
    // console.log(this.service.executeHelloWorldBeanService());
    this.service.executeHelloWorldBeanService().subscribe( // to allow async responses
      response => this.handleSuccessfulResponse(response), // response - subscribe parameter
      error => this.handleErrorResponse(error) // error - subscribe parameter
    );
  }

  getWelcomeMessageWithParameters() {
    this.service.executeHelloWorldBeanServiceWithParameters(this.name).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }

  handleSuccessfulResponse(response) {
    this.welcomeMessageFromService = response.message;
    console.log(response);
    // console.log(response.message);
  }

  handleErrorResponse(error) {
    // console.log(error.error);
    this.errorMessageFromService = error.error.message;

  }

}
