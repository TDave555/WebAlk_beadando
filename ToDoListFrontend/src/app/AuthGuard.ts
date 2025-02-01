import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.authService.getLoggedIn().pipe(
      tap(loggedIn => {
        if (!loggedIn) {
          console.log('AuthGuard: user is not logged in, redirecting to login page');
          this.router.navigate(['/login']);
        }
      })
    );
  }
}
