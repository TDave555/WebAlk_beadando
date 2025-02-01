import { Component, OnInit } from '@angular/core';
import { TodoService } from '../../services/todo.service';
import { CategoryService } from '../../services/category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Todo } from '../../models/todo.model';
import { Category } from '../../models/category.model';

@Component({
  selector: 'app-todo-list',
  imports: [CommonModule, FormsModule],
  templateUrl: './todo-list.component.html',
  styleUrl: './todo-list.component.css'
})
export class TodoListComponent implements OnInit {
  todos?: Todo[] = [];
  categories: Category[] = [];
  newTodo: any = { title: '', description: '', categoryId: null };

  constructor(
    private todoService: TodoService,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.loadTodos();
    this.loadCategories();
  }

  loadTodos(): void {
    this.todoService.getAllTodos().subscribe(
      (data) => this.todos = data,
      (error) => console.error('Error loading todos:', error)
    );
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe(
      (data) => this.categories = data,
      (error) => console.error('Error loading categories:', error)
    );
  }

  addTodo(): void {
    this.todoService.createTodo(this.newTodo).subscribe(
      () => {
        this.loadTodos();
        this.newTodo = { title: '', description: '', categoryId: null };
      },
      (error) => console.error('Error adding todo:', error)
    );
  }

  deleteTodo(id: number): void {
    this.todoService.deleteTodo(id).subscribe(
      () => this.loadTodos(),
      (error) => console.error('Error deleting todo:', error)
    );
  }

  toggleComplete(todo: Todo): void {
    todo.completed = !todo.completed;
    this.todoService.updateTodo(todo.id, todo).subscribe(
      () => {
        console.log('Todo updated successfully');
      },
      (error) => {
        console.error('Error updating todo:', error);
      }
    );
  }
}
