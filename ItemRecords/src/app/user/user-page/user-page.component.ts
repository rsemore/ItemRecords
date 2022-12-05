import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../login/authentication/authentication.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../user.service";
import {CommentService} from "./comment.service";
import {ToastrService} from "ngx-toastr";
import {UserComment} from "./user-comment";
import {timeout} from "rxjs";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  comments: UserComment[] = [];
  user: any

  constructor(
    private authService: AuthenticationService,
    private commentService: CommentService,
    private userService: UserService,
    private toastr: ToastrService
  ) {
  }

  addCommentForm: FormGroup = new FormGroup({
    author: new FormControl(this.authService.getLoggedInUsername(), Validators.required),
    content: new FormControl("", Validators.required)
  })

  ngOnInit(): void {
    this.user = this.authService.getLoggedInUserData()
    // TODO - load user by id
    this.commentService.getAllByUser(this.user.userId)
      .subscribe({
        next: data => {
          this.comments = data
          console.log(this.comments)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error loading items! " + err)
        }
      })
  }

  addComment() {
    let author = this.addCommentForm.value.author
    let content = this.addCommentForm.value.content

    let userId = this.user.userId

    this.commentService.addComment({
        author: author,
        content: content
      }, userId
    )
      .subscribe({
        next: () => {
          this.toastr.success("Komentář přidán")
          setTimeout(() => {
            location.reload()
          }, 1000);
        },
        error: err => {
          this.toastr.error("Chyba při přidávání komentáře")
          console.log("Error adding item: " + err.message)
        }
      });
  }

  /*getUserData(userId: number) {
    this.userService.getUserById(userId)
      .subscribe()
  }*/

}
