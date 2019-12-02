import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {TabsModule} from "ng2-bootstrap";
import {TextMaskModule} from "angular2-text-mask";
import {SubscriptionComponent} from "./components/subscription.component";

@NgModule({
  declarations: [
    SubscriptionComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    TextMaskModule,
    TabsModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [
  ],
  exports: [SubscriptionComponent]
})
export class SubscriptionModule {}
