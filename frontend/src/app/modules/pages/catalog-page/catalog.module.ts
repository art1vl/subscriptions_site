import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {FooterModule} from "../../footer/footer.module";
import {CatalogComponent} from "./components/catalog.component";
import {ProductModule} from "./product/product.module";
import {CommonModule} from "@angular/common";
import {ProductServiceImpl} from "../../../services/impl/product.service.impl";
import {ReactiveFormsModule} from "@angular/forms";
import {ProductTypeServiceImpl} from "../../../services/impl/productType.service.impl";

@NgModule({
  declarations: [
    CatalogComponent
  ],
  imports: [
    FooterModule,
    RouterModule,
    ProductModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [
    ProductServiceImpl,
    ProductTypeServiceImpl
  ],
  exports: [CatalogComponent]
})
export class CatalogModule {}
