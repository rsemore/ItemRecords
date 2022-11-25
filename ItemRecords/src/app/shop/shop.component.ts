import { Component, OnInit } from '@angular/core';
import {ItemOffer} from "./item-offer/item-offer";
import {ItemOfferService} from "./item-offer/item-offer.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  offers: ItemOffer[] = [];

  constructor(private itemOfferService : ItemOfferService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.itemOfferService.getAll()
      .subscribe({
        next: data => {
          this.offers = data;
          console.log(data);
          //this.toastr.success("Offers successfully loaded!")
        },
        error: err => {
          console.log("Error loading offers! " + err)
          this.toastr.error("Error loading offers!")
        }
      })
  }

}
