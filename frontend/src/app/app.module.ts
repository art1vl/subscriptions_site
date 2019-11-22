import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import { AppComponent } from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
// import {RouterModule} from "@angular/router";
import {RouterModule, Routes} from "@angular/router";
import {SignInComponent} from "./modules/sign-in/components/sign-in.component";
import {HomeComponent} from "./modules/home-page/components/home.component";
import {HeaderModule} from "./modules/header/header.module";
import {HomeModule} from "./modules/home-page/home.module";
import {CatalogComponent} from "./modules/catalog-page/components/catalog.component";
import {CatalogModule} from "./modules/catalog-page/catalog.module";
import {RegistrationPageComponent} from "./modules/registration-page/components/registration-page.component";


const appRoutes: Routes = [
  {path: "", component: HomeComponent},
  {path: "sign_in", component: SignInComponent},
  {path: "catalog", component: CatalogComponent},
  {path: "registration", component: RegistrationPageComponent},
  // {path: "**"}
];

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    RegistrationPageComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HeaderModule,
    HomeModule,
    CatalogModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
