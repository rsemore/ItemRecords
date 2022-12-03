import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../login/authentication/authentication.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user: any

  constructor(
    private authService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.user = this.authService.getLoggedInUserData()
    // TODO - load user by id
  }

  addCommentForm: FormGroup = new FormGroup({
    author: new FormControl(this.authService.getLoggedInUsername()),
    content: new FormControl("", Validators.required)
  })

  addComment() {
    // TODO - add comment
  }

}
