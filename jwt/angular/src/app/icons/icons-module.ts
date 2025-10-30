import { NgModule } from '@angular/core';

import { FeatherModule } from 'angular-feather';
import {
  Github,
  Mail,
  Linkedin,
  Lock,
  User,
  ChevronRight,
  Activity,
  Settings,
  LogOut,
  LogIn,
} from 'angular-feather/icons';

const icons = {
  Mail,
  Github,
  Linkedin,
  Lock,
  User,
  ChevronRight,
  Activity,
  Settings,
  LogOut,
  LogIn,
};

@NgModule({
  imports: [FeatherModule.pick(icons)],
  exports: [FeatherModule],
})
export class IconsModule {}
