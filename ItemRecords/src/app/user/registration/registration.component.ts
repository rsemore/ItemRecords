import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  isSuccessful = false
  isSignUpFailed = false
  errorMessage = ""

  constructor(
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  registrationForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", Validators.required)
  });

  ngOnInit() {
  }

  handleRegistration() {
    let username = this.registrationForm.value.username
    let email = this.registrationForm.value.email
    let password = this.registrationForm.value.password

    this.authService.register(username, email, password).subscribe({
      next: data => {
        console.log(data)
        this.isSuccessful = true
        this.isSignUpFailed = false
        this.toastr.success("Registrace byla úspěšná")
        this.router.navigate(["/login"])
      },
      error: err => {
        this.errorMessage = err.error
        this.isSignUpFailed = true
        this.toastr.error(this.errorMessage)
        console.log('ERROR:' + this.errorMessage)
      }
    })
  }

}
