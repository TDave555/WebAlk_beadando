import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { AuthService } from "./services/auth.service";
import { map, Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.authService.getCurrentUserRole().pipe(
      map(role => {
        if (role === 'ADMIN') {
          console.log('AdminGuard: user is admin');
          return true;
        } else {
          console.log('AdminGuard: user is not admin, redirecting to unauthorized page');
          this.router.navigate(['/unauthorized']);
          return false;
        }
      })
    );
  }
}
