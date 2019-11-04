import {Component, Input, OnInit} from "@angular/core";
import {Product} from "../../../models/catalog-page/product";

@Component({
  selector: "app-catalog-product",
  templateUrl: "./product.component.html",
  styleUrls: ["./product.component.css"]
})
export class ProductComponent implements OnInit {
  @Input() product: Product;

  constructor( ) {}

  ngOnInit() {

  }
}
