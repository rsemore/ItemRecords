import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {UserService} from "../user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent implements OnInit {

  constructor(private userService: UserService, private toastr: ToastrService) {
  }

  addUserForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", Validators.required)
  });

  ngOnInit(): void {
  }

  addUser() {
    this.userService.addUser({
      username: this.addUserForm.value.username,
      email: this.addUserForm.value.email,
      password: this.addUserForm.value.password
    })
      .subscribe(
        {
          next: () => this.toastr.success("User added"),
          error: err => {
            this.toastr.error("An error occured while adding user!")
            console.log("Error adding user! " + err.message);
          }
        }
      );
  }

}
