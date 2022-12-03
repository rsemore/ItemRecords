import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Item} from "../item";
import {ItemService} from "../item.service";
import {ToastrService} from "ngx-toastr";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {EditItemDialogComponent} from "../edit-item-dialog/edit-item-dialog.component";
import {AddItemDialogComponent} from "../add-item-dialog/add-item-dialog.component";
import {SellItemDialogComponent} from "../sell-item-dialog/sell-item-dialog.component";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Item[] = [];
  item: any | null = null

  public displayedColumns: string[] = ['itemId', 'itemName', 'category', 'itemDescription', 'actions'];

  constructor(
    private itemService: ItemService,
    private toastr: ToastrService,
    private authService: AuthenticationService,
    private router: Router,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.itemService.getAllByUser(this.authService.getLoggedInUsername())
      .subscribe({
        next: data => {
          this.items = data
          console.log(this.items)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error loading items! " + err)
        }
      })
  }

  addItem() {
    this.dialog.open(AddItemDialogComponent, {disableClose: true})
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
          this.toastr.success("Nabídka úspěšně smazána")
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
