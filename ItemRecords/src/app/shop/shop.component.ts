import {Component, OnInit} from '@angular/core';
import {ItemOffer} from "./item-offer/item-offer";
import {ItemOfferService} from "./item-offer/item-offer.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {ItemOfferDialogComponent} from "./item-offer-dialog/item-offer-dialog.component";
import {EditItemDialogComponent} from "../items/edit-item-dialog/edit-item-dialog.component";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  offers: any[] = [];

  constructor(
    private itemOfferService: ItemOfferService,
    private toastr: ToastrService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.itemOfferService.getAll()
      .subscribe({
        next: data => {
          this.offers = data;
          console.log(data);
        },
        error: err => {
          console.log("Error loading offers! " + err)
          this.toastr.error("Error loading offers!")
        }
      })
  }

  openOfferDialog(offerId: number) {
    this.itemOfferService.getById(offerId)
      .subscribe({
        next: result => {
          this.dialog.open(ItemOfferDialogComponent, {data: result})
        },
        error: err => {
          this.toastr.error("Nabídka nenalezena")
          console.log("Error finding item: " + err.message)
        }
      })
  }

}
