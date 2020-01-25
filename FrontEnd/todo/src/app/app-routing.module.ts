import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {ErrorComponent} from './error/error.component';
import {TodosListComponent} from './todos-list/todos-list.component';
import {LogoutComponent} from './logout/logout.component';
import {RouteGuardService} from './service/route-guard.service';
import {TodoComponent} from './todo/todo.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'welcome/:name', component: WelcomeComponent, canActivate: [RouteGuardService]}, // canActivate - enable access for
  {path: 'todos', component: TodosListComponent, canActivate: [RouteGuardService]},       // the routing url only if logged in before
  {path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService]},
  {path: 'todos/:id', component: TodoComponent, canActivate: [RouteGuardService]},

  {path: '**', component: ErrorComponent} // Anything other than the specified above
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
