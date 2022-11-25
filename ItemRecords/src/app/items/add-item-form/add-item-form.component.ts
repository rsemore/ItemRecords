import { Component, OnInit } from '@angular/core';
import {ItemService} from "../item.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "../category";
import {Item} from "../item";

@Component({
  selector: 'app-add-item-form',
  templateUrl: './add-item-form.component.html',
  styleUrls: ['./add-item-form.component.css']
})
export class AddItemFormComponent implements OnInit {

  constructor(private itemService: ItemService) { }

  addItemForm: FormGroup = new FormGroup({
    itemId: new FormControl(1, Validators.required),
    itemName: new FormControl("", Validators.required),
    category: new FormControl(Category.OTHER, Validators.required),
    itemDescription: new FormControl("")
  })

  ngOnInit(): void {
  }

  addItem() {
    var itemId = this.addItemForm.value.itemId;
    var itemName = this.addItemForm.value.itemName;
    var category = this.addItemForm.value.category;
    var itemDescription = this.addItemForm.value.description;
    //this.itemService.addItem(new Item(itemId, itemName, category, itemDescription));
  }

}
