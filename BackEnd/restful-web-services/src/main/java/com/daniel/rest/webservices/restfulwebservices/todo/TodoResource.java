package com.daniel.rest.webservices.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoResource {
    // @Autowired - A new style of Dependency Injection.
    // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    // It will autowired the matched property of any bean in current Spring container.
    // The @Autowired annotation is auto wire the bean by matching data type.
    @Autowired
    private HardcodedService hardcodedService;

    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return hardcodedService.findAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{username}/todos/{id}")
    // ResponseEntity - enable us to return a specific status back
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
        Todo todo = hardcodedService.deleteById(id);
        if(todo != null){
            return ResponseEntity.noContent().build(); // for successful delete
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username,@PathVariable long id) {
        return hardcodedService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/todos/{id}") // PUT - update
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,@PathVariable long id, @RequestBody Todo todo) {
        // @RequestBody - For access to the HTTP request body. Body content is converted to the declared method argument type.
        // Default constructor is required (according to the type. e.g. - here, is for the Todo component)
        Todo updatedTodo = this.hardcodedService.save(todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    // path = "/users/{username}/todos" , still without id because we creating a new todo
    @RequestMapping(method = RequestMethod.POST, path = "/users/{username}/todos") // POST - create
    public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
        Todo createdTodo = this.hardcodedService.save(todo);

        // creating a new url with the new id assigning to that created new todo
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).
                  toUri();

        return ResponseEntity.created(uri).build(); // return created status. We could return status with the new todo also.
    }

}
