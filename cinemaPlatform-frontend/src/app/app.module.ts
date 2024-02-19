import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CinemaComponent } from './components/cinema/cinema.component';
import { HttpClientModule } from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {CinemaService} from "./services/cinema.service";

@NgModule({
  declarations: [
    AppComponent,
    CinemaComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
  providers: [
      CinemaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
