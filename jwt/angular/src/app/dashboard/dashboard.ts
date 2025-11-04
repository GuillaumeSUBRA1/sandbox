import { Component, effect, inject, OnInit } from '@angular/core';
import { IconsModule } from '../icons/icons-module';
import { AuthService } from '../../services/auth-service.service';
import { ConnectedUser } from '../model/user.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'dashboard',
  imports: [IconsModule],
  templateUrl: './dashboard.html',
})
export class Dashboard implements OnInit {
  router = inject(Router);
  route = inject(ActivatedRoute);
  authService = inject(AuthService);
  user?: ConnectedUser | undefined;
  userConnected = false;
  letter?: string;

  cards: { textColor: string; bgColor: string; title: string; description: string }[] = [
    {
      textColor: 'text-indigo-400',
      bgColor: 'bg-indigo-900/30',
      title: 'Profil Utilisateur',
      description: 'Gérez vos informations personnelles et vos préférences de compte.',
    },
    {
      textColor: 'text-purple-400',
      bgColor: 'bg-purple-900/30',
      title: 'Paramètres',
      description: 'Configurez les paramètres de sécurité et de confidentialité de votre compte.',
    },
    {
      textColor: 'text-pink-400',
      bgColor: 'bg-pink-900/30',
      title: 'Activité',
      description:
        "Consultez l'historique des connexions et les activités récentes sur votre compte.",
    },
  ];

  constructor() {
    effect(() => {
      this.getUser();
    });
  }

  ngOnInit(): void {
    if (localStorage.getItem('token') != null) {
      this.userConnected = true;
      this.authService.getUser(localStorage.getItem('token')!);
    }
  }

  getUser() {
    if (localStorage.getItem('token') != null) {
      this.user = this.authService.fetchUser();
      if (this.user != undefined) {
        this.user.token = localStorage.getItem('token')!;
        this.initLetter();
        this.connected();
      }
    }
  }

  connected() {
    this.userConnected = this.authService.fetchIsAuthenticated();
  }

  login() {
    this.router.navigate(['connexion']);
  }

  register() {
    this.router.navigate(['inscription']);
  }

  logout() {
    if (this.userConnected) {
      this.authService.disconnect();
      this.userConnected = false;
      this.user = undefined;
    }
  }

  initLetter() {
    if (this.user) {
      this.letter = this.user.name?.charAt(0).toUpperCase();
    }
  }
}
