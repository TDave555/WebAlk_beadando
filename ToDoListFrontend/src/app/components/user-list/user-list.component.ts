import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-list',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  newUser: User = { id: 0, username: '', role: 'user' };
  creatnewUser: any = { username: '', password: '', role: 'user' };


  constructor(private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe(
      (data) => this.users = data,
      (error) => console.error('Error loading users:', error)
    );
  }

  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe(
      () => this.loadUsers(),
      (error) => console.error('Error deleting user:', error)
    );
  }

  createUser() {
    this.userService.createUser(this.newUser).subscribe(
      (createdUser) => {
        console.log('User created:', createdUser);
        this.creatnewUser = { username: '', password: '', role: 'user' };
      },
      (error) => console.error('Error creating user:', error)
    );
  }
}
