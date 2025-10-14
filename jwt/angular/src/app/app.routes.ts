import { Routes } from '@angular/router';
import { Connexion } from './connexion/connexion';
import { Inscription } from './inscription/inscription';

export const routes: Routes = [
    {
      path: "",
      component: Connexion
    },
    {
      path: "inscription",
      component: Inscription
    }
];
