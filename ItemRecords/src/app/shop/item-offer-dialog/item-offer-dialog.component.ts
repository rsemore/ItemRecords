import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-item-offer-dialog',
  templateUrl: './item-offer-dialog.component.html',
  styleUrls: ['./item-offer-dialog.component.css']
})
export class ItemOfferDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any
  ) { }

  offer: any = this.data

  ngOnInit(): void {
    console.log(this.data)
  }

}
