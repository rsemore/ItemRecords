import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent implements OnInit {
  isSuccessful = false
  isSignUpFailed = false
  errorMessage = ""

  constructor(
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  addUserForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", Validators.required)
  });

  ngOnInit() {
  }

  handleRegistration() {
    let username = this.addUserForm.value.username
    let email = this.addUserForm.value.email
    let password = this.addUserForm.value.password

    this.authService.register(username, email, password).subscribe({
      next: data => {
        console.log(data)
        this.isSuccessful = true
        this.isSignUpFailed = false
        this.toastr.success("Registrace byla úspěšná")
        this.router.navigate(["/login"])
      },
      error: err => {
        this.errorMessage = err.error.message
        this.isSignUpFailed = true
        this.toastr.error("Během registrace došlo k chybě")
        console.log("Error registering user: " + err.message);
      }
    })
  }

}
