import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {PaginationComponent, TabsetComponent} from "ngx-bootstrap";
import {subscriptionModel} from "../../../models/subscriptionModel";
import {Subject, Subscription} from "rxjs";
import {companyModel} from "../../../models/companyModel";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {customerModel} from "../../../models/customerModel";
import {ProductModel} from "../../../models/productModel";
import {ProductServiceImpl} from "../../../../services/impl/product.service.impl";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {AdminServiceImpl} from "../../../../services/impl/admin.service.impl";
import {Router} from "@angular/router";

@Component({
  selector: "app-admin-page",
  templateUrl: "./admin-page.component.html",
  styleUrls: ["./admin-page.component.css"]
})
export class AdminPageComponent implements OnInit, OnDestroy {
  myForm : FormGroup;
  products: ProductModel[] = [];
  companyModel: companyModel[] = [];
  customerModel: customerModel[] = [];
  errorsMapCompany: Map<string, string> = new Map<string, string>();
  companyCreatedFlag: boolean = false;
  totalPagesProduct: number;
  totalProducts: number;
  numberOfLastLoadedProductPage: number;
  private productPaginationFlag = new Subject<boolean>();
  public productPaginationFlag$ = this.productPaginationFlag.asObservable();

  private subscriptions: Subscription[] = [];

  constructor(private productService: ProductServiceImpl,
              private adminService: AdminServiceImpl,
              private companyService: CompanyServiceImpl,
              private customerService: CustomerServiceImpl,
              private router: Router){
  }

  ngOnInit() {
    if (this.adminService.admin == null) {
      this.companyService.company = null;
      this.adminService.admin = null;
      this.customerService.customer = null;
      this.router.navigate(["/sign/in"]);
    }
    else {
      this.loadFirstProductsPageOrReload(0);
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
        "repeatPassword": new FormControl("", [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern('^[0-9a-zA-Z]+$'),
        ]),
        "companyName": new FormControl("", [
          Validators.required,
          Validators.pattern('^[A-Z a-z0-9]+$')
        ]),
      },);
    }
  }

  private loadFirstProductsPageOrReload(pageNumber: number): void {
    this.subscriptions.push(this.productService.findAllProducts(pageNumber, 5).subscribe(products => {
      this.products = products.productModelList as ProductModel[];
      this.totalPagesProduct = products.totalPages;
      this.totalProducts = products.totalElements;
      if (this.totalProducts > 5) {
        this.productPaginationFlag.next(true);
      }
      this.numberOfLastLoadedProductPage = pageNumber;
    }));
  }

  private loadProductPage(pagination: PaginationComponent): void {
    this.subscriptions.push(this.productService.findAllProducts(pagination.page - 1, 5)
      .subscribe(products => {
        this.products = products.productModelList as ProductModel[];
        this.totalPagesProduct = products.totalPages;
        this.totalProducts = products.totalElements;
        this.numberOfLastLoadedProductPage = pagination.page - 1;
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

  private blockOrUnblockProduct(product: ProductModel, newStatus: number): void {
    product.isActive = newStatus;
    this.subscriptions.push(this.productService.changeProductStatus(product).subscribe(productOrErrors => {
      if(productOrErrors.errors == null) {
        for (let i: number = 0; i < this.products.length; i++) {
          if (this.products[i].id == productOrErrors.product.id) {
            this.products[i] = productOrErrors.product as ProductModel;
            break;
          }
        }
      }
    }));
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
