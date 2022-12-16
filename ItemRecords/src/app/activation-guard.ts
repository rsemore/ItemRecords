import {CanActivate, Router} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class ActivationGuard implements CanActivate {

  private can = false

  constructor(private router: Router) {
  }

  canActivate() {
    console.log('ActivationGuard called, can: ', this.can)
    if (!this.can) {
      this.router.navigate(['/login'])
      alert('Na tuto stránku nemáte přístup! Přihlašte se')
      return false
    }
    return true
  }

  setCanActivate(can: boolean) {
    this.can = can
  }
}
