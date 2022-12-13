import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ItemListComponent} from "./items/item-list/item-list.component";
import {ShopComponent} from "./shop/shop.component";
import {AddUserFormComponent} from "./user/add-user-form/add-user-form.component";
import {LoginComponent} from "./user/login/login.component";
import {UserPageComponent} from "./user/user-page/user-page.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LoginComponent},
  {path: 'register', component: AddUserFormComponent},
  {path: 'items', component: ItemListComponent},
  {path: 'shop', component: ShopComponent},
  {path: 'user/:userId', component: UserPageComponent}
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
