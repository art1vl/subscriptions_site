import {AfterViewChecked, ChangeDetectorRef, Component, OnDestroy, OnInit} from "@angular/core";
import {Subject, Subscription} from "rxjs";
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
import {SubscriptionServiceImpl} from "../../../../services/impl/subscription.service.impl";
import {subscriptionModel} from "../../../models/subscriptionModel";

@Component({
  selector: "app-product-page",
  templateUrl: "./product-page.component.html",
  styleUrls: ["./product-page.component.css"]
})
export class ProductPageComponent implements OnInit, OnDestroy, AfterViewChecked {
  product: ProductModel;
  productId: string;
  formatDate: string;
  customer: customerModel;
  // subscriptionFlag: boolean;
  company: companyModel;
  admin: adminModel;
  subscription: subscriptionModel;
  errors: Map<string, string> = new Map<string, string>();
  private subject = new Subject<boolean>();
  public subject$ = this.subject.asObservable();

  private subscriptions: Subscription[] = [];

  constructor(private productService: ProductServiceImpl,
              private customerServiceImpl: CustomerServiceImpl,
              private subscriptionService: SubscriptionServiceImpl,
              private router: Router,
              public datePipe: DatePipe,
              private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl,
              private cdr: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.productId = this.router.url.substring(this.router.url.lastIndexOf('/') + 1);
    this.customer = this.customerServiceImpl.customer;
    this.company = this.companyService.company;
    this.admin = this.adminService.admin;
    this.loadProduct();
  }

  private isCustomerSubscribed(): void {
    this.subscriptions.push(this.subscriptionService.findSubscription(this.product.id, this.customer.id).subscribe(subscription => {
      this.subscription = subscription as subscriptionModel;
      console.log(this.subscription);
      if (this.subscription == null) {
        this.subject.next(false);
      }
      else {
        this.subject.next(true);
      }
    }));
  }

  private loadProduct(): void {
    this.subscriptions.push(this.productService.findProductById(this.productId).subscribe(prod => {
      this.product = prod as ProductModel;
      this.formatDate = this.datePipe.transform(this.product.realiseDate, 'dd-MM-yyyy');
      if (this.customer != null) {
        this.isCustomerSubscribed();
      } else {
        // this.subscriptionFlag = false;
        this.subject.next(false);
      }
    }));
  }

  private subscribe(): void {
    if (this.customer == null) {
      this.router.navigate(["sign/in"]);
    } else {
      let subscription: subscriptionModel = new subscriptionModel();
      subscription.isActive = 1;
      subscription.idCustomer = this.customer.id;
      subscription.product = this.product;
      subscription.startSubscriptionDate = new Date();
      this.subscriptions.push(this.subscriptionService.createNewSubscription(subscription).subscribe(subscriptionOrErrors => {
        if (subscriptionOrErrors.errors == null) {
          this.subscription = subscriptionOrErrors.subscriptionModel as subscriptionModel;
          this.subject.next(true);
        } else {
          this.subscription = null;
          this.subject.next(false);
          this.errors = subscriptionOrErrors.errors;
        }
      }))
    }
  }

  private unsubscribe(): void {
    this.subscriptions.push(this.subscriptionService.deleteSubscription(this.subscription.id).subscribe(() => {
      this.subject.next(false);
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  ngAfterViewChecked(): void {
    this.cdr.detectChanges();

  }
}
