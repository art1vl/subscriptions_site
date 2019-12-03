import { NgModule } from "@angular/core";
import {ProductComponent} from "./components/product.component";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    ProductComponent
  ],
  imports: [
    RouterModule
  ],
  providers: [],
  exports: [ProductComponent]
})
export class ProductModule {}
