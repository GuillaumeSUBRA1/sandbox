import { Component, ElementRef, inject,  ViewChild } from '@angular/core';
import { IconsModule } from '../icons/icons-module';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'inscription',
  imports: [IconsModule, CommonModule],
  templateUrl: './inscription.html',
  styleUrl: './inscription.css',
})
export class Inscription {
  @ViewChild('strength1') strength1!: ElementRef;
  @ViewChild('strength2') strength2!: ElementRef;
  @ViewChild('strength3') strength3!: ElementRef;
  @ViewChild('strength4') strength4!: ElementRef;
  @ViewChild('regPassword') regPassword!: ElementRef;

  router = inject(Router);

  testPassword(event: Event) {
    const pwd = (event.target as HTMLInputElement).value;

    // Réinitialise les couleurs
    [this.strength1, this.strength2, this.strength3, this.strength4].forEach((el) => {
      el.nativeElement.style.backgroundColor = '#374151';
    });

    // Très faible
    if (pwd.length > 0) {
      this.strength1.nativeElement.style.backgroundColor = '#ef4444';
    }

    // Faible
    if (pwd.length >= 6) {
      this.strength2.nativeElement.style.backgroundColor = '#f97316';
    }

    // Moyen
    if (pwd.length >= 8 && this.hasMaj(pwd) && this.hasNumber(pwd)) {
      this.strength3.nativeElement.style.backgroundColor = '#eab308';
    }

    // Strong
    if (pwd.length >= 10 && this.hasMaj(pwd) && this.hasNumber(pwd) && this.hasMin(pwd)) {
      this.strength4.nativeElement.style.backgroundColor = '#22c55e';
    }
  }

  hasMaj(s: string){
    return /[A-Z]/.test(s);
  }

  hasMin(s: string){
    return /[a-z]/.test(s);
  }

  hasNumber(s: string){
    return /[0-9]/.test(s);
  }


  toConnexion(){
    this.router.navigate(['']);
  }
}
