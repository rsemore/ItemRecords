import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ItemListComponent} from "./items/item-list/item-list.component";
import {AddItemFormComponent} from "./items/add-item-form/add-item-form.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {ShopComponent} from "./shop/shop.component";
import {UserLoginComponent} from "./user/user-login/user-login.component";

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'items', component: ItemListComponent},
  {path: 'item/add', component: AddItemFormComponent},
  {path: 'shop', component: ShopComponent},
  {path: 'login', component: UserLoginComponent}
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
