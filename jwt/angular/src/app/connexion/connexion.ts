import { Component, effect, inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IconsModule } from '../icons/icons-module';
import { AuthService } from '../../services/auth-service.service';
import { ConnectUserForm } from '../model/user.model';

@Component({
  selector: 'connexion',
  imports: [IconsModule, ReactiveFormsModule],
  templateUrl: './connexion.html',
})
export class Connexion {
  authService = inject(AuthService);
  formBuilder = inject(FormBuilder);
  router = inject(Router);
  route = inject(ActivatedRoute);

  sites = [
    { name: 'linkedin', link: 'https://www.linkedin.com/in/guillaume-subra-622689139/' },
    { name: 'github', link: 'https://github.com/GuillaumeSUBRA1' },
  ];

  connectUserForm = this.formBuilder.nonNullable.group<ConnectUserForm>({
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
    password: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
  });

  toInscription() {
    this.router.navigate(['inscription']);
  }

  connexion() {
    if (this.connectUserForm.invalid) {
      return;
    }
    this.authService
      .connexion({
        email: this.connectUserForm.value.email!,
        password: this.connectUserForm.value.password!,
      })
      .subscribe({
        next: (user) => {
          localStorage.setItem('token', user!.token!);
          this.router.navigate(['']);
        },
        error: () => {
          console.log('Échec de la connexion');
        },
      });
  }
}
