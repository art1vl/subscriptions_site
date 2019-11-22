import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {HomeComponent} from "./components/home.component";
import {FooterModule} from "../../footer/footer.module";

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    FooterModule,
    RouterModule
  ],
  providers: [],
  exports: [HomeComponent]
})
export class HomeModule {}
