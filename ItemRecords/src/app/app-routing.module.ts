import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ItemListComponent} from "./items/item-list/item-list.component";
import {ShopComponent} from "./shop/shop.component";
import {RegistrationComponent} from "./user/registration/registration.component";
import {LoginComponent} from "./user/login/login.component";
import {UserPageComponent} from "./user/user-page/user-page.component";
import {ActivationGuard} from "./activation-guard";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'items', component: ItemListComponent, canActivate: [ActivationGuard]},
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
