import {Injectable} from '@angular/core';
import {Item} from "./item";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private apiUrl = "http://localhost:8080/api/items/"

  constructor(private http: HttpClient) {
  }

  getAllByUserId(username: String) {
    return this.http.get<Item[]>(this.apiUrl + "all/" + username)
  }

  addItem(item: Item, userId: number) {
    return this.http.post<String>(this.apiUrl + "add/" + userId, item)
  }

  deleteItem(itemId: number) {
    return this.http.delete(this.apiUrl + "delete/" + itemId, {responseType: 'text'})
  }

  editItem(item: Item, itemId: number) {
    return this.http.put(this.apiUrl + "edit/" + itemId, item)
  }

}
