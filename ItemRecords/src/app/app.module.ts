import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ItemListComponent} from './items/item-list/item-list.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {ShopComponent} from './shop/shop.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {RegistrationComponent} from './user/registration/registration.component';
import {ToastrModule} from "ngx-toastr";
import {HttpClientModule} from "@angular/common/http";
import {MatGridListModule} from "@angular/material/grid-list";
import {LoginComponent} from './user/login/login.component';
import {MenuComponent} from './menu/menu.component';
import {MatDialogModule} from "@angular/material/dialog";
import { EditItemDialogComponent } from './items/edit-item-dialog/edit-item-dialog.component';
import { AddItemDialogComponent } from './items/add-item-dialog/add-item-dialog.component';
import {MatIconModule} from "@angular/material/icon";
import { SellItemDialogComponent } from './items/sell-item-dialog/sell-item-dialog.component';
import { UserPageComponent } from './user/user-page/user-page.component';
import { ItemOfferDialogComponent } from './shop/item-offer-dialog/item-offer-dialog.component';
import { SettingsDialogComponent } from './menu/settings-dialog/settings-dialog.component';
import {ActivationGuard} from "./activation-guard";

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    ShopComponent,
    RegistrationComponent,
    LoginComponent,
    MenuComponent,
    EditItemDialogComponent,
    AddItemDialogComponent,
    SellItemDialogComponent,
    UserPageComponent,
    ItemOfferDialogComponent,
    SettingsDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSidenavModule,
    HttpClientModule,
    FormsModule,
    ToastrModule.forRoot(),
    MatGridListModule,
    MatDialogModule,
    MatIconModule
  ],
  providers: [ActivationGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
