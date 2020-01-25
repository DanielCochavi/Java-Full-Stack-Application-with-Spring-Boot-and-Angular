import {Component, OnInit} from '@angular/core';
import {Todo} from '../todos-list/todos-list.component';
import {TodoDataService} from '../service/data/todo-data.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  id: number;
  todo: Todo;

  constructor(private todoService: TodoDataService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id']; // to initialize the id
    this.todo = new Todo(this.id, '', false, new Date()); // in order to get a non-null data for the todo right
    console.log(this.todo);
    if (this.id != -1) { // just in case of updating existing todo
      // after the ngOnInit() finished and the html loaded
      this.todoService.retrieveTodo('daniel', this.id)
        .subscribe(
          data => this.todo = data // to present the todo's data
        );
    }
  }

  saveTodo() {
    if (this.id == -1) { // == for comparing primitives in JS
      this.todoService.addTodo('daniel', this.todo).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['todos']);
        }
      );
    } else {
      this.todoService.updateTodo('daniel', this.id, this.todo).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['todos']);
        }
      );
    }
  }
}
