import {Component, OnDestroy, OnInit} from "@angular/core";
import {ProductModel} from "../../../models/productModel";
import {Subscription} from "rxjs";
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
      "productName": new FormControl("", [
        Validators.pattern('^[0-9a-zA-Z]+$'),
      ])
    })
  }

  private getProductTypes(): void {
    this.subscriptions.push(this.productTypeService.findTypes().subscribe(types => {
      this.typeArray = types as ProductTypeModel[];
    }));
  }

  private loadFirstPageProduct(): void {
    this.subscriptions.push(this.ProductService.findProductsIsActive(0, 8).subscribe(productPageModel => {
      this.products = productPageModel.productModelList as ProductModel[];
      this.totalProducts = productPageModel.totalElements;
      this.totalPages = productPageModel.totalPages;
    }));
  }

  private loadNewPageProduct(pagination: PaginationComponent): void {
    this.subscriptions.push(this.ProductService.findProductsIsActive(pagination.page - 1, 8).subscribe(productPageModel => {
      this.products = productPageModel.productModelList as ProductModel[];
      this.totalProducts = productPageModel.totalElements;
      this.totalPages = productPageModel.totalPages;
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
