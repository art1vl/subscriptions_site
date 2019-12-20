import {Component, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {Subject, Subscription} from "rxjs";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {PaginationComponent, TabsetComponent} from "ngx-bootstrap";
import {subscriptionModel} from "../../../models/subscriptionModel";
import {SubscriptionServiceImpl} from "../../../../services/impl/subscription.service.impl";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {customerModel} from "../../../models/customerModel";
import {WalletModel} from "../../../models/walletModel";
import {Router} from "@angular/router";
import {WalletServiceImpl} from "../../../../services/impl/wallet-service-impl.service";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {AdminServiceImpl} from "../../../../services/impl/admin.service.impl";

@Component({
  selector: "app-customer-page",
  templateUrl: "./customer-page.component.html",
  styleUrls: ["./customer-page.component.css"]
})

export class CustomerPageComponent implements OnInit, OnDestroy {
  public myNumber = '';
  public myDate = '';
  public myCvv = '';
  public numberMask = [/\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/];
  public dateMask = [/\d/, /\d/, '/', /\d/, /\d/];
  public cvvMask = [/\d/, /\d/, /\d/];

  customer: customerModel;
  customerSubscriptions: subscriptionModel[];
  personalInfFlag: boolean = false;
  replenishFlag: boolean = false;
  walletFlag: boolean;
  myForm: FormGroup;
  walletForm: FormGroup;
  replenishForm: FormGroup;
  ageArray: number[] = [];
  errorsMapCustomer: Map<string, string> = new Map<string, string>();
  errorsMapWallet: Map<string, string> = new Map<string, string>();
  errorsMapReplenishWallet: Map<string, string> = new Map<string, string>();
  hiddenCardNumber: string;
  cardDateString: string;
  totalPage: number;
  totalElements: number;
  numberOfLastLoadedPage: number;
  private balanceFlag = new Subject<boolean>();
  public balanceFlag$ = this.balanceFlag.asObservable();
  private debtFlag = new Subject<boolean>();
  public debtFlag$ = this.debtFlag.asObservable();
  private subscriptionFlag = new Subject<boolean>();
  public subscriptionFlag$ = this.subscriptionFlag.asObservable();
  private paginationFlag = new Subject<boolean>();
  public paginationFlag$ = this.paginationFlag.asObservable();

  private subscriptions: Subscription[] = [];

  constructor(private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl,
              private customerServiceImpl: CustomerServiceImpl,
              private subscriptionServiceImpl: SubscriptionServiceImpl,
              private router: Router,
              private walletServiceImpl: WalletServiceImpl) {
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
    if (this.customerServiceImpl.customer == null) {
      localStorage.clear();
      this.customerServiceImpl.customer = null;
      this.companyService.company = null;
      this.adminService.admin = null;
      this.router.navigate(["/sign/in"]);
    }
    else {
      for (let i = 18; i < 101; i++) {
        this.ageArray.push(i);
      }
      this.subscriptions.push(this.customerServiceImpl.findCustomerById(this.customerServiceImpl.customer.id).subscribe(customer => {
        this.customer = customer as customerModel;
        this.customerServiceImpl.customer = customer;
        this.paginationFlag.next(false);
        this.loadSubscriptionsFirstPageOrReload(0);
        if (this.customer.wallet != null) {
          if (this.customer.wallet.cardNumber != 0) {
            this.walletFlag = true;
            this.hiddenCardNumber = '**** **** **** ' + this.customer.wallet.cardNumber.toString().substring(this.customer.wallet.cardNumber.toString().length - 4);
            let stringDate: string = new Date(this.customer.wallet.cardDate).toLocaleDateString();
            this.cardDateString = stringDate.substring(3, 5) + "/" + stringDate.substring(8);
            this.balanceFlag.next(true);
            if (this.customer.wallet.debt != 0) {
              this.debtFlag.next(true);
            }
          } else {
            this.walletFlag = false;
          }
        } else {
          this.walletFlag = false;
        }
      }));
      this.myForm = new FormGroup({
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
        "userName": new FormControl("", [
          Validators.required,
          Validators.pattern('^[A-Z]{1}[a-z]+$')
        ]),
        "userSurname": new FormControl("", [
          Validators.required,
          Validators.pattern('^[A-Z]{1}[a-z]+$')
        ]),
      },);
      this.replenishForm = new FormGroup({
        "number": new FormControl("", [
          Validators.required,
          Validators.pattern('^[1-9]{1}[0-9]*$')
        ])
      })
    }
  }

  private loadSubscriptionsFirstPageOrReload(pageNumber: number): void {
    this.subscriptions.push(this.subscriptionServiceImpl.findAllSubscriptionsByCustomerId(this.customer.id, pageNumber, 5)
      .subscribe(subscription => {
      this.customerSubscriptions = subscription.subscriptionModels as subscriptionModel[];
      this.totalPage = subscription.totalPages;
      this.totalElements = subscription.totalElements;
      if (this.totalElements > 5) {
        this.paginationFlag.next(true);
      }
      if (this.customerSubscriptions.length != 0) {
        this.subscriptionFlag.next(true);
      }
      else {
        this.subscriptionFlag.next(false);
      }
      this.numberOfLastLoadedPage = pageNumber;
    }));
  }

  private loadSubscriptionsPage(pagination: PaginationComponent): void {
    this.subscriptions.push(this.subscriptionServiceImpl.findAllSubscriptionsByCustomerId(this.customer.id, pagination.page - 1, 5)
      .subscribe(subscription => {
        this.customerSubscriptions = subscription.subscriptionModels as subscriptionModel[];
        this.totalPage = subscription.totalPages;
        this.totalElements = subscription.totalElements;
        this.numberOfLastLoadedPage = pagination.page - 1;
      }));
  }


  private unsubscribe(subscription: subscriptionModel): void {
    this.subscriptions.push(this.subscriptionServiceImpl.deleteSubscription(subscription.id).subscribe(() => {
      if (this.customerSubscriptions.length == 1) {
        if (this.numberOfLastLoadedPage == 0) {
          this.subscriptionFlag.next(false);
        }
        else {
          this.numberOfLastLoadedPage--;
        }
      }
      if (this.customer.isActive == 0) {
        if (subscription.isActive == 0) {
          this.customer.wallet.debt -= subscription.product.cost;
          if (this.customer.wallet.debt == 0) {
            this.subscriptions.push(this.customerServiceImpl.findCustomerById(this.customer.id).subscribe(customer => {
              this.customer = customer as customerModel;
              this.customerServiceImpl.customer = this.customer;
              if (this.customer.wallet.debt == 0) {
                this.debtFlag.next(false);
              }
            }))
          }
        }
      }
      this.loadSubscriptionsFirstPageOrReload(this.numberOfLastLoadedPage);
    }));
  }

  private updatePersonalInf(password: string, name: string, surname: string, age: number): void {
    let updatedCustomer: customerModel = new customerModel();
    updatedCustomer.email = this.customer.email;
    updatedCustomer.password = password;
    updatedCustomer.name = name;
    updatedCustomer.surname = surname;
    updatedCustomer.age = age;
    updatedCustomer.isActive = this.customer.isActive;
    updatedCustomer.id = this.customer.id;
    updatedCustomer.wallet = this.customer.wallet;
    updatedCustomer.idLogInInf = this.customer.idLogInInf;
    this.subscriptions.push(this.customerServiceImpl.updateCustomerPersonalInf(updatedCustomer).subscribe(customer => {
      if (customer.errors == null) {
        this.customer = updatedCustomer;
        this.customerServiceImpl.customer = updatedCustomer;
        this.personalInfFlag = false;
        this.errorsMapCustomer = new Map<string, string>();
      } else {
        this.errorsMapCustomer = customer.errors;
      }
    }));
  }

  private saveWallet(cardNumber: string, cardDate: string, cardCvv: string, cardHolderName: string): void {
    if (this.customer.wallet == null) {
      this.customer.wallet = new WalletModel();
    }
    this.customer.wallet.cardNumber = +cardNumber.replace(/\s/g, '');
    this.customer.wallet.cardDate = new Date(cardDate.substring(0, 2) + "/01/20" + cardDate.substring(3));
    this.customer.wallet.cardCvvCode = +cardCvv;
    this.customer.wallet.personName = cardHolderName;
    this.subscriptions.push(this.customerServiceImpl.saveCustomerWallet(this.customer).subscribe(customerOrErrors => {
      if (customerOrErrors.errors == null) {
        this.customer = customerOrErrors.customerModel as customerModel;
        this.customerServiceImpl.customer = this.customer;
        this.errorsMapWallet = new Map<string, string>();
        this.hiddenCardNumber = '**** **** **** ' + cardNumber.substring(cardNumber.length - 4);
        this.cardDateString = cardDate;
        this.walletFlag = true;
        this.myForm.reset();
      } else {
        this.errorsMapWallet = customerOrErrors.errors;
        this.customer = this.customerServiceImpl.customer;
      }
    }));
  }

  private deleteCard(): void {
    this.subscriptions.push(this.walletServiceImpl.deleteCard(this.customer.wallet.idWallet).subscribe(() => {
      this.customer.wallet.cardDate = null;
      this.customer.wallet.cardNumber = null;
      this.customer.wallet.personName = null;
      this.customer.wallet.cardCvvCode = null;
      this.customerServiceImpl.customer = this.customer;
      this.walletFlag = false;
    }));
  }

  private replenishCard(number: string): void {
    if (this.customer.isActive == 0) {
        this.customer.wallet.debt -= +number;
        this.subscriptions.push(this.customerServiceImpl.liquidateDebt(this.customer).subscribe(customerOrErrors => {
          if (customerOrErrors.errors == null) {
            this.customerServiceImpl.customer = customerOrErrors.customerModel as customerModel;
            this.customer = customerOrErrors.customerModel as customerModel;
            if (this.customer.wallet.debt == 0) {
              this.debtFlag.next(false);
              this.loadSubscriptionsFirstPageOrReload(this.numberOfLastLoadedPage);
            }
            this.balanceFlag.next(true);
            this.replenishFlag = true;
          }
          else {
            this.customer = this.customerServiceImpl.customer;
          }
        }));
    }
    else {
      this.customer.wallet.balance += +number;
      this.subscriptions.push(this.walletServiceImpl.updateCard(this.customer.wallet).subscribe(walletOrErrors => {
        if (walletOrErrors.errors == null) {
          this.customerServiceImpl.customer = this.customer;
          this.errorsMapReplenishWallet = new Map<string, string>();
          this.replenishFlag = true;
          this.balanceFlag.next(true);
        } else {
          this.errorsMapReplenishWallet = walletOrErrors.errors;
          this.customer = this.customerServiceImpl.customer;
        }
      }));
    }
  }

  changeReplenishFlag(): void {
    this.replenishForm.reset();
    this.replenishFlag = false;
  }

  private editPersonalInf(): void {
    this.personalInfFlag = true;
  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
