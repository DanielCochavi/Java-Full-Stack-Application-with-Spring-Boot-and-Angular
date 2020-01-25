package com.daniel.rest.webservices.restfulwebservices.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // for connecting the Todo to a database
public class Todo {

    @Id
    @GeneratedValue // used to to generate automatic object IDs for entity objects
    private Long id; // changed from long to Long for JPA use

    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;

    public Todo() {

    }

    public Todo(Long id, String username, String description, Date targetDate, boolean isDone) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
