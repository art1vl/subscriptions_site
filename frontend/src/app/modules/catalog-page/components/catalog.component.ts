import { Component, OnInit } from "@angular/core";
import {Product} from "../../models/catalog-page/product";
import {Subscription} from "rxjs";
import {ProductServiceImpl} from "../../../services/impl/product.service.impl";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalService} from "ngx-bootstrap";

@Component({
  selector: "app-catalog",
  templateUrl: "./catalog.component.html",
  styleUrls: ["./catalog.component.css"]
})
export class CatalogComponent implements OnInit {
  public products: Product[];

  private subscriptions: Subscription[] = [];

  constructor(private ProductService: ProductServiceImpl,
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
