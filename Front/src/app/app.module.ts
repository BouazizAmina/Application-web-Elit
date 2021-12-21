import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { RegistrationComponent } from './registration/registration.component';
import {FormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {OwlModule} from "ngx-owl-carousel";
import { PageprincipaleComponent } from './pageprincipale/pageprincipale.component';
import { DeconnexionComponent } from './deconnexion/deconnexion.component';
import {PanneComponent} from "./panne/panne.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    PageprincipaleComponent,
    DeconnexionComponent,
    PanneComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    OwlModule,
    RouterModule.forRoot([
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'registration',
        component: RegistrationComponent
      },

      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'principale',
        component: PageprincipaleComponent
      },
      {
        path: 'panne',
        component: PanneComponent
      }
    ]),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
