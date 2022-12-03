import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "../category";
import {ItemService} from "../item.service";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-add-item-dialog',
  templateUrl: './add-item-dialog.component.html',
  styleUrls: ['../item-dialog.css']
})
export class AddItemDialogComponent implements OnInit {

  constructor(
    private itemService: ItemService,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) { }

  addItemForm: FormGroup = new FormGroup({
    itemName: new FormControl("", Validators.required),
    category: new FormControl(Category.OTHER, Validators.required),
    itemDescription: new FormControl("")
  })

  ngOnInit(): void {
  }

  addItem() {
    let itemName = this.addItemForm.value.itemName
    let category = this.addItemForm.value.category
    let itemDescription = this.addItemForm.value.itemDescription
    let userId = this.authService.getLoggedInUserData().userId

    this.itemService.addItem({
        itemName: itemName,
        category: category,
        itemDescription: itemDescription
      }, userId
    )
      .subscribe({
        next: () => {
          this.toastr.success("Předmět přidán")
        },
        error: err => {
          this.toastr.error("Chyba při přidávání předmětu")
          console.log("Error adding item: " + err.message)
        }
      });
  }

  reloadPage() {
    location.reload()
  }

}
