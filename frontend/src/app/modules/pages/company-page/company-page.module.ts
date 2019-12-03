import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {CompanyPageComponent} from "./components/company-page.component";
import {TabsModule} from "ng2-bootstrap";
import {TextMaskModule} from "angular2-text-mask";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BsDatepickerModule} from "ngx-bootstrap/datepicker";
import {ProductTypeServiceImpl} from "../../../services/impl/productType.service.impl";

@NgModule({
  declarations: [
    CompanyPageComponent
  ],
  imports: [
    RouterModule,
    ReactiveFormsModule,
    TextMaskModule,
    CommonModule,
    TabsModule.forRoot(),
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
  ],
  providers: [
    ProductTypeServiceImpl
  ],
  exports: [CompanyPageComponent]
})
export class CompanyPageModule {}
