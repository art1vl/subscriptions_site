import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {FooterModule} from "../../footer/footer.module";
import {CatalogComponent} from "./components/catalog.component";
import {ProductModule} from "./product/product.module";
import {CommonModule} from "@angular/common";
import {ProductServiceImpl} from "../../../services/impl/product.service.impl";
import {ReactiveFormsModule} from "@angular/forms";

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
  providers: [ProductServiceImpl],
  exports: [CatalogComponent]
})
export class CatalogModule {}
