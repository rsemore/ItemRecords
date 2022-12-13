import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../authentication/authentication.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../user.service";
import {CommentService} from "./comment.service";
import {ToastrService} from "ngx-toastr";
import {UserComment} from "./user-comment";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../authentication/token-storage.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  userId: number = 0
  user: any
  comments: UserComment[] = [];

  constructor(
    private authService: AuthenticationService,
    private commentService: CommentService,
    private tokenStorage: TokenStorageService,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  addCommentForm: FormGroup = new FormGroup({
    author: new FormControl({value: this.tokenStorage.getUser().username, disabled: true}, Validators.required),
    content: new FormControl("", Validators.required)
  })

  ngOnInit(): void {
    this.route.params.subscribe((params) => this.userId = params['userId'])
    this.router.routeReuseStrategy.shouldReuseRoute = () => false   // reload page on params change
    this.getComments(this.userId)
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
          console.log("Error adding comment: " + err.message)
        }
      });
  }
/*
  getUserData(userId: number) {
    this.userService.getUserById(userId)
      .subscribe({
        next: data => {
          this.user = data
          console.log(this.user)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error getting user! " + err)
        }
      })
  }*/

  getComments(userId: number) {
    this.commentService.getAllByUser(userId)
      .subscribe({
        next: data => {
          this.comments = data
          console.log(this.comments)
        },
        error: err => {
          this.toastr.error("Chyba při načítání dat!")
          console.log("Error loading comments! " + err)
        }
      })
  }

}
