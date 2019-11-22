import { Component, OnInit } from "@angular/core";
import {ProductModel} from "../../../models/productModel";
import {Subscription} from "rxjs";
import {ProductServiceImpl} from "../../../../services/impl/product.service.impl";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {customerModel} from "../../../models/customerModel";
//import { Location } from '@angular/common';

@Component({
  selector: "app-catalog",
  templateUrl: "./catalog.component.html",
  styleUrls: ["./catalog.component.css"]
})
export class CatalogComponent implements OnInit {
  typeArray: String[] = [];
  products: ProductModel[];
  searchForm: FormGroup;
  customer: customerModel;

  private subscriptions: Subscription[] = [];

  constructor(private ProductService: ProductServiceImpl,
             // private location: Location,
              //this.location.replaceState("/sign_in");
              private loadingService: Ng4LoadingSpinnerService,
              private customerServiceImpl: CustomerServiceImpl) {
    this.searchForm = new FormGroup({

      "productName": new FormControl("", [
        Validators.pattern('^[0-9a-zA-Z]+$'),
      ])})
  }

  // Calls on component init
  ngOnInit() {
    this.loadProduct();
    this.getProductTypes();
    this.customer = this.customerServiceImpl.customer;
  }

  private getProductTypes(): void{
    this.subscriptions.push(this.ProductService.findProductTypes().subscribe(types => {
      // Parse json response into local array
      this.typeArray = types as String[];
      // Check data in console
    //  console.log(this.products);// don't use console.log in angular :)
      this.loadingService.hide();
    }));
  }

  private loadProduct(): void {
    this.loadingService.show();
    // Get data from BillingAccountService
    this.subscriptions.push(this.ProductService.findProducts().subscribe(prod => {
      // Parse json response into local array
      this.products = prod as ProductModel[];
      // Check data in console
     // console.log(this.products);// don't use console.log in angular :)
      this.loadingService.hide();
    }));
  }

}
