import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Category } from '../../models/category.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-category-list',
  imports: [CommonModule, FormsModule],
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css',
})
export class CategoryListComponent implements OnInit {
  categories?: Category[] = [];
  newCategory: Category = {
    id: 0,
    title: '',
    owner: new User()
  };

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe(
      (data) => this.categories = data,
      (error) => console.error('Error loading categories:', error)
    );
  }

  addCategory(): void {
    this.categoryService.createCategory(this.newCategory).subscribe(
      () => {
        this.loadCategories();
        this.newCategory = {
          id: 0,
          title: '',
          owner: new User()
        };
      },
      (error) => console.error('Error adding category:', error)
    );
  }

  deleteCategory(id: number): void {
    this.categoryService.deleteCategory(id).subscribe(
      () => this.loadCategories(),
      (error) => console.error('Error deleting category:', error)
    );
  }
}
