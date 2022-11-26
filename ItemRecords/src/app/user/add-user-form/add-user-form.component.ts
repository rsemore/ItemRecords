import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegistrationService} from "./registration/registration.service";

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent implements OnInit {

  constructor(
    private registrationService: RegistrationService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  addUserForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", Validators.required)
  });

  ngOnInit(): void {
  }

  addUser() {
    let username = this.addUserForm.value.username;
    let email = this.addUserForm.value.email;
    let password = this.addUserForm.value.password;

    this.registrationService.addUser({
      username: username,
      email: email,
      password: password
    })
      .subscribe({
        next: () => {
          this.toastr.success("Registrace byla úspěšná")
          this.router.navigate(["/login"])
        },
        error: err => {
          this.toastr.error("Během registrace došlo k chybě")
          console.log("Error adding user: " + err.message);
        }
      });
  }

}
