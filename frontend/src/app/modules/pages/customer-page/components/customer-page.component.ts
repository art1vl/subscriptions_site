import {Component, OnInit, ViewChild} from "@angular/core";
import {Subscription} from "rxjs";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {TabsetComponent} from "ngx-bootstrap";
import {subscriptionModel} from "../../../models/subscriptionModel";
import {SubscriptionServiceImpl} from "../../../../services/impl/subscription.service.impl";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {customerModel} from "../../../models/customerModel";
import {WalletModel} from "../../../models/walletModel";
import {Router} from "@angular/router";

@Component({
  selector: "app-customer-page",
  templateUrl: "./customer-page.component.html",
  styleUrls: ["./customer-page.component.css"]
})

export class CustomerPageComponent implements OnInit {
  public myNumber = '';
  public myDate = '';
  public myCvv = '';
  public numberMask = [/\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/];
  public dateMask = [ /\d/, /\d/, '/', /\d/, /\d/];
  public cvvMask = [ /\d/, /\d/, /\d/];

  customer: customerModel;
  customerSubscriptions: subscriptionModel[];
  personalInfFlag: boolean = false;
  walletFlag: boolean = false;
  myForm : FormGroup;
  walletForm: FormGroup;
  replenishForm: FormGroup;
  ageArray: number[] = [];
  errorsMapCustomer: Map<string, string> = new Map<string, string>();
  errorsMapWallet: Map<string, string> = new Map<string, string>();
  errorsMapReplenishWallet: Map<string, string> = new Map<string, string>();
  hiddenCardNumber: string;
  cardDateString: string;

  private subscriptions: Subscription[] = [];

  constructor(private customerServiceImpl: CustomerServiceImpl,
              private subscriptionServiceImpl: SubscriptionServiceImpl,
              private router: Router){
    this.myForm = new FormGroup({
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
      "userName": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z]{1}[a-z]+$')
      ]),
      "userSurname": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z]{1}[a-z]+$')
      ])
    }, );
    this.walletForm = new FormGroup({
      "cardNumber": new FormControl("",[
        Validators.required,
        Validators.pattern('^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$')
      ]),
      "cardDate": new FormControl("",[
        Validators.required,
        Validators.pattern('^(0[1-9]|1[012])/(20|21|22|23)$')
      ]),
      "cardCvv": new FormControl("",[
        Validators.required,
        Validators.pattern('^\\d{3}$')
      ]),
      "cardHolderName": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z]+\\s[A-Z]+$')
      ])
    }, );
    this.replenishForm = new FormGroup({
      "number": new FormControl("",[
        Validators.required,
        Validators.pattern('^[1-9]{1}[0-9]*$')
      ])
    })
  }

  ngOnInit() {
    if (this.customerServiceImpl.customer == null) {
      this.router.navigateByUrl("/sign_in");
    }
    else {
      for (let i = 18; i < 101; i++) {
        this.ageArray.push(i);
      }
      this.loadSubscriptions();
      this.customer = this.customerServiceImpl.customer;
    }
  }

  private loadSubscriptions(): void {
    this.subscriptions.push(this.subscriptionServiceImpl.findAllSubscriptionsByCustomerId("1").subscribe(subscription => {
      this.customerSubscriptions = subscription as subscriptionModel[];
    }));
  }

  public _updateSubscriptions(): void {
    this.loadSubscriptions();
  }

  private unsubscribe(subscriptionId: number, customerId: number): void{
    this.subscriptions.push(this.subscriptionServiceImpl.deleteSubscriptionById(subscriptionId, customerId).subscribe(() => {
        this._updateSubscriptions();
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
    this.subscriptions.push(this.customerServiceImpl.updateCustomerPersonalInf(updatedCustomer).subscribe( customer => {
      if (customer.errors == null) {
        this.customer = updatedCustomer;
        this.customerServiceImpl.customer = updatedCustomer;
        this.personalInfFlag = false;
        this.errorsMapCustomer = null;
      }
      else {
        this.errorsMapCustomer = customer.errors;
      }
    }));
  }

  private saveWallet(cardNumber: string, cardDate: string, cardCvv: string, cardHolderName: string): void {
    this.customer.wallet = new WalletModel();
    this.customer.wallet.cardNumber = +cardNumber.replace(' ', '');
    this.customer.wallet.cardDate = new Date(cardDate);
    this.customer.wallet.cardCvvCode = +cardCvv;
    this.customer.wallet.personName = cardHolderName;
    this.subscriptions.push(this.customerServiceImpl.saveCustomerWallet(this.customer).subscribe( customerOrErrors => {
      if (customerOrErrors.errors == null) {
        this.customer = customerOrErrors.customerModel as customerModel;
        this.customerServiceImpl.customer = this.customer;
        this.errorsMapWallet = null;
        this.hiddenCardNumber = '**** **** ****' + cardNumber.substring(cardNumber.length - 4);
        this.cardDateString = cardDate;
        this.walletFlag = true;
      }
      else {
        this.errorsMapCustomer = customerOrErrors.errors;
        this.customer = this.customerServiceImpl.customer;
      }
    }));
  }

  private deleteCard(): void {
    this.customer.wallet.cardDate = null;
    this.customer.wallet.cardNumber = null;
    this.customer.wallet.personName = null;
    this.customer.wallet.cardCvvCode = null;
    this.subscriptions.push(this.customerServiceImpl.deleteCard(this.customer).subscribe( () => {
      this.customerServiceImpl.customer = this.customer;
      this.walletFlag = false;
    }));
  }

  private replenishCard(number: number): void {
    this.customer.wallet.balance += number;
    this.subscriptions.push(this.customerServiceImpl.replenishCard(this.customer).subscribe( customerOrErrors => {
      if (customerOrErrors.errors == null) {
        this.customerServiceImpl.customer = this.customer;
      }
      else {
        this.errorsMapReplenishWallet = customerOrErrors.errors;
        this.customer = this.customerServiceImpl.customer;
      }
    }));
  }

  private editPersonalInf(): void {
    this.personalInfFlag = true;
  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }

  // //for test
  // public ngModelChange(): void {
  //   console.log(this.myModel);
  // }
}
