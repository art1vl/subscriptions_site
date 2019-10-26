import { Component, OnInit } from "@angular/core";
import {Product} from "../../../models/product-page/product";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalService} from "ngx-bootstrap";
import {ProductService} from "../../../../services/product.service";
import {Subscription} from "rxjs";

@Component({
  selector: "app-catalog-product",
  templateUrl: "./product.component.html",
  styleUrls: ["./product.component.css"]
})
export class ProductComponent implements OnInit {
  public products: Product[];

  private subscriptions: Subscription[] = [];

  constructor(private ProductService: ProductService,
              private loadingService: Ng4LoadingSpinnerService,
              private modalService: BsModalService ) {}

  // Calls on component init
  ngOnInit() {
    this.loadProduct();
  }

  private loadProduct(): void {
    this.loadingService.show();
    // Get data from BillingAccountService
    this.subscriptions.push(this.ProductService.getProducts().subscribe(prod => {
      // Parse json response into local array
      this.products = prod as Product[];
      // Check data in console
      console.log(this.products);// don't use console.log in angular :)
      this.loadingService.hide();
    }));
  }

}
