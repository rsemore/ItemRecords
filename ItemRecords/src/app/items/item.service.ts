import {Injectable} from '@angular/core';
import {Item} from "./item";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const apiUrl = "http://localhost:8080/api/items"

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Item[]> {
    return this.http.get<Item[]>(apiUrl + "/all");
  }

  /*addItem(item: Item) {
    this.items.push(item);
  }*/

}
