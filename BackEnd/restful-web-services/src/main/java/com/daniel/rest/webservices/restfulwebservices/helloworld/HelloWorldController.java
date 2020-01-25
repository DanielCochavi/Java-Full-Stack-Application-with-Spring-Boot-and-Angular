package com.daniel.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200") // for allowing communication between
                                                 // port 8080 to 2400
@RestController // RestController - handle REST requests
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world") // or using @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        // throw new RuntimeException("An Error Had Occurred - For more information, contact your admin.");
        return new HelloWorldBean("Hello World! - Changed");
    }

    // /hello-world/path-variable/daniel
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

}
