import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddhostComponent } from './addhost/addhost.component';
import { CreateComponent } from './create/create.component';
import { HistoryComponent } from './history/history.component';
import { HostmanagerComponent } from './hostmanager/hostmanager.component';

const routes: Routes = [
  { path: '', component: CreateComponent },
  { path: 'addhost', component: AddhostComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'hostmanager', component: HostmanagerComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
