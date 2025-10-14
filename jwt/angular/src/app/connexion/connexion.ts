import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { IconsModule } from '../icons/icons-module';

@Component({
  selector: 'connexion',
  imports: [IconsModule],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css'
})
export class Connexion {
  sites = [
    { name: 'linkedin', link: 'https://www.linkedin.com/in/guillaume-subra-622689139/' },
    { name: 'github', link: 'https://github.com/GuillaumeSUBRA1' },
  ];

  router = inject(Router);

  toInscription(){
    this.router.navigate(['inscription']);
  }
}
