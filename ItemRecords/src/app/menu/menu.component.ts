import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../user/login/authentication/authentication.service";
import {ToastrService} from "ngx-toastr";
import {SellItemDialogComponent} from "../items/sell-item-dialog/sell-item-dialog.component";
import {SettingsDialogComponent} from "./settings-dialog/settings-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = false
  username: String | undefined
  userId: number | undefined

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn()
    this.username = this.authService.getLoggedInUsername()
    this.userId = this.authService.getLoggedInUserData().userId
  }

  handleLogout() {
    this.toastr.info("Odhlašování...")
    setTimeout(() => {
      location.reload()
    }, 700);
    this.authService.logout()
  }

  openSettingsDialog() {
    this.dialog.open(SettingsDialogComponent, {disableClose: true})
  }

}
