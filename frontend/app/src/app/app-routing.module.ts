import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CardComponent} from './pages/card/card.component';
import {LoginComponent} from './pages/login/login.component';
import {SignupComponent} from './pages/signup/signup.component';
import {DetailComponent} from './pages/item-detail/detail.component';
import {CartComponent} from './pages/cart/cart.component';
import {AuthGuard} from './_guards/auth.guard';
import {OrderComponent} from './pages/order/order.component';
import {OrderDetailComponent} from './pages/order-detail/order-detail.component';
import {ItemListComponent} from './pages/item-list/item.list.component';
import {UserDetailComponent} from './pages/user-edit/user-detail.component';
import {ItemEditComponent} from './pages/item-edit/item-edit.component';
import {Role} from './enum/Role';

const routes: Routes = [
    {path: '', redirectTo: '/item', pathMatch: 'full'},
    {path: 'item/:id', component: DetailComponent},
    {path: 'category/:id', component: CardComponent},
    {path: 'item', component: CardComponent},
    {path: 'category', component: CardComponent},
    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LoginComponent},
    {path: 'register', component: SignupComponent},
    {path: 'cart', component: CartComponent},
    {path: 'success', component: SignupComponent},
    {path: 'order/:id', component: OrderDetailComponent, canActivate: [AuthGuard]},
    {path: 'order', component: OrderComponent, canActivate: [AuthGuard]},
    {path: 'seller', redirectTo: 'seller/item', pathMatch: 'full'},
    {
        path: 'seller/item',
        component: ItemListComponent,
        canActivate: [AuthGuard],
        data: {roles: [Role.Manager, Role.Employee]}
    },
    {
        path: 'profile',
        component: UserDetailComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'seller/item/:id/edit',
        component: ItemEditComponent,
        canActivate: [AuthGuard],
        data: {roles: [Role.Manager, Role.Employee]}
    },
    {
        path: 'seller/item/:id/new',
        component: ItemEditComponent,
        canActivate: [AuthGuard],
        data: {roles: [Role.Employee]}
    },

];

@NgModule({
    declarations: [],
    imports: [
        RouterModule.forRoot(routes)// {onSameUrlNavigation: 'reload'}
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
