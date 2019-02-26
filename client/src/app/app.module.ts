import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {HttpClientModule} from '@angular/common/http';
import {ItemListComponent} from './item-list/item-list.component';
import {CoinSlotComponent} from './coin-slot/coin-slot.component';

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    CoinSlotComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
