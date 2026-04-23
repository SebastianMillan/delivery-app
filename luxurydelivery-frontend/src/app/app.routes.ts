import { Routes } from '@angular/router';
import { Login } from './features/auth/login/login';
import { AdminShell } from './features/admin/components/admin-shell/admin-shell';
import { AdminDashboardPage } from './features/admin/pages/admin-dashboard-page/admin-dashboard-page';
import { AdminEntityPage } from './features/admin/pages/admin-entity-page/admin-entity-page';

export const routes: Routes = [
  { path: 'login', component: Login },
  {
    path: '',
    component: AdminShell,
    children: [
      { path: 'dashboard', component: AdminDashboardPage },
      { path: 'management/:entityKey', component: AdminEntityPage },
      { path: 'products', redirectTo: 'management/product', pathMatch: 'full' },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: 'dashboard' }
];
