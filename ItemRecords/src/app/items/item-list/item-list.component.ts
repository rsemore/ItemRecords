import { Component, OnInit } from '@angular/core';
import {Item} from "../item";
import {ItemService} from "../item.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Item[] = [];

  public displayedColumns: string[] = [/*'itemId', */'itemName', 'category', 'itemDescription'];

  constructor(private itemService: ItemService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.itemService.getAll()
      .subscribe({
        next: data => {
          this.items = data;
          //this.toastr.success("Items successfully loaded!")
        },
        error: err => {
          console.log("Error loading items! " + err)
          this.toastr.error("Error loading items!")
        }
      })
  }

}
