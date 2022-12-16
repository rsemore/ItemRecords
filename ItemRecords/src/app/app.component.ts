import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "./user/authentication/token-storage.service";
import {ActivationGuard} from "./activation-guard";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ItemRecords';

  constructor(
    private tokenStorage: TokenStorageService,
    private activationGuard: ActivationGuard
  ) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken() != null)
      this.activationGuard.setCanActivate(true)
  }
}
