import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Item} from "../item";
import {ItemService} from "../item.service";
import {ToastrService} from "ngx-toastr";
import {AuthenticationService} from "../../user/authentication/authentication.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {EditItemDialogComponent} from "../edit-item-dialog/edit-item-dialog.component";
import {AddItemDialogComponent} from "../add-item-dialog/add-item-dialog.component";
import {SellItemDialogComponent} from "../sell-item-dialog/sell-item-dialog.component";
import {Category} from "../category";
import {TokenStorageService} from "../../user/authentication/token-storage.service";
import {ItemDetailsDialogComponent} from "../item-details-dialog/item-details-dialog.component";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Item[] = []
  item: any | null = null

  filteredItems: Item[] = []
  _filterText: string = ""

  category = Category
  enumKeys: string[] = []
  selectedCategory: any

  checkedIsForOffer = false

  pageSlice: Item[] = []

  get filterText() {
    return this._filterText
  }

  set filterText(value: string) {
    this._filterText = value
    this.filteredItems = this.filterItemsByName(value)
    this.pageSlice = this.filteredItems.slice(0, 10)
  }

  public displayedColumns: string[] = ['itemName', 'category', 'itemDescription', 'actions'];

  constructor(
    private itemService: ItemService,
    private toastr: ToastrService,
    private tokenStorage: TokenStorageService,
    private router: Router,
    private dialog: MatDialog
  ) {
    this.enumKeys = Object.keys(this.category)
  }

  ngOnInit(): void {
    this.itemService.getAllByUserId(this.tokenStorage.getUser().userId)
      .subscribe({
        next: data => {
          this.items = data
          this.filteredItems = this.items
          this.pageSlice = this.filteredItems.slice(0, 10)
          console.log(this.items)
        },
        error: err => {
          this.toastr.error("Chyba p??i na????t??n?? dat!")
          console.log("Error loading items! " + err)
        }
      })
  }

  onPageChange(event: PageEvent) {
    const startIndex = event.pageIndex * event.pageSize
    let endIndex = startIndex + event.pageSize
    if (endIndex > this.filteredItems.length)
      endIndex = this.filteredItems.length
    this.pageSlice = this.filteredItems.slice(startIndex, endIndex)
  }

  filterItemsByName(filterTerm: string) {
    if (this.items.length == 0 || filterTerm == '')
      return this.items
    else {
      return this.items.filter((item) => {
        return item.itemName.toLowerCase().includes(filterTerm.toLowerCase())
      })
    }
  }

  valueChanged() {
    this.filteredItems = this.items.filter((item) => {
      return item.category === this.selectedCategory
    })
    this.pageSlice = this.filteredItems.slice(0, 10)
  }

  filterChange() {
    if (this.checkedIsForOffer)
      this.filteredItems = this.items.filter((item: any) => {
        return item.itemOffer != null
      })
    if (!this.checkedIsForOffer)
      this.resetSearch()
    this.pageSlice = this.filteredItems.slice(0, 10)
  }

  resetSearch() {
    this.filteredItems = this.items
    this.pageSlice = this.filteredItems.slice(0, 10)
    this.selectedCategory = null
    this._filterText = ""
  }

  addItem() {
    this.dialog.open(AddItemDialogComponent, {disableClose: true})
  }

  confirmDeletion(messageType: string, id: number) {
    if (messageType == 'ITEM') {
      if (confirm('Opravdu chcete p??edm??t smazat?'))
        this.deleteItem(id)
    }
    if (messageType == 'OFFER') {
      if (confirm('Opravdu chcete nab??dku odstranit?'))
        this.deleteItemOffer(id)
    }
  }

  deleteItem(itemId: number) {
    this.itemService.deleteItem(itemId)
      .subscribe({
        next: () => {
          this.toastr.success("P??edm??t ??sp????n?? smaz??n")
          setTimeout(() => {
            location.reload()
          }, 1500);
        },
        error: err => {
          this.toastr.error("Chyba p??i maz??n?? p??edm??tu")
          console.log("Error deleting item: " + err.message)
        }
      })
  }

  showItemDetails(itemId: number) {
    this.itemService.getById(itemId)
      .subscribe({
        next: result => {
          this.dialog.open(ItemDetailsDialogComponent, {data: result})
        },
        error: err => {
          this.toastr.error("P??edm??t nenalezen")
          console.log("Error finding item: " + err.message)
        }
      })
  }

  editItem(itemId: number) {
    this.itemService.getById(itemId)
      .subscribe({
        next: result => {
          this.dialog.open(EditItemDialogComponent, {data: result})
        },
        error: err => {
          this.toastr.error("P??edm??t nenalezen")
          console.log("Error finding item: " + err.message)
        }
      })
  }

  sellItem(itemId: number) {
    this.itemService.getById(itemId)
      .subscribe({
        next: result => {
          this.dialog.open(SellItemDialogComponent, {disableClose: true, data: result})
        },
        error: err => {
          this.toastr.error("P??edm??t nenalezen")
          console.log("Error finding item: " + err.message)
        }
      })
  }

  deleteItemOffer(offerId: number) {
    this.itemService.deleteItemOffer(offerId)
      .subscribe({
        next: () => {
          this.toastr.success("Nab??dka ??sp????n?? odstran??na")
          setTimeout(() => {
            location.reload()
          }, 1500);
        },
        error: err => {
          this.toastr.error("Chyba p??i maz??n?? nab??dky")
          console.log("Error deleting item: " + err.message)
        }
      })
  }

}
