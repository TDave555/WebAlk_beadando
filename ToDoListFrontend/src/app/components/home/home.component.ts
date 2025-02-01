import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { LoginComponent } from "../login/login.component";
import { BrowserModule } from '@angular/platform-browser';
import { map, Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, LoginComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  Title = 'To-Do App'

  IsLoggedIn: Observable<boolean>;
  isAdmin: Observable<boolean>;

  constructor(private authService: AuthService, private router: Router) {
      this.IsLoggedIn = this.authService.getLoggedIn();
      this.isAdmin = this.authService.getAdminStatus();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }


}
