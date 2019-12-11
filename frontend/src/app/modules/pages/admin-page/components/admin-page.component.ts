import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {TabsetComponent} from "ngx-bootstrap";
import {subscriptionModel} from "../../../models/subscriptionModel";
import {Subscription} from "rxjs";
import {companyModel} from "../../../models/companyModel";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {customerModel} from "../../../models/customerModel";
import {ProductModel} from "../../../models/productModel";
import {ProductServiceImpl} from "../../../../services/impl/product.service.impl";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";

@Component({
  selector: "app-admin-page",
  templateUrl: "./admin-page.component.html",
  styleUrls: ["./admin-page.component.css"]
})
export class AdminPageComponent implements OnInit, OnDestroy {
  myForm : FormGroup;
  productModel: ProductModel[] = [];
  companyModel: companyModel[] = [];
  customerModel: customerModel[] = [];
  errorsMapCompany: Map<string, string> = new Map<string, string>();
  companyCreatedFlag: boolean = false;
  totalPagesProduct: number;
  totalProducts: number;

  private subscriptions: Subscription[] = [];

  constructor(private productService: ProductServiceImpl,
              private companyService: CompanyServiceImpl,
              private customerService: CustomerServiceImpl){
  }

  ngOnInit() {
    this.loadFirstPageProducts();
   // this.loadCompanies();
    this.myForm = new FormGroup({
      "email": new FormControl("", [
        Validators.required,
        Validators.email
      ]),
      "password": new FormControl("", [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern('^[0-9a-zA-Z]+$')
      ]),
      "repeatPassword": new FormControl("",[
        Validators.required,
        Validators.minLength(8),
        Validators.pattern('^[0-9a-zA-Z]+$'),
      ]),
      "companyName": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z a-z0-9]+$')
      ]),
    }, );
  }

  private loadFirstPageProducts(): void {
    this.subscriptions.push(this.productService.findProducts(0, 8).subscribe(productPageModel => {
      this.productModel = productPageModel.productModelList as ProductModel[];
      this.totalProducts = productPageModel.totalElements;
      this.totalPagesProduct = productPageModel.totalPages;
    }));
  }

  private loadCompanies(): void {
    this.subscriptions.push(this.companyService.findAllCompanies().subscribe(company => {
      this.companyModel = company as companyModel[];
    }));
  }

  private createCompanyAccount(email: string, password: string, companyName: string): void {
    let newCompany = new companyModel();
    newCompany.email = email;
    newCompany.password = password;
    newCompany.name = companyName;
    newCompany.isActive = 1;
    this.subscriptions.push(this.companyService.saveCompany(newCompany).subscribe(companyOrErrors => {
      if (companyOrErrors.errors != null) {
        this.errorsMapCompany = companyOrErrors.errors;
      }
      else {
        this.companyCreatedFlag = true;
      }
    }));
  }

  private changeCompanyCreatedFlag(): void {
    this.companyCreatedFlag = false;
    this.myForm.reset();
  }

  //todo
  private blockOrUnblockProduct(product: ProductModel, number: number): void {

  }

  //todo
  private blockOrUnblockCompany(company: companyModel, number: number): void {

  }

  //todo
  private blockOrUnblockCustomer(customer: customerModel, number: number): void {

  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
