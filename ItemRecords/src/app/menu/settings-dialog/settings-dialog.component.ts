import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user/user.service";
import {ToastrService} from "ngx-toastr";
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
  ) {
  }

  groups: any
  user: any = this.tokenStorage.getUser()
  userData: any

  ngOnInit(): void {
    this.loadInterests()
    this.loadUserData(this.user.userId)
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

  removeFromGroup(groupId: number) {
    this.userService.removeFromGroup(groupId, this.user.userId)
      .subscribe({
        next: () => {
          this.toastr.success("Zájem odebrán")
        },
        error: err => {
          this.toastr.error("Chyba při mazání zájmu")
          console.log("Error removing interest group: " + err.message)
        }
      })
  }

  loadInterests() {
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

  loadUserData(userId: number) {
    this.userService.getUserById(userId)
      .subscribe({
        next: data => {
          this.userData = data
          console.log(this.userData)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error getting user data! " + err.error.message)
          console.log(err.message)
        }
      })
  }

  checkIfAlreadyJoined(groupIdToCheck: number) {
    let joinedInterestGroups: any[] = []
    for (const group of this.userData.interestGroups)
      joinedInterestGroups.push(group.groupId)
    for (const joinedGroupId of joinedInterestGroups) {
      if (joinedGroupId == groupIdToCheck)
        return true
    }
    return
  }

  reloadPage() {
    window.location.reload()
  }

}
