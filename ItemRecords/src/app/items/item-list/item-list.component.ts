import { Component, OnInit } from '@angular/core';
import {Item} from "../item";
import {ItemService} from "../item.service";
import {ToastrService} from "ngx-toastr";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";
import {AddItemFormComponent} from "../add-item-form/add-item-form.component";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Item[] = [];

  public displayedColumns: string[] = ['itemId', 'itemName', 'category', 'itemDescription', 'actions'];

  constructor(
    private itemService: ItemService,
    private toastr: ToastrService,
    private authService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.itemService.getAllByUserId(this.authService.getLoggedInUsername())
      .subscribe({
        next: data => {
          this.items = data;
          console.log(this.items)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error loading items! " + err)
        }
      })
  }

  deleteItem(itemId: number) {
    this.itemService.deleteItem(itemId)
      .subscribe({
        next: () => {
          this.toastr.success("Předmět úspěšně smazán")
          setTimeout(() => {location.reload()}, 1500);
        },
        error: err => {
          this.toastr.error("Chyba při mazání předmětu")
          console.log("Error deleting item: " + err.message)
        }
      })
  }

  editItem(itemId: number) {

  }

}
