import { NgModule } from '@angular/core';

import { FeatherModule } from 'angular-feather';
import { Github, Mail, Linkedin, Lock, User } from 'angular-feather/icons';

const icons = {
  Mail,
  Github,
  Linkedin,
  Lock,
  User
};

@NgModule({
  imports: [FeatherModule.pick(icons)],
  exports: [FeatherModule],
})
export class IconsModule {}
