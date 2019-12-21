import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {TabsModule} from "ng2-bootstrap";
import {PaginationModule} from "ngx-bootstrap";
import {ErrorPageComponent} from "./components/error-page.component";


@NgModule({
  declarations: [
    ErrorPageComponent
  ],
  imports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    TabsModule.forRoot(),
    PaginationModule.forRoot()
  ],
  providers: [],
  exports: [ErrorPageComponent]
})
export class ErrorPageModule {}
