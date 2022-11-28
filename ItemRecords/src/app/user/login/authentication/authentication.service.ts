import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";
import {User} from "../../user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  USER_SESSION_ATTRIBUTE_NAME = 'authUserData'

  private apiUrl: String = "http://localhost:8080/api/users/"

  username: String | undefined
  password: String | undefined

  user: User | undefined

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
  }

  loginUser(username: String, password: String) {
    return this.http.post(this.apiUrl + "login", {username: username, password: password})
  }

  registerSuccessfulLogin(username: String) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username.toString())
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    sessionStorage.removeItem(this.USER_SESSION_ATTRIBUTE_NAME)
    this.username = ""
    this.password = ""
    this.router.navigate(["/login"])
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUsername() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ""
    return user
  }

  setLoggedInUserData(user: any) {
    sessionStorage.setItem(this.USER_SESSION_ATTRIBUTE_NAME, JSON.stringify(user))
  }

  getLoggedInUserData() {
    let user = JSON.parse(sessionStorage.getItem(this.USER_SESSION_ATTRIBUTE_NAME)!)
    if (user === null) return {}
    return user
  }

}
