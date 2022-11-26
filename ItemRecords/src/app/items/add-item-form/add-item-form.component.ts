import {Component, OnInit} from '@angular/core';
import {ItemService} from "../item.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "../category";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-add-item-form',
  templateUrl: './add-item-form.component.html',
  styleUrls: ['./add-item-form.component.css']
})
export class AddItemFormComponent implements OnInit {

  constructor(
    private itemService: ItemService,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {
  }

  addItemForm: FormGroup = new FormGroup({
    itemId: new FormControl(1, Validators.required),
    itemName: new FormControl("", Validators.required),
    category: new FormControl(Category.OTHER, Validators.required),
    itemDescription: new FormControl("")
  })

  ngOnInit(): void {
  }

  // TODO - item add
  addItem() {
    let itemName = this.addItemForm.value.itemName;
    let category = this.addItemForm.value.category;
    let itemDescription = this.addItemForm.value.itemDescription;

    this.itemService.addItem({
      itemName: itemName,
      category: category,
      itemDescription: itemDescription
    }, {
      username: this.authService.getLoggedInUsername(),
      password: "",
      email: ""
    })
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

}
