import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ItemService} from "../item.service";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";
import {ToastrService} from "ngx-toastr";
import {Item} from "../item";

@Component({
  selector: 'app-sell-item-dialog',
  templateUrl: './sell-item-dialog.component.html',
  styleUrls: ['../item-dialog.css']
})
export class SellItemDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private itemService: ItemService,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {
  }

  item: any = this.data

  sellItemForm: FormGroup = new FormGroup({
    price: new FormControl(0, Validators.required),
    description: new FormControl("", Validators.required),
    startDate: new FormControl(0, Validators.required),
    endDate: new FormControl(0, Validators.required),
  })

  ngOnInit(): void {
  }

  sellItem() {
    let item = this.data
    let itemId = this.data.itemId
    let price = this.sellItemForm.value.price
    let description = this.sellItemForm.value.description
    let startDate = this.sellItemForm.value.startDate
    let endDate = this.sellItemForm.value.endDate

    this.itemService.sellItem({
        item: item,
        price: price,
        description: description,
        startDate: startDate,
        endDate: endDate
      }, itemId
    ).subscribe({
      next: () => {
        this.toastr.success("Předmět dán na prodej")
        location.reload()
      },
      error: err => {
        this.toastr.error("Chyba")
        console.log("Error adding item: " + err.message)
      }
    })
  }

}
