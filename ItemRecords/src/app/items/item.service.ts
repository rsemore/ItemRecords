import {Injectable} from '@angular/core';
import {Item} from "./item";
import {HttpClient} from "@angular/common/http";
import {ItemOffer} from "../shop/item-offer/item-offer";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private apiUrl = "http://localhost:8080/api/items/"
  private apiUrlOffers = "http://localhost:8080/api/offers/"

  constructor(private http: HttpClient) {
  }

  getAllByUserId(userId: number) :Observable<any> {
    return this.http.get<Item[]>(this.apiUrl + "all/" + userId)
  }

  getById(itemId: number) :Observable<any> {
    return this.http.get(this.apiUrl + "get/" + itemId)
  }

  addItem(item: Item, userId: number) :Observable<any> {
    return this.http.post<String>(this.apiUrl + "add/" + userId, item)
  }

  deleteItem(itemId: number) :Observable<any> {
    return this.http.delete(this.apiUrl + "delete/" + itemId, {responseType: 'text'})
  }

  editItem(item: Item, itemId: number) :Observable<any> {
    return this.http.put(this.apiUrl + "edit/" + itemId, item, {responseType: 'text'})
  }

  sellItem(offer: ItemOffer, itemId: number) :Observable<any> {
    return this.http.post(this.apiUrlOffers + "sell/" + itemId, offer)
  }

  deleteItemOffer(offerId: number) :Observable<any> {
    return this.http.delete(this.apiUrlOffers + "delete/" + offerId, {responseType: 'text'})
  }

}
