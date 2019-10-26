import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {HeaderModule} from "../header/header.module";
import {FooterModule} from "../footer/footer.module";
import {CatalogComponent} from "./components/catalog.component";
import {ProductModule} from "./product/product.module";

@NgModule({
  declarations: [
    CatalogComponent
  ],
  imports: [
    FooterModule,
    HeaderModule,
    RouterModule,
    ProductModule
  ],
  providers: [],
  exports: [CatalogComponent]
})
export class CatalogModule {}
