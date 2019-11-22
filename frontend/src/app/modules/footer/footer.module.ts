import { NgModule } from "@angular/core";
import {FooterComponent} from "./components/footer.component";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    FooterComponent
  ],
  imports: [
    RouterModule
  ],
  providers: [],
  exports: [FooterComponent]
})
export class FooterModule {}
