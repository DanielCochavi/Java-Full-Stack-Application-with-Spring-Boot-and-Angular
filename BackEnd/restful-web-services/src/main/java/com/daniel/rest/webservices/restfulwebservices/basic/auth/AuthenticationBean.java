package com.daniel.rest.webservices.restfulwebservices.basic.auth;

/* A bean encapsulates many objects into one object so that we can access
this object from multiple places. Moreover, it provides easy maintenance. */
public class AuthenticationBean {
    private String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }

    public void setMassage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AuthenticationBean{" +
                "message='" + message + '\'' + '}';
    }
}
