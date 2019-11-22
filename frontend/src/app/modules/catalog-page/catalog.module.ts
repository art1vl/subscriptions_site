import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {HeaderModule} from "../header/header.module";
import {FooterModule} from "../footer/footer.module";
import {CatalogComponent} from "./components/catalog.component";
import {ProductModule} from "./product/product.module";
import {CommonModule} from "@angular/common";
import {ProductServiceImpl} from "../../services/impl/product.service.impl";

@NgModule({
  declarations: [
    CatalogComponent
  ],
  imports: [
    FooterModule,
    HeaderModule,
    RouterModule,
    ProductModule,
    CommonModule
  ],
  providers: [ProductServiceImpl],
  exports: [CatalogComponent]
})
export class CatalogModule {}
