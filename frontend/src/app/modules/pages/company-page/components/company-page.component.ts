import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {Subscription} from "rxjs";
import {TabsetComponent} from "ngx-bootstrap";
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

@Component({
  selector: "app-company-page",
  templateUrl: "./company-page.component.html",
  styleUrls: ["./company-page.component.css"]
})
export class CompanyPageComponent implements OnInit, OnDestroy {
  @ViewChild('name') name: ElementRef;
  @ViewChild('date') date: ElementRef;
  @ViewChild('description') description: ElementRef;
  @ViewChild('file') file: ElementRef;
  @ViewChild('cost') cost: ElementRef;

  public myNumber = '';
  public myDate = '';
  public myCvv = '';
  public numberMask = [/\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/];
  public dateMask = [ /\d/, /\d/, '/', /\d/, /\d/];
  public cvvMask = [ /\d/, /\d/, /\d/];
  minDate: Date;
  maxDate: Date;

  selectedFiles: FileList;
  typeArray: ProductTypeModel[];
  company: companyModel;
  walletFlag: boolean;
  productFlag: boolean = false;
  walletForm: FormGroup;
  productForm: FormGroup;
  errorsMapWallet: Map<string, string> = new Map<string, string>();
  errorsMapProduct: Map<string,string> = new Map<string, string>();
  hiddenCardNumber: string;
  cardDateString: string;

  private subscriptions: Subscription[] = [];

  constructor(private companyService: CompanyServiceImpl,
              private walletServiceImpl: WalletServiceImpl,
              private productTypeService: ProductTypeServiceImpl,
              private productServiceImpl: ProductServiceImpl,
              private router: Router){
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
        Validators.pattern('^[A-Z .,:;a-z0-9]+$'),
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
  }

  ngOnInit() {
    if (this.companyService.company == null) {
      this.router.navigate(["/sign_in"]);
    }
    else {
      this.company = this.companyService.company;
      if (this.company.wallet != null) {
        if (this.company.wallet.cardCvvCode != 0) {
          this.walletFlag = true;
        }
      }
      else {
        this.walletFlag = false;
      }
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

  private getProductTypes(): void{
    this.subscriptions.push(this.productTypeService.findTypes().subscribe(types => {
      this.typeArray = types as ProductTypeModel[];
    }));
  }

  private saveWallet(cardNumber: string, cardDate: string, cardCvv: string, cardHolderName: string): void {
    if (this.company.wallet == null) {
      this.company.wallet = new WalletModel();
    }
    this.company.wallet.cardNumber = +cardNumber.replace(/\s/g, '');
    this.company.wallet.cardDate = new Date(cardDate);
    this.company.wallet.cardCvvCode = +cardCvv;
    this.company.wallet.personName = cardHolderName;
    this.company.password = "11111111";
    this.subscriptions.push(this.companyService.saveCompanyWallet(this.company).subscribe( companyOrErrors => {
      if (companyOrErrors.errors == null) {
        this.company = companyOrErrors.companyModel as companyModel;
        this.companyService.company = this.company;
        this.errorsMapWallet.clear();
        this.hiddenCardNumber = '**** **** ****' + cardNumber.substring(cardNumber.length - 4);
        this.cardDateString = cardDate;
        this.walletFlag = true;
      }
      else {
        this.errorsMapWallet = companyOrErrors.errors;
        this.company = this.companyService.company;
      }
    }));
  }

  private deleteCard(): void {
    this.company.wallet.cardDate = null;
    this.company.wallet.cardNumber = null;
    this.company.wallet.personName = null;
    this.company.wallet.cardCvvCode = null;
    this.subscriptions.push(this.walletServiceImpl.deleteCard(this.company.wallet.idWallet).subscribe( () => {
      this.companyService.company = this.company;
      this.walletFlag = false;
    }));
  }

  private createProduct(name: string, date: Date, description: string, cost: number, typeIndex: number): void {
    let product: ProductModel = new ProductModel();
    let image: File = this.selectedFiles.item(0);
    product.productName = name;
    product.realiseDate = date;
    product.description = description;
    product.cost = cost;
    product.type = this.typeArray[typeIndex];
    product.companyId = this.company.id;
    product.companyName = this.company.name;
    product.isActive = 1;
    //console.log(product);
    this.subscriptions.push(this.productServiceImpl.saveProduct(product).subscribe( productOrErrors => {
      if (productOrErrors.errors != null) {
        this.errorsMapProduct = productOrErrors.errors;
      }
      else {
        this.errorsMapProduct.clear();
        let createdProduct: ProductModel = productOrErrors.product as ProductModel;
        this.subscriptions.push(this.productServiceImpl.saveProductImageByProductId(createdProduct.id, image).subscribe(() => {
          this.productFlag = true;
        }));
      }
    }));
  }

  private changeProductCreatedFlag(): void {
    this.productFlag = false;
    this.name.nativeElement.value = '';
    this.date.nativeElement.value = '';
    this.description.nativeElement.value = '';
    this.file.nativeElement.value = null;
    this.cost.nativeElement.value = '';
  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
