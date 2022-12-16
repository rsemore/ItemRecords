import {Component, OnInit} from '@angular/core';
import {ItemOfferService} from "./item-offer/item-offer.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {ItemOfferDialogComponent} from "./item-offer-dialog/item-offer-dialog.component";
import {Category} from "../items/category";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  offers: any[] = [];

  filteredOffers: any[] = []
  _filterText: string = ""

  category = Category
  enumKeys: string[] = []

  get filterText() {
    return this._filterText
  }

  set filterText(value: string) {
    this._filterText = value
    this.filteredOffers = this.filterOffersByName(value)
  }

  constructor(
    private itemOfferService: ItemOfferService,
    private toastr: ToastrService,
    private dialog: MatDialog
  ) {
    this.enumKeys = Object.keys(this.category)
  }

  ngOnInit(): void {
    this.itemOfferService.getAll()
      .subscribe({
        next: data => {
          this.offers = data
          this.filteredOffers = this.offers
          console.log(data)
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

  filterOffersByName(filterTerm: string) {
    if (this.offers.length == 0 || filterTerm == '')
      return this.offers
    else {
      return this.offers.filter((offer) => {
        return offer.item.itemName.toLowerCase().includes(filterTerm.toLowerCase())
      })
    }
  }

  selectedCategory: any

  valueChanged() {
    console.log("Filtering by category " + this.selectedCategory)
    this.filteredOffers = this.offers.filter((offer) => {
      return offer.item.category === this.selectedCategory
    })
  }

  resetSearch() {
    this.filteredOffers = this.offers
    this.selectedCategory = null
    this._filterText = ""
  }

}
