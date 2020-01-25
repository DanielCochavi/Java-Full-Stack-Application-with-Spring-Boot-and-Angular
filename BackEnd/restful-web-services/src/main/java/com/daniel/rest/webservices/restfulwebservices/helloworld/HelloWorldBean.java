package com.daniel.rest.webservices.restfulwebservices.helloworld;

/* A bean encapsulates many objects into one object so that we can access
this object from multiple places. Moreover, it provides easy maintenance. */
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
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
        return "HelloWorldBean{" +
                "message='" + message + '\'' + '}';
    }
}
