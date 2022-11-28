import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ItemListComponent} from "./items/item-list/item-list.component";
import {AddItemFormComponent} from "./items/add-item-form/add-item-form.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {ShopComponent} from "./shop/shop.component";
import {AddUserFormComponent} from "./user/add-user-form/add-user-form.component";
import {LoginComponent} from "./user/login/login.component";

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LoginComponent},
  {path: 'register', component: AddUserFormComponent},
  {path: 'items', component: ItemListComponent},
  {path: 'item/add', component: AddItemFormComponent},
  {path: 'item/edit/:itemId', component: AddItemFormComponent},
  {path: 'shop', component: ShopComponent}
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
