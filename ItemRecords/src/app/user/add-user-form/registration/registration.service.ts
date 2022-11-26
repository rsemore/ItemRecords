import { Injectable } from '@angular/core';
import {User} from "../../user";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private apiUrl : String = "http://localhost:8080/api/users/";

  constructor(private http: HttpClient) { }

  addUser(user: User) {
    return this.http.post<String>(this.apiUrl + "add", user);
  }

}
