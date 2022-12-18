import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-item-offer-dialog',
  templateUrl: './item-offer-dialog.component.html',
  styleUrls: ['../../items/item-dialog.css']
})
export class ItemOfferDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private router: Router
  ) { }

  offer: any = this.data
  startDate: string = ''
  endDate: string = ''

  ngOnInit(): void {
    console.log(this.data)
    this.startDate = formatDate(this.offer.startDate, 'd. M. y (H:mm)', 'en-US')
    this.endDate = formatDate(this.offer.endDate, 'd. M. y (H:mm)', 'en-US')
  }

  openUserPage(user: any) {
    this.router.navigate(['/user/', user.userId])
  }

}
