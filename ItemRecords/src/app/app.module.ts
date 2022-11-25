import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AddItemFormComponent} from './items/add-item-form/add-item-form.component';
import {ItemListComponent} from './items/item-list/item-list.component';
import {HomePageComponent} from './home-page/home-page.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {ShopComponent} from './shop/shop.component';
import {UserLoginComponent} from './user/user-login/user-login.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {AddUserFormComponent} from './user/add-user-form/add-user-form.component';
import {ToastrModule} from "ngx-toastr";
import {HttpClientModule} from "@angular/common/http";
import {MatGridListModule} from "@angular/material/grid-list";

@NgModule({
  declarations: [
    AppComponent,
    AddItemFormComponent,
    ItemListComponent,
    HomePageComponent,
    ShopComponent,
    UserLoginComponent,
    AddUserFormComponent,
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
        MatGridListModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
