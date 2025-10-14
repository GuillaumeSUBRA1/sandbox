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

  router = inject(Router);

  toInscription(){
    this.router.navigate(['inscription']);
  }
}
