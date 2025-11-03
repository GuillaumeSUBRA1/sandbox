import { FormControl } from '@angular/forms';

export interface ConnectedUser {
  id?: number;
  email?: string;
  name?: string;
  token?: string;
}

export type CreateUserForm = {
  name: FormControl<string>;
  email: FormControl<string>;
  password: FormControl<string>;
  confirmPassword: FormControl<string>;
  accept: FormControl<boolean>;
};

export type ConnectUserForm = {
  email: FormControl<string>;
  password: FormControl<string>;
};

export interface ConnectUser {
  email: string;
  password: string;
}

export interface CreateUser extends ConnectUser{
  name: string;
}
