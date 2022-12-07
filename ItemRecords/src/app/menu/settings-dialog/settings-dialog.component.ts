import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user/user.service";
import {ToastrService} from "ngx-toastr";
import {AuthenticationService} from "../../user/login/authentication/authentication.service";

@Component({
  selector: 'app-settings-dialog',
  templateUrl: './settings-dialog.component.html',
  styleUrls: ['./settings-dialog.component.css']
})
export class SettingsDialogComponent implements OnInit {

  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private authService: AuthenticationService
  ) { }

  groups: any;

  ngOnInit(): void {
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

  // TODO post gives error but user is added to group ????
  joinGroup(groupId: number) {
    this.userService.joinGroup(groupId, this.authService.getLoggedInUserData().userId)
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
