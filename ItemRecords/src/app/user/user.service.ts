import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) {
  }

  private apiUrl : String = "http://localhost:8080/api/users/";

  addUser(user: User) {
    return this.http.post<String>(this.apiUrl + "add", user);
  }

  loginUser(user: User) {
    return this.http.post<String>(this.apiUrl + "login", user);
  }
}
