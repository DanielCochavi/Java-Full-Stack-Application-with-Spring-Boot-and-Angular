package com.daniel.rest.webservices.restfulwebservices.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// @Service annotation indicates that an annotated class is a service Spring managed class.
// It turns the class into a Spring bean at the auto-scan time.
// Classes decorated with this annotation are considered as candidates for auto-detection when using
// annotation-based configuration and classpath scanning.
@Service
public class HardcodedService {

    private static List<Todo> todos = new ArrayList<>();
    private static long idCounter = 0;

    // static block is used for static initializations of a class.
    // This code inside static block is executed only once: the first time you make an
    // object of that class or the first time you access a static member of that class
    // (even if you never make an object of that class)
    static {
        todos.add(new Todo(++idCounter, "Daniel",
                "Create a full-stack app with Angular & StringBoot", new Date(), false));
        todos.add(new Todo(++idCounter, "Daniel",
                "Search daily open SW positions", new Date(), false));
        todos.add(new Todo(++idCounter, "Daniel",
                "To be attending to the interviews preparation workshop", new Date(), false));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo save(Todo todo) {
        if (todo.getId() == -1 || todo.getId() == 0) { // if the todo didn't exist already, insert to the list
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);
        if (todo == null) {
            return null;
        }
        if (todos.remove(todo)) {
            return todo;
        }
        return null;
    }

    public Todo findById(long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }
}
