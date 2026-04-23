import { Routes } from '@angular/router';
import { Login } from './features/auth/login/login';
import { ProductsPage } from './features/products/pages/products-page/products-page';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'products', component: ProductsPage },
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: '**', redirectTo: 'products' },
];
