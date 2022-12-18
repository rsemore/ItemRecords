import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-item-details-dialog',
  templateUrl: './item-details-dialog.component.html',
  styleUrls: ['../item-dialog.css']
})
export class ItemDetailsDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) private data: any) { }

  item: any = {}

  ngOnInit(): void {
    this.item = this.data
  }

}
