import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {SettingsDialogComponent} from "./settings-dialog/settings-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {TokenStorageService} from "../user/authentication/token-storage.service";

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
    private tokenStorage: TokenStorageService,
    private toastr: ToastrService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken() != null)
      this.isLoggedIn = true
    this.username = this.tokenStorage.getUser().username
    this.userId = this.tokenStorage.getUser().userId
  }

  handleLogout() {
    this.toastr.info("Odhlašování...")
    setTimeout(() => location.reload(), 700);
    this.tokenStorage.signOut()
    this.isLoggedIn = false
    this.router.navigate(["/login"])
  }

  openSettingsDialog() {
    this.dialog.open(SettingsDialogComponent, {disableClose: true})
  }

  openUserPage() {
    this.router.navigate(['/user/', this.userId])
  }

}
