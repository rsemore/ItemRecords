import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "./authentication/authentication.service";
import {ToastrService} from "ngx-toastr";
import {User} from "../user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: String | undefined
  password: String | undefined

  loggedUser: any = {}
  isLoggedIn = false

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn()
    this.loggedUser = this.authService.getLoggedInUserData()
  }

  handleLogin() {
    let username = this.username!.toString()
    let password = this.password!.toString()

    if (username == null || password == null)
      this.toastr.warning("Vyplňte přihlašovací údaje")

    this.authService.loginUser(username, password)
      .subscribe({
        next: response => {
          this.authService.registerSuccessfulLogin(username!.toString())
          this.authService.setLoggedInUserData(response)
          console.log(response)
          this.toastr.success("Přihlášení bylo úspěšné")
          this.router.navigate(["/login"])
          setTimeout(() => {location.reload()}, 1000);
        },
        error: err => {
          this.toastr.warning("Nesprávné přihlašovací údaje");
          console.log(err.message);
        }
      })
  }

}
