import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ItemOffer} from "./item-offer";

const apiUrl = "http://localhost:8080/api/offers"

@Injectable({
  providedIn: 'root'
})
export class ItemOfferService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<ItemOffer[]> {
    return this.http.get<ItemOffer[]>(apiUrl + "/all");
  }

}
