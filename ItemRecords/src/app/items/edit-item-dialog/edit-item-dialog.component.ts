import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "../category";
import {ItemService} from "../item.service";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-edit-item-dialog',
  templateUrl: './edit-item-dialog.component.html',
  styleUrls: ['../item-dialog.css']
})
export class EditItemDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private itemService: ItemService,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {
  }

  editItemForm: FormGroup = new FormGroup({
    itemName: new FormControl(this.data.itemName, Validators.required),
    category: new FormControl(this.data.category, Validators.required),
    itemDescription: new FormControl(this.data.itemDescription),
    manufacturer: new FormControl(this.data.manufacturer),
    yearOfManufacture: new FormControl(this.data.yearOfManufacture)
  })

  ngOnInit(): void {
    console.log(this.data)
  }

  editItem() {
    let itemName = this.editItemForm.value.itemName
    let category = this.editItemForm.value.category
    let itemDescription = this.editItemForm.value.itemDescription
    let manufacturer = this.editItemForm.value.manufacturer
    let yearOfManufacture = this.editItemForm.value.yearOfManufacture
    let itemId = this.data.itemId

    this.itemService.editItem({
        itemName: itemName,
        category: category,
        itemDescription: itemDescription,
        manufacturer: manufacturer,
        yearOfManufacture: yearOfManufacture
      }, itemId
    ).subscribe({
      next: () => {
        this.toastr.success("Předmět upraven")
        location.reload()
      },
      error: err => {
        this.toastr.error("Chyba při upravování předmětu")
        console.log("Error adding item: " + err.message)
      }
    })
  }

}
