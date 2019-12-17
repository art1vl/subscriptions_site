import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {CustomerPageComponent} from "./components/customer-page.component";
import {SubscriptionServiceImpl} from "../../../services/impl/subscription.service.impl";
import {TabsModule} from "ng2-bootstrap";
import {TextMaskModule} from "angular2-text-mask";
import {FooterModule} from "../../footer/footer.module";
import {PaginationModule} from "ngx-bootstrap";

@NgModule({
  declarations: [
    CustomerPageComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    TextMaskModule,
    FooterModule,
    TabsModule.forRoot(),
    PaginationModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [
    SubscriptionServiceImpl,
  ],
  exports: [CustomerPageComponent]
})
export class CustomerPageModule {}
