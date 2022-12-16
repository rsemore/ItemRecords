import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = "http://localhost:8080/api/users/"
  private apiUrlGroups = "http://localhost:8080/api/groups/"

  constructor(
    private http: HttpClient
  ) { }

  getUserById(userId: number): Observable<any> {
    return this.http.get(this.apiUrl + "get/" + userId)
  }

  getAllInterestGroups(): Observable<any>  {
    return this.http.get(this.apiUrlGroups + "all")
  }

  joinGroup(groupId: number, userId: number): Observable<any>  {
    return this.http.post<String>(this.apiUrlGroups + groupId + "/user/" + userId, {})
  }

  removeFromGroup(groupId: number, userId: number): Observable<any> {
    return this.http.delete(this.apiUrlGroups + groupId + "/delete/user/" + userId)
  }

}
