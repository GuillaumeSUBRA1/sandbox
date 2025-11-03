import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Component, effect, inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { IconsModule } from '../icons/icons-module';

@Component({
  selector: 'connexion',
  imports: [IconsModule],
  templateUrl: './connexion.html',
})
export class Connexion {
  formBuilder = inject(FormBuilder);
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
}
