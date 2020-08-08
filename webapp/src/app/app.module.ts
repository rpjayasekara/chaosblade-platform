import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateComponent } from './create/create.component';
import { AddhostComponent } from './addhost/addhost.component';
import { HistoryComponent } from './history/history.component';
import { HostmanagerComponent } from './hostmanager/hostmanager.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateComponent,
    AddhostComponent,
    HistoryComponent,
    HostmanagerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
