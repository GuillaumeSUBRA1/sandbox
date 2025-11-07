import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, WritableSignal, computed, inject, signal } from '@angular/core';
import { ConnectedUser, ConnectUser, CreateUser } from '../app/model/user.model';
import { environment } from '../environments/environment.prod';
import { catchError, EMPTY, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  http = inject(HttpClient);

  private fetchUserWritable: WritableSignal<ConnectedUser | undefined> = signal<
    ConnectedUser | undefined
  >(undefined);
  fetchUser = computed(() => this.fetchUserWritable());

  private fetchIsAuthenticatedWritable: WritableSignal<boolean> = signal<boolean>(false);
  fetchIsAuthenticated = computed(() => this.fetchIsAuthenticatedWritable());

  isAuthenticated() {
    const token = localStorage.getItem('token');
    this.http.get<boolean>(`${environment.API_URL}/auth/is-authenticated/${token}`).subscribe({
      next: (auth) => this.fetchIsAuthenticatedWritable.set(auth),
      error: () => this.fetchIsAuthenticatedWritable.set(false),
    });
  }

  createUser(newUser: CreateUser): Observable<ConnectedUser> {
    return this.http.post<ConnectedUser>(`${environment.API_URL}/auth/register`, newUser).pipe(
      tap((user) => {
        this.fetchUserWritable.set(user);
        this.fetchIsAuthenticatedWritable.set(true);
        localStorage.setItem('token', user.token!);
      })
    );
  }

  connexion(user: ConnectUser): Observable<ConnectedUser> {
    return this.http.post<ConnectedUser>(`${environment.API_URL}/auth/login`, user).pipe(
      tap((user) => {
        this.fetchUserWritable.set(user);
        this.fetchIsAuthenticatedWritable.set(true);
      }),
      catchError(() => {
        this.fetchIsAuthenticatedWritable.set(false);
        return EMPTY;
      })
    );
  }

  disconnect() {
    localStorage.removeItem('token');
    this.fetchUserWritable.set(undefined);
    this.fetchIsAuthenticatedWritable.set(false);
  }

  getUser(token: string) {
    this.http
      .get<ConnectedUser>(`${environment.API_URL}/auth/get-user`, {
        headers: new HttpHeaders({ Authorization: `Bearer ${token}` }),
        withCredentials: true,
      })
      .subscribe({
        next: (user) => {
          this.fetchUserWritable.set(user);
          this.fetchIsAuthenticatedWritable.set(true);
        },
      });
  }
}
