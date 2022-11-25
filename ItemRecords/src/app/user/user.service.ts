import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //private headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  constructor(private http : HttpClient) {
  }

  private apiUrl : String = "http://localhost:8080/";


  addUser(user: User) {
    return this.http.post<String>(this.apiUrl + "api/users/add", user);
    //return this.http.post<String>(this.apiUrl + "api/users/add", {username: "Bob", email: "bob@bob.cz", password: "heslo"});
  }
}
