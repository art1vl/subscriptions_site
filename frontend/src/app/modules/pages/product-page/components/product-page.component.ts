import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs";
import {ProductModel} from "../../../models/productModel";
import {ProductServiceImpl} from "../../../../services/impl/product.service.impl";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {customerModel} from "../../../models/customerModel";
import {companyModel} from "../../../models/companyModel";
import {adminModel} from "../../../models/adminModel";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {AdminServiceImpl} from "../../../../services/impl/admin.service.impl";

@Component({
  selector: "app-product-page",
  templateUrl: "./product-page.component.html",
  styleUrls: ["./product-page.component.css"]
})
export class ProductPageComponent implements OnInit, OnDestroy {
  product: ProductModel;
  productId: string;
  formatDate: string;
  customer: customerModel;
  subscriptionFlag: boolean;
  company: companyModel;
  admin: adminModel;

  private subscriptions: Subscription[] = [];

  constructor(private productService: ProductServiceImpl,
              private customerServiceImpl: CustomerServiceImpl,
              private router: Router,
              public datePipe: DatePipe,
              private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl) {
  }

  ngOnInit() {
    this.productId = this.router.url.substring(this.router.url.lastIndexOf('/') + 1);
    this.loadProduct();
    this.customer = this.customerServiceImpl.customer;
    this.company = this.companyService.company;
    this.admin = this.adminService.admin;
  }

  private loadProduct(): void {
    this.subscriptions.push(this.productService.findProductById(this.productId).subscribe(prod => {
      this.product = prod as ProductModel;
      this.formatDate = this.datePipe.transform(this.product.realiseDate, 'dd-MM-yyyy');
    }));
  }

  //todo
  private subscribe(): void{
    if (this.customer == null) {
      this.router.navigate(["sign_in"]);
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
