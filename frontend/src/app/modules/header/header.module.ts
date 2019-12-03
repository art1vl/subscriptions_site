import { NgModule } from "@angular/core";
import {HeaderComponent} from "./components/header.component";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  providers: [],
  exports: [HeaderComponent]
})
export class HeaderModule {}
