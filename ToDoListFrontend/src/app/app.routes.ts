import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { AuthGuard } from './AuthGuard';
import { AdminGuard } from './AdminGuard';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {path: 'home', component: HomeComponent },                  //canActivate: [AuthGuard]},
  { path: 'todos', component: TodoListComponent },             //canActivate: [AuthGuard] },
  { path: 'categories', component: CategoryListComponent },   //canActivate: [AuthGuard] },
  { path: 'users', component: UserListComponent },            //canActivate: [AuthGuard, AdminGuard] },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];
