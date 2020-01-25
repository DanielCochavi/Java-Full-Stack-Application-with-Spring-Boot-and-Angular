import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Todo} from '../../todos-list/todos-list.component';
import {TODO_JPAAPI_URL} from '../../app.constants';

// TODO_JPAAPI_URL - connecting the todo table content from the database to the
// frontend
@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(private http: HttpClient) {
  }

  retrieveAllTodos(username) { // because each todo is connecting to username, we don't have problem
    return this.http.get<Todo[]>(`${TODO_JPAAPI_URL}/users/${username}/todos`); // returning observable
  }

  deleteTodo(username, id) {
    return this.http.delete<Todo>(`${TODO_JPAAPI_URL}/users/${username}/todos/${id}`);
  }

  retrieveTodo(username, id) {
    return this.http.get<Todo>(`${TODO_JPAAPI_URL}/users/${username}/todos/${id}`);
  }

  updateTodo(username, id, todo) {
    return this.http.put<Todo>(`${TODO_JPAAPI_URL}/users/${username}/todos/${id}`, todo);
  }

  addTodo(username, todo) {
    return this.http.post(`${TODO_JPAAPI_URL}/users/${username}/todos`, todo);
  }
}
