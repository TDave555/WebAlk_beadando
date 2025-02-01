import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8081';
  private currentUserRole: string | null = null;
  private loggedIn = new BehaviorSubject<boolean>(this.isLoggedIn());
  private adminStatus = new BehaviorSubject<boolean>(this.isAdmin());

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Promise<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { username, password }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        this.currentUserRole = response.role;
        this.loggedIn.next(true);
        this.adminStatus.next(response.role === 'admin');
      }),
      catchError(error => {
        console.error('Login error:', error);
        throw error;
      })
    ).toPromise();
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.currentUserRole = null;
    this.loggedIn.next(false);
    this.adminStatus.next(false);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  isLoggedIn$(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'admin';
  }

  isAdmin$(): Observable<boolean> {
    return this.adminStatus.asObservable();
  }

  getCurrentUserRole(): Observable<string> {
    if (this.currentUserRole) {
      return of(this.currentUserRole);
    } else {
      return this.http.get<string>(`${this.apiUrl}/current-user-role`).pipe(
        tap(role => this.currentUserRole = role),
        catchError(error => {
          console.error('Error getting user role:', error);
          return of('');
        })
      );
    }
  }

  getLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  getAdminStatus(): Observable<boolean> {
    return this.adminStatus.asObservable();
  }

}
