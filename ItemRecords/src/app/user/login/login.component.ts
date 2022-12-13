import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../authentication/authentication.service";
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../authentication/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ""
  password = ""

  isLoggedIn = false
  isLoginFailed = false
  errorMessage = ""
  loggedUser: any = {}

  constructor(
    private authService: AuthenticationService,
    private tokenStorage: TokenStorageService,
    private toastr: ToastrService
  ) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true
      this.loggedUser = this.tokenStorage.getUser()
    }
  }

  handleLogin() {
    let username = this.username
    let password = this.password

    this.authService.login(username, password).subscribe({
      next: data => {
        this.tokenStorage.saveToken(data.token)
        this.tokenStorage.saveUser(data)

        this.isLoginFailed = false
        this.isLoggedIn = true
        this.loggedUser = this.tokenStorage.getUser()
        window.location.reload()
      },
      error: err => {
        this.errorMessage = err.error.message
        this.isLoginFailed = true
        this.toastr.error("Chyba při přihlašování")
      }
    })
  }

}
