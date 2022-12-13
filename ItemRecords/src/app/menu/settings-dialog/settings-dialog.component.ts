import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user/user.service";
import {ToastrService} from "ngx-toastr";
import {AuthenticationService} from "../../user/authentication/authentication.service";
import {TokenStorageService} from "../../user/authentication/token-storage.service";

@Component({
  selector: 'app-settings-dialog',
  templateUrl: './settings-dialog.component.html',
  styleUrls: ['./settings-dialog.component.css']
})
export class SettingsDialogComponent implements OnInit {

  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private tokenStorage: TokenStorageService
  ) { }

  groups: any;
  user: any = this.tokenStorage.getUser()

  ngOnInit(): void {
    console.log(this.user)

    this.userService.getAllInterestGroups()
      .subscribe({
        next: data => {
          this.groups = data
          console.log(this.groups)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error getting interest groups! " + err)
        }
      })
  }

  joinGroup(groupId: number) {
    this.userService.joinGroup(groupId, this.user.userId)
      .subscribe({
        next: () => {
          this.toastr.success("Zájem přidán")
        },
        error: err => {
          this.toastr.error("Chyba při přidávání zájmu")
          console.log("Error joining interest group: " + err.message)
        }
      })
  }

}
