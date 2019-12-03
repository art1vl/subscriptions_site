import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {AdminPageComponent} from "./components/admin-page.component";
import {TabsModule} from "ng2-bootstrap";


@NgModule({
  declarations: [
    AdminPageComponent
  ],
  imports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    TabsModule.forRoot(),
  ],
  providers: [],
  exports: [AdminPageComponent]
})
export class AdminPageModule {}
