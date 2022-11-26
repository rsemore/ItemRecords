import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../user/login/authentication/authentication.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  username: String = this.authService.getLoggedInUsername();

  ngOnInit(): void {
  }

}
