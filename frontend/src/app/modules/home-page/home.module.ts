import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {HomeComponent} from "./components/home.component";
import {HeaderModule} from "../header/header.module";
import {FooterModule} from "../footer/footer.module";

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    FooterModule,
    HeaderModule,
    RouterModule
  ],
  providers: [],
  exports: [HomeComponent]
})
export class HomeModule {}
