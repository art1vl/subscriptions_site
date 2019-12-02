import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {CompanyPageComponent} from "./components/company-page.component";
import {TabsModule} from "ng2-bootstrap";

@NgModule({
  declarations: [
    CompanyPageComponent
  ],
  imports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    TabsModule.forRoot(),
  ],
  providers: [],
  exports: [CompanyPageComponent]
})
export class CompanyPageModule {}
