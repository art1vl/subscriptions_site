import {Component, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {Subject, Subscription} from "rxjs";
import {PaginationComponent, TabsetComponent} from "ngx-bootstrap";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {WalletModel} from "../../../models/walletModel";
import {companyModel} from "../../../models/companyModel";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {WalletServiceImpl} from "../../../../services/impl/wallet-service-impl.service";
import {ProductTypeModel} from "../../../models/productTypeModel";
import {ProductTypeServiceImpl} from "../../../../services/impl/productType.service.impl";
import {ProductServiceImpl} from "../../../../services/impl/product.service.impl";
import {ProductModel} from "../../../models/productModel";
import {Router} from "@angular/router";
import {AdminServiceImpl} from "../../../../services/impl/admin.service.impl";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";

@Component({
  selector: "app-company-page",
  templateUrl: "./company-page.component.html",
  styleUrls: ["./company-page.component.css"]
})
export class CompanyPageComponent implements OnInit, OnDestroy {
  public myNumber = '';
  public myDate = '';
  public myCvv = '';
  public numberMask = [/\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/];
  public dateMask = [/\d/, /\d/, '/', /\d/, /\d/];
  public cvvMask = [/\d/, /\d/, /\d/];
  minDate: Date;
  maxDate: Date;

  selectedFiles: FileList;
  typeArray: ProductTypeModel[];
  company: companyModel;
  productFlag: boolean = false;
  walletForm: FormGroup;
  productForm: FormGroup;
  withdrawForm: FormGroup;
  errorsMapWallet: Map<string, string> = new Map<string, string>();
  errorsMapProduct: Map<string, string> = new Map<string, string>();
  hiddenCardNumber: string;
  cardDateString: string;
  products: ProductModel[];
  totalPage: number;
  totalElements: number;
  numberOfLastLoadedPage: number;
  balance: number = 0;
  withdrawFlag: boolean = true;
  private balanceFlag = new Subject<boolean>();
  public balanceFlag$ = this.balanceFlag.asObservable();
  private withDrawFlag = new Subject<boolean>();
  public withDrawFlag$ = this.withDrawFlag.asObservable();
  private paginationFlag = new Subject<boolean>();
  public paginationFlag$ = this.paginationFlag.asObservable();
  private productsEmptyFlag = new Subject<boolean>();
  public productsEmptyFlag$ = this.productsEmptyFlag.asObservable();
  private companyWalletExistFlag = new Subject<boolean>();
  public companyWalletExistFlag$ = this.companyWalletExistFlag.asObservable();
  private deleteWalletFlag = new Subject<boolean>();
  public deleteWalletFlag$ = this.deleteWalletFlag.asObservable();

  private subscriptions: Subscription[] = [];

  constructor(private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl,
              private customerService: CustomerServiceImpl,
              private walletServiceImpl: WalletServiceImpl,
              private productTypeService: ProductTypeServiceImpl,
              private productServiceImpl: ProductServiceImpl,
              private router: Router) {
    this.productForm = new FormGroup({
      "date": new FormControl("", [
        Validators.required,
      ]),
      "name": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z a-z0-9]+$')
      ]),
      "description": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z \'\\-.,:;a-z0-9]+$'),
        Validators.maxLength(500),
      ]),
      "cost": new FormControl("", [
        Validators.required,
        Validators.pattern('^[1-9][0-9]+$'),
      ])
    });
    this.walletForm = new FormGroup({
      "cardNumber": new FormControl("", [
        Validators.required,
        Validators.pattern('^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$')
      ]),
      "cardDate": new FormControl("", [
        Validators.required,
        Validators.pattern('^(0[1-9]|1[012])/(20|21|22|23)$')
      ]),
      "cardCvv": new FormControl("", [
        Validators.required,
        Validators.pattern('^\\d{3}$')
      ]),
      "cardHolderName": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z]+\\s[A-Z]+$')
      ])
    },);
    this.withdrawForm = new FormGroup({
      "number": new FormControl("", [
        Validators.required,
        Validators.pattern('^[1-9]{1}[0-9]*$'),
      ])
    })
  }

  ngOnInit() {
    if (this.companyService.company == null || this.companyService.company.isActive == 0) {
      localStorage.clear();
      this.companyService.company = null;
      this.adminService.admin = null;
      this.customerService.customer = null;
      this.router.navigate(["/sign/in"]);
    }
    else {
      this.subscriptions.push(this.companyService.findCompanyById(this.companyService.company.id).subscribe( company => {
        this.company = company as companyModel;
        if (this.company.wallet != null) {
          if (this.company.wallet.cardNumber != 0) {
            this.companyWalletExistFlag.next(true);
            this.hiddenCardNumber = '**** **** **** ' + this.company.wallet.cardNumber.toString().substring(this.company.wallet.cardNumber.toString().length - 4);
            let stringDate: string = new Date(this.company.wallet.cardDate).toLocaleDateString();
            this.cardDateString = stringDate.substring(3, 5) + "/" + stringDate.substring(8);
            this.balance = this.company.wallet.balance;
            this.balanceFlag.next(true);
          }
          else {
            this.companyWalletExistFlag.next(false);
          }
        }
        else {
          this.companyWalletExistFlag.next(false);
        }
        this.companyService.company = company;
        this.paginationFlag.next(false);
        this.loadProductsFirstPageOrReload(0);
      }));
      this.withDrawFlag.next(false);
      this.deleteWalletFlag.next(false);
      this.minDate = new Date();
      this.maxDate = new Date();
      this.minDate.setDate(this.minDate.getDate() - 1090);
      this.maxDate.setDate(this.maxDate.getDate());
      this.getProductTypes();
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  private getProductTypes(): void {
    this.subscriptions.push(this.productTypeService.findTypes().subscribe(types => {
      this.typeArray = types as ProductTypeModel[];
    }));
  }

  private loadProductsFirstPageOrReload(pageNumber: number): void {
    this.subscriptions.push(this.productServiceImpl.findAllProductsByCompanyId(this.company.id, pageNumber, 5)
      .subscribe(products => {
        this.products = products.productModelList as ProductModel[];
        this.totalPage = products.totalPages;
        this.totalElements = products.totalElements;
        if (this.totalElements > 5) {
          this.paginationFlag.next(true);
        }
        if (this.products.length != 0) {
          this.productsEmptyFlag.next(true);
        }
        else {
          this.productsEmptyFlag.next(false);
        }
        this.numberOfLastLoadedPage = pageNumber;
      }));
  }

  private loadProductPage(pagination: PaginationComponent): void {
    this.subscriptions.push(this.productServiceImpl.findAllProductsByCompanyId(this.company.id, pagination.page - 1, 5)
      .subscribe(products => {
        this.products = products.productModelList as ProductModel[];
        this.totalPage = products.totalPages;
        this.totalElements = products.totalElements;
        this.numberOfLastLoadedPage = pagination.page - 1;
      }));
  }

  private deleteProduct(productId: number): void {
    this.subscriptions.push(this.productServiceImpl.deleteProductById(productId).subscribe(() => {
      if (this.products.length == 1) {
        if (this.numberOfLastLoadedPage == 0) {
          this.productsEmptyFlag.next(false);
        }
        else {
          this.numberOfLastLoadedPage--;
        }
      }
      this.loadProductsFirstPageOrReload(this.numberOfLastLoadedPage);
    }));
  }

  private saveWallet(cardNumber: string, cardDate: string, cardCvv: string, cardHolderName: string): void {
    if (this.company.wallet == null) {
      this.company.wallet = new WalletModel();
    }
    this.company = {
      ...this.company,
      wallet: {
        ...this.company.wallet,
        cardNumber: +cardNumber.replace(/\s/g, ''),
        cardDate: new Date(cardDate.substring(0, 2) + "/01/20" + cardDate.substring(3)),
        cardCvvCode: +cardCvv,
        personName: cardHolderName
      }
    };
    this.subscriptions.push(this.companyService.saveCompanyWallet(this.company).subscribe(companyOrErrors => {
      if (companyOrErrors.errors == null) {
        this.company = companyOrErrors.companyModel as companyModel;
        this.companyService.company = this.company;
        this.errorsMapWallet = new Map<string, string>();
        this.hiddenCardNumber = '**** **** **** ' + cardNumber.substring(cardNumber.length - 4);
        this.cardDateString = cardDate;
        this.companyWalletExistFlag.next(true);
      } else {
        this.errorsMapWallet = companyOrErrors.errors;
        this.company = this.companyService.company;
      }
    }));
  }

  private deleteCard(): void {
    if (this.products.length != 0) {
      this.deleteWalletFlag.next(true);
    }
    else {
      this.subscriptions.push(this.walletServiceImpl.deleteCard(this.company.wallet.idWallet).subscribe(() => {
        this.company.wallet.cardDate = null;
        this.company.wallet.cardNumber = null;
        this.company.wallet.personName = null;
        this.company.wallet.cardCvvCode = null;
        this.companyService.company = this.company;
        this.companyWalletExistFlag.next(false);
      }));
      this.deleteWalletFlag.next(false);
      this.companyWalletExistFlag.next(false);
    }
  }

  private withdrawCard(number: number): void {
    if (this.company.wallet.balance - number < 0) {
      this.withdrawFlag = false;
    }
    else {
      this.withdrawFlag = true;
      this.company.wallet.balance -= +number;
      this.subscriptions.push(this.walletServiceImpl.updateCard(this.company.wallet).subscribe(walletOrErrors => {
        if (walletOrErrors.errors == null) {
          this.companyService.company = this.company;
          this.errorsMapWallet = new Map<string, string>();
          this.withDrawFlag.next(true);
          this.balance = this.company.wallet.balance;
          this.balanceFlag.next(true);
        } else {
          this.errorsMapWallet = walletOrErrors.errors;
          this.company = this.companyService.company;
        }
      }));
    }
  }

  changeWithdrawFlag(): void {
    this.withdrawForm.reset();
    this.withDrawFlag.next(false);
  }

  private createProduct(name: string, date: string, description: string, cost: number, typeIndex: number): void {
    let product: ProductModel = new ProductModel();
    if (this.selectedFiles.item(0) == null) {
      this.errorsMapProduct.set("file", "Select product image");
    } else {
      let image: File = this.selectedFiles.item(0);
      product.productName = name;
      product.realiseDate = new Date(date);
      product.description = description;
      product.cost = cost;
      product.type = this.typeArray[typeIndex];
      product.companyId = this.company.id;
      product.companyName = this.company.name;
      product.isActive = 1;
      console.log(product);
      this.subscriptions.push(this.productServiceImpl.saveProduct(product).subscribe(productOrErrors => {
        if (productOrErrors.errors != null) {
          this.errorsMapProduct = productOrErrors.errors;
        } else {
          this.errorsMapProduct = new Map<string, string>();
          console.log("entry");
          let createdProduct: ProductModel = productOrErrors.product as ProductModel;
          console.log(createdProduct);
          this.subscriptions.push(this.productServiceImpl.saveProductImageByProductId(createdProduct.id, image).subscribe(productOrErrors => {
            if (productOrErrors.errors != null) {
              this.errorsMapProduct = productOrErrors.errors;
            } else {
              this.productFlag = true;
              this.loadProductsFirstPageOrReload(this.numberOfLastLoadedPage);
            }
          }));
        }
      }));
    }
  }

  private changeProductCreatedFlag(): void {
    this.productFlag = false;
    this.productForm.reset();
    this.selectedFiles = null;
  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
