import {Component, OnDestroy, OnInit} from "@angular/core";
import {ProductModel} from "../../../models/productModel";
import {Subject, Subscription} from "rxjs";
import {ProductServiceImpl} from "../../../../services/impl/product.service.impl";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {customerModel} from "../../../models/customerModel";
import {ProductTypeServiceImpl} from "../../../../services/impl/productType.service.impl";
import {ProductTypeModel} from "../../../models/productTypeModel";
import {PaginationComponent} from "ng2-bootstrap";

@Component({
  selector: "app-catalog",
  templateUrl: "./catalog.component.html",
  styleUrls: ["./catalog.component.css"]
})
export class CatalogComponent implements OnInit, OnDestroy {
  typeArray: ProductTypeModel[];
  products: ProductModel[];
  searchForm: FormGroup;
  customer: customerModel;
  totalPages: number;
  totalProducts: number;
  product: string;
  company: string;
  min: number = 0;
  max: number = 99999999;
  type: string;
  paramSearch: Map<string, string> = new Map<string, string>();
  private cost = new Subject<boolean>();
  public cost$ = this.cost.asObservable();
  private emptyProductsList = new Subject<boolean>();
  public emptyProductsList$ = this.emptyProductsList.asObservable();
  private paginationFlag = new Subject<boolean>();
  public paginationFlag$ = this.paginationFlag.asObservable();

  private subscriptions: Subscription[] = [];

  constructor(private ProductService: ProductServiceImpl,
              private loadingService: Ng4LoadingSpinnerService,
              private customerServiceImpl: CustomerServiceImpl,
              private productTypeService: ProductTypeServiceImpl) {
  }

  ngOnInit() {
    this.loadFirstPageProduct();
    this.getProductTypes();
    this.customer = this.customerServiceImpl.customer;
    this.searchForm = new FormGroup({
      "product": new FormControl("", [
        Validators.pattern('^[A-Z a-z0-9]+$'),
      ]),
      "company": new FormControl("", [
        Validators.pattern('^[A-Z a-z0-9]+$'),
      ]),
      "min": new FormControl("", [
        Validators.pattern('^[1-9]{1}|[1-9][0-9]+$'),
      ]),
      "max": new FormControl("", [
        Validators.pattern('^[1-9]{1}|[1-9][0-9]+$'),
      ])
    })
  }

  private loadFirstPageProduct(): void {
    this.subscriptions.push(this.ProductService.findProductsIsActive(0, 8).subscribe(productPageModel => {
      this.products = productPageModel.productModelList as ProductModel[];
      this.totalProducts = productPageModel.totalElements;
      this.totalPages = productPageModel.totalPages;
      if (this.totalProducts > 8) {
        this.paginationFlag.next(true);
      }
    }));
  }

  private loadNewPageProduct(pagination: PaginationComponent): void {
    this.subscriptions.push(this.ProductService.findProductsIsActive(pagination.page - 1, 8).subscribe(productPageModel => {
      this.products = productPageModel.productModelList as ProductModel[];
      this.totalProducts = productPageModel.totalElements;
      this.totalPages = productPageModel.totalPages;
    }));
  }

  private getProductTypes(): void {
    this.typeArray = new Array();
    this.subscriptions.push(this.productTypeService.findTypes().subscribe(types => {
      this.typeArray.push(new ProductTypeModel(1000000, "all"));
      for (let i: number = 0; i < types.length; i++) {
        this.typeArray.push(types[i]);
      }
    }));
  }

  private search(product: string, company: string, min: string, max: string, type: string): void {
    this.paramSearch = new Map<string, string>();
    this.min = 0;
    this.max = 99999999;
    if (max != "") {
      this.max = +max;
    }
    if (min != "") {
      this.min = +min;
    }
    if (this.min > this.max) {
      this.cost.next(true);
    } else {
      this.paginationFlag.next(false);
      this.emptyProductsList.next(false);
      this.cost.next(false);
      if (product != "") {
        this.paramSearch.set("product", product);
      }
      if (company != "") {
        this.paramSearch.set("company", company);
      }
      if (this.min != 0) {
        this.paramSearch.set("min", this.min.toString());
      }
      if (this.max != 99999999) {
        this.paramSearch.set("max", this.max.toString());
      }
      if (type != "all") {
        this.paramSearch.set("type", type);
      }
      this.subscriptions.push(this.ProductService.searchProductsByPage(this.paramSearch, 0, 8).subscribe(
        productPageModel => {
          this.products = productPageModel.productModelList as ProductModel[];
          this.totalProducts = productPageModel.totalElements;
          this.totalPages = productPageModel.totalPages;
          if (this.totalProducts > 8) {
            this.paginationFlag.next(true);
          }
          if (this.products.length == 0) {
            this.emptyProductsList.next(true);
          }
        }
      ))
    }
  }

  private reset(): void {
    this.paramSearch = new Map<string, string>();
    this.min = 0;
    this.max = 99999999;
    this.searchForm.reset();
    this.loadFirstPageProduct();
    this.getProductTypes();
    this.emptyProductsList.next(false);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
