import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "../category";
import {ItemService} from "../item.service";
import {AuthenticationService} from "../../user/authentication/authentication.service";
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../../user/authentication/token-storage.service";

@Component({
  selector: 'app-add-item-dialog',
  templateUrl: './add-item-dialog.component.html',
  styleUrls: ['../item-dialog.css']
})
export class AddItemDialogComponent implements OnInit {

  constructor(
    private itemService: ItemService,
    private tokenStorage: TokenStorageService,
    private toastr: ToastrService
  ) {
  }

  addItemForm: FormGroup = new FormGroup({
    itemName: new FormControl("", Validators.required),
    category: new FormControl(Category.OTHER, Validators.required),
    itemDescription: new FormControl(""),
    manufacturer: new FormControl(""),
    yearOfManufacture: new FormControl(null)
  })

  ngOnInit(): void {
  }

  addItem() {
    let itemName = this.addItemForm.value.itemName
    let category = this.addItemForm.value.category
    let itemDescription = this.addItemForm.value.itemDescription
    let manufacturer = this.addItemForm.value.manufacturer
    let yearOfManufacture = this.addItemForm.value.yearOfManufacture
    let userId = this.tokenStorage.getUser().userId

    this.itemService.addItem({
        itemName: itemName,
        category: category,
        itemDescription: itemDescription,
        manufacturer: manufacturer,
        yearOfManufacture: yearOfManufacture
      }, userId
    )
      .subscribe({
        next: () => {
          this.toastr.success("Předmět přidán")
          this.addItemForm.reset()
        },
        error: err => {
          this.toastr.error("Chyba při přidávání předmětu")
          console.log("Error adding item: " + err.message)
        }
      });
  }

  reloadPage() {
    window.location.reload()
  }

}
