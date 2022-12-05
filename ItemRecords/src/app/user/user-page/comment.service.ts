import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserComment} from "./user-comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiUrl = "http://localhost:8080/api/comments/"

  constructor(
    private http: HttpClient
  ) { }

  getAllByUser(userId: number) {
    return this.http.get<UserComment[]>(this.apiUrl + "all/" + userId)
  }

  addComment(comment: UserComment, userId: number) {
    return this.http.post<String>(this.apiUrl + "add/" + userId, comment)
  }

}
