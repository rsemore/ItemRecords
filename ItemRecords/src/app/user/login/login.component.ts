import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "./authentication/authentication.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: String | undefined;
  password: String | undefined;

  isLoggedIn = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();
  }

  handleLogin() {
    let username = this.username
    let password = this.password

    if(username == null || password == null)
      this.toastr.warning("Vyplňte přihlašovací údaje")

    this.authService.loginUser({
      username: username!.toString(),
      password: password!.toString(),
      email: ""
    })
      .subscribe({
        next: () => {
          this.authService.registerSuccessfulLogin(username!.toString())
          this.toastr.success("Přihlášení bylo úspěšné")
          this.router.navigate([""])
          window.location.reload();
        },
        error: err => {
          this.toastr.warning("Nesprávné přihlašovací údaje");
          console.log(err.message);
        }
      })
  }

}
