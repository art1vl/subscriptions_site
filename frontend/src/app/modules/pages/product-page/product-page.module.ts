import { NgModule } from "@angular/core";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule, DatePipe} from "@angular/common";
import {ProductPageComponent} from "./components/product-page.component";
import {ProductServiceImpl} from "../../../services/impl/product.service.impl";
import {FooterModule} from "../../footer/footer.module";


@NgModule({
  declarations: [
    ProductPageComponent
  ],
  imports: [
    FooterModule,
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule
  ],
  providers: [
    ProductServiceImpl,
    DatePipe
  ],
  exports: [ProductPageComponent]
})
export class ProductPageModule {}
