import {Component, OnInit} from '@angular/core';
import {TodoDataService} from '../service/data/todo-data.service';
import {Router} from '@angular/router';

export class Todo {
  constructor(public id: number, public description: string,
              public done: boolean, public targetDate: Date) {
  }
}

@Component({
  selector: 'app-todos-list',
  templateUrl: './todos-list.component.html',
  styleUrls: ['./todos-list.component.css']
})

export class TodosListComponent implements OnInit {
  todos: Todo[];
  message: String;

  // = [
  //   new Todo(1, 'Create a full-stack app with Angular & StringBoot', false, new Date()),
  //   new Todo(2, 'Explore the \'Interview bit\' site', false, new Date()),
  //   new Todo(3, 'Practice one question from LeetCode', false, new Date()),
  //   new Todo(4, 'Search daily open SW positions', true, new Date()),
  //   new Todo(5, 'To be attending to the interviews preparation workshop', false, new Date())
  // ];

  constructor(private todoService: TodoDataService, private router: Router) {
  }

  refreshTodoList() {
    this.todoService.retrieveAllTodos('daniel').subscribe(
      response => {
        console.log(response);
        this.todos = response; // assigning the return response to the todos list
      }
    );
  }

  ngOnInit() {
    this.refreshTodoList();
  }

  deleteTodo(id) {
    this.todoService.deleteTodo('daniel', id).subscribe(
      response => { this.message = `Delete todo\'s id ${id} successfully!`;
        this.refreshTodoList();
      }
    );
  }

  updateTodo(id) {
    this.router.navigate(['todos', id]);
  }

  createTodo() {
    this.router.navigate(['todos', -1]); // -1 for initial new id
  }

}
