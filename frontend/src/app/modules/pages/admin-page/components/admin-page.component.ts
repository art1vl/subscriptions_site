import {Component, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {PaginationComponent, TabsetComponent} from "ngx-bootstrap";
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
  myForm: FormGroup;
  products: ProductModel[] = [];
  companyModel: companyModel[] = [];
  customerModel: customerModel[] = [];
  errorsMapCompany: Map<string, string> = new Map<string, string>();
  companyCreatedFlag: boolean = false;
  totalPagesProduct: number;
  totalProducts: number;
  totalPagesCompanies: number;
  totalCompanies: number;
  totalPagesCustomers: number;
  totalCustomers: number;
  numberOfLastLoadedCustomerPage: number;
  numberOfLastLoadedCompanyPage: number;
  numberOfLastLoadedProductPage: number;
  private customerPaginationFlag = new Subject<boolean>();
  public customerPaginationFlag$ = this.customerPaginationFlag.asObservable();
  private productPaginationFlag = new Subject<boolean>();
  public productPaginationFlag$ = this.productPaginationFlag.asObservable();
  private companyPaginationFlag = new Subject<boolean>();
  public companyPaginationFlag$ = this.companyPaginationFlag.asObservable();

  private subscriptions: Subscription[] = [];

  constructor(private productService: ProductServiceImpl,
              private adminService: AdminServiceImpl,
              private companyService: CompanyServiceImpl,
              private customerService: CustomerServiceImpl,
              private router: Router) {
  }

  ngOnInit() {
    if (this.adminService.admin == null) {
      this.companyService.company = null;
      this.adminService.admin = null;
      this.customerService.customer = null;
      this.router.navigate(["/sign/in"]);
    } else {
      this.loadFirstProductsPageOrReload(0);
      this.loadFirstCompanyPageOrReload(0);
      this.loadFirstCustomerPageOrReload(0);
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

  private loadFirstCompanyPageOrReload(pageNumber: number): void {
    this.subscriptions.push(this.companyService.findAllByPage(pageNumber, 5).subscribe(companies => {
      this.companyModel = companies.companyModelList as companyModel[];
      this.totalPagesCompanies = companies.totalPages;
      this.totalCompanies = companies.totalElements;
      if (this.totalCompanies > 5) {
        this.companyPaginationFlag.next(true);
      }
      this.numberOfLastLoadedCompanyPage = pageNumber;
    }));
  }

  private loadCompanyPage(pagination: PaginationComponent): void {
    this.subscriptions.push(this.companyService.findAllByPage(pagination.page - 1, 5)
      .subscribe(companies => {
        this.companyModel = companies.companyModelList as companyModel[];
        this.totalPagesCompanies = companies.totalPages;
        this.totalCompanies = companies.totalElements;
        this.numberOfLastLoadedCompanyPage = pagination.page - 1;
      }));
  }

  private loadFirstCustomerPageOrReload(pageNumber: number): void {
    this.subscriptions.push(this.customerService.findAllByPage(pageNumber, 5).subscribe(customers => {
      this.customerModel = customers.customerModelList as customerModel[];
      this.totalPagesCustomers = customers.totalPages;
      this.totalCustomers = customers.totalElements;
      if (this.totalCustomers > 5) {
        this.customerPaginationFlag.next(true);
      }
      this.numberOfLastLoadedCustomerPage = pageNumber;
    }));
  }

  private loadCustomerPage(pagination: PaginationComponent): void {
    this.subscriptions.push(this.customerService.findAllByPage(pagination.page - 1, 5)
      .subscribe(customers => {
        this.customerModel = customers.customerModelList as customerModel[];
        this.totalPagesCustomers = customers.totalPages;
        this.totalCustomers = customers.totalElements;
        this.numberOfLastLoadedCustomerPage = pagination.page - 1;
      }));
  }

  private createCompanyAccount(email: string, password: string, companyName: string): void {
    this.errorsMapCompany = new Map<string, string>();
    let newCompany = new companyModel();
    newCompany.email = email;
    newCompany.password = password;
    newCompany.name = companyName;
    newCompany.isActive = 1;
    this.subscriptions.push(this.companyService.saveCompany(newCompany).subscribe(companyOrErrors => {
      if (companyOrErrors.errors != null) {
        this.errorsMapCompany = companyOrErrors.errors;
      } else {
        this.companyCreatedFlag = true;
      }
    }));
  }

  private changeCompanyCreatedFlag(): void {
    this.companyCreatedFlag = false;
    this.myForm.reset();
    this.loadFirstCompanyPageOrReload(this.numberOfLastLoadedCompanyPage)
  }

  private blockOrUnblockProduct(product: ProductModel, newStatus: number): void {
    product.isActive = newStatus;
    this.subscriptions.push(this.productService.changeProductStatus(product).subscribe(productOrErrors => {
      if (productOrErrors.errors == null) {
        for (let i: number = 0; i < this.products.length; i++) {
          if (this.products[i].id == productOrErrors.product.id) {
            this.products[i] = productOrErrors.product as ProductModel;
            break;
          }
        }
      }
    }));
  }

  private blockOrUnblockCompany(company: companyModel, newStatus: number): void {
    company.isActive = newStatus;
    this.subscriptions.push(this.companyService.changeStatus(company).subscribe(companyOrErrors => {
      if (companyOrErrors.errors == null) {
        for (let i: number = 0; i < this.companyModel.length; i++) {
          if (this.companyModel[i].id == companyOrErrors.companyModel.id) {
            this.companyModel[i] = companyOrErrors.companyModel as companyModel;
            break;
          }
        }
        this.loadFirstProductsPageOrReload(this.numberOfLastLoadedProductPage);
      }
    }))
  }

  private blockOrUnblockCustomer(customer: customerModel, newStatus: number): void {
    customer.isActive = newStatus;
    this.subscriptions.push(this.customerService.changeStatus(customer).subscribe(customerOrErrors => {
      if (customerOrErrors.errors == null) {
        for (let i: number = 0; i < this.customerModel.length; i++) {
          if (this.customerModel[i].id == customerOrErrors.customerModel.id) {
            this.customerModel[i] = customerOrErrors.customerModel as customerModel;
            break;
          }
        }
      }
    }))
  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
