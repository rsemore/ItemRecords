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

  get filterText() {
    return this._filterText
  }

  set filterText(value: string) {
    this._filterText = value
    this.filteredItems = this.filterItemsByName(value)
  }

  public displayedColumns: string[] = [/*'itemId', */'itemName', 'category', 'itemDescription', 'actions'];

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
          console.log(this.items)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error loading items! " + err)
        }
      })
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

  selectedCategory: any

  valueChanged() {
    console.log("Filtering by category " + this.selectedCategory)
    this.filteredItems = this.items.filter((item) => {
      return item.category === this.selectedCategory
    })
  }

  resetSearch() {
    this.filteredItems = this.items
    this.selectedCategory = null
    this._filterText = ""
  }

  addItem() {
    this.dialog.open(AddItemDialogComponent, {disableClose: true})
  }

  confirmDeletion(messageType: string, id: number){
    if(messageType == 'ITEM') {
      if(confirm('Opravdu chcete předmět smazat?'))
        this.deleteItem(id)
    }
    if(messageType == 'OFFER') {
      if(confirm('Opravdu chcete nabídku odstranit?'))
        this.deleteItemOffer(id)
    }
  }

  deleteItem(itemId: number) {
    this.itemService.deleteItem(itemId)
      .subscribe({
        next: () => {
          this.toastr.success("Předmět úspěšně smazán")
          setTimeout(() => {
            location.reload()
          }, 1500);
        },
        error: err => {
          this.toastr.error("Chyba při mazání předmětu")
          console.log("Error deleting item: " + err.message)
        }
      })
  }

  editItem(itemId: number) {
    this.itemService.getById(itemId)
      .subscribe({
        next: result => {
          this.dialog.open(EditItemDialogComponent, {disableClose: true, data: result})
        },
        error: err => {
          this.toastr.error("Předmět nenalezen")
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
          this.toastr.error("Předmět nenalezen")
          console.log("Error finding item: " + err.message)
        }
      })
  }

  deleteItemOffer(offerId: number) {
    this.itemService.deleteItemOffer(offerId)
      .subscribe({
        next: () => {
          this.toastr.success("Nabídka úspěšně odstraněna")
          setTimeout(() => {
            location.reload()
          }, 1500);
        },
        error: err => {
          this.toastr.error("Chyba při mazání nabídky")
          console.log("Error deleting item: " + err.message)
        }
      })
  }

}
