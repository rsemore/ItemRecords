import {Injectable} from '@angular/core';
import {Item} from "./item";
import {HttpClient} from "@angular/common/http";
import {User} from "../user/user";

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private apiUrl = "http://localhost:8080/api/items/"

  constructor(private http: HttpClient) {
  }

  getAllByUserId(username: String) {
    return this.http.get<Item[]>(this.apiUrl + "all/" + username);
  }

  // TODO - item add
  addItem(item: Item, user: User) {
    console.log(item + "; " + user);
    return this.http.post<String>(this.apiUrl + "add", [item, user]);
  }

}
