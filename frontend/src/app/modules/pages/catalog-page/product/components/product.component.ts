import {Component, Input, OnInit} from "@angular/core";
import {ProductModel} from "../../../../models/productModel";

@Component({
  selector: "app-catalog-product",
  templateUrl: "./product.component.html",
  styleUrls: ["./product.component.css"]
})
export class ProductComponent implements OnInit {
  @Input() product: ProductModel;

  constructor( ) {}

  ngOnInit() {

  }
}
