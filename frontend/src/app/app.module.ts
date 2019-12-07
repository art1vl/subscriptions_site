import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import { AppComponent } from "./app.component";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import {SignInComponent} from "./modules/pages/sign-in/components/sign-in.component";
import {HomeComponent} from "./modules/pages/home-page/components/home.component";
import {HeaderModule} from "./modules/header/header.module";
import {HomeModule} from "./modules/pages/home-page/home.module";
import {CatalogComponent} from "./modules/pages/catalog-page/components/catalog.component";
import {CatalogModule} from "./modules/pages/catalog-page/catalog.module";
import {RegistrationPageComponent} from "./modules/pages/registration-page/components/registration-page.component";
import {RegistrationPageModule} from "./modules/pages/registration-page/registration-page.module";
import {SignInModule} from "./modules/pages/sign-in/sign-in.module";
import {ProductPageModule} from "./modules/pages/product-page/product-page.module";
import {ProductPageComponent} from "./modules/pages/product-page/components/product-page.component";
import {CustomerPageComponent} from "./modules/pages/customer-page/components/customer-page.component";
import {CustomerPageModule} from "./modules/pages/customer-page/customer-page.module";
import {AdminPageModule} from "./modules/pages/admin-page/admin-page.module";
import {CompanyPageModule} from "./modules/pages/company-page/company-page.module";
import {TextMaskModule} from "angular2-text-mask";
import {CustomerServiceImpl} from "./services/impl/customer.service.impl";
import {FooterModule} from "./modules/footer/footer.module";
import {CompanyPageComponent} from "./modules/pages/company-page/components/company-page.component";
import {AdminPageComponent} from "./modules/pages/admin-page/components/admin-page.component";
import {WalletServiceImpl} from "./services/impl/wallet-service-impl.service";
import {ProductServiceImpl} from "./services/impl/product.service.impl";
import {AuthInterceptor} from "./services/impl/AuthInterceptor";

const appRoutes: Routes = [
  {path: "", component: HomeComponent},
  {path: "sign_in", component: SignInComponent},
  {path: "catalog", component: CatalogComponent},
  {path: "registration", component: RegistrationPageComponent},
  {path: "catalog/product/:id", component: ProductPageComponent},
  {path: "customer", component: CustomerPageComponent},
  {path: "company", component: CompanyPageComponent},
  {path: "admin", component: AdminPageComponent}
  // {path: "**"}
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RegistrationPageModule,
    ProductPageModule,
    CustomerPageModule,
    AdminPageModule,
    TextMaskModule,
    CompanyPageModule,
    SignInModule,
    HeaderModule,
    HomeModule,
    FooterModule,
    CatalogModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    CustomerServiceImpl,
    HttpClient,
    WalletServiceImpl,
    ProductServiceImpl,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
