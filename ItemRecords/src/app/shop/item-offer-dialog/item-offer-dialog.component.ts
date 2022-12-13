import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-item-offer-dialog',
  templateUrl: './item-offer-dialog.component.html',
  styleUrls: ['./item-offer-dialog.component.css']
})
export class ItemOfferDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private router: Router
  ) { }

  offer: any = this.data

  ngOnInit(): void {
    console.log(this.data)
  }

  openUserPage(user: any) {
    this.router.navigate(['/user/', user.userId])
  }

}
