package com.daniel.rest.webservices.restfulwebservices.jwt.resource;

import java.io.Serializable;

public class JwtTokenRequest implements Serializable {

    private static final long serialVersionUID = -5616176897013108345L;

    private String username;
    private String password;

    // in order to get the JWT token we enter in the RESTlet tool this url: http://localhost:8080/authenticate
    // with POST request and username, password. To refresh it: http://localhost:8080/refresh with Authorization header.
/*
    {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYW5pZWwiLCJleHAiOjE1ODAyMjgzMjMsImlhdCI6MTU3OTYyMzUyM30.T1CB7SPt5XxWpFn-1vUIUIiLs4mLtWaemS59l6Kuv7-b4weiyoJXW5Gjh6y_BVIGa3uQJDSCd4zCF1lyWZDzUg"
    }
    */

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
