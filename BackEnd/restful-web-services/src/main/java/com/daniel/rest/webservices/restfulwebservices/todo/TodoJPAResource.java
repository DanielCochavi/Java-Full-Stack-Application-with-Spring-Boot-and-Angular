package com.daniel.rest.webservices.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// We created this class in order to connect the backend to the database
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoJPAResource {

    @Autowired
    private HardcodedService hardcodedService;

    @Autowired
    private TodoJPARepository todoJPARepository;

    @RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoJPARepository.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/jpa/users/{username}/todos/{id}")
    // ResponseEntity - enable us to return a specific status back
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
        todoJPARepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return todoJPARepository.findById(id).get();
        // return hardcodedService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/jpa/users/{username}/todos/{id}") // PUT - update
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
        //todo.setUsername(username);
        Todo updatedTodo = this.todoJPARepository.save(todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/jpa/users/{username}/todos") // POST - create
    public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo createdTodo = this.todoJPARepository.save(todo);

        // creating a new url with the new id assigning to that created new todo
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(createdTodo.getId()).
                toUri();

        return ResponseEntity.created(uri).build();
    }
}