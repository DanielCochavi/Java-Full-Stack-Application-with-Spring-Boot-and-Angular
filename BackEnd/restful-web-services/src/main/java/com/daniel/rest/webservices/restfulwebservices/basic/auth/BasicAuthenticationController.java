package com.daniel.rest.webservices.restfulwebservices.basic.auth;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
/* We created this class for authenticated users from the backend and not as hardcoded data. */
public class BasicAuthenticationController {

    @RequestMapping(method = RequestMethod.GET, path = "/basicauth")
    public AuthenticationBean authenticationBean() {
        return new AuthenticationBean("You are authenticated!");
    }


}
