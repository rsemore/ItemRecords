import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../user.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(private userService: UserService,
              private toastr: ToastrService,
              private router: Router
  ) {
  }

  loginUserForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required)
  });

  ngOnInit(): void {
  }

  loginUser() {
    let username = this.loginUserForm.value.username;
    let password = this.loginUserForm.value.password;

    this.userService.loginUser({
      username: username,
      password: password,
      email: ""
    })
      .subscribe({
        next: () => {
          this.toastr.success("Login successful. Welcome!")
          this.router.navigate([""])
        },
        error: err => {
          this.toastr.warning("Login invalid");
          console.log(err.message);
        }
      })
  }

}
