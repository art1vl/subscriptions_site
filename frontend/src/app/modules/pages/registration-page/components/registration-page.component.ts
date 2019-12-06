import {Component, OnDestroy, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {Subscription} from "rxjs";
import {customerModel} from "../../../models/customerModel";
import {Router} from "@angular/router";

@Component({
  selector: "app-registration-page",
  templateUrl: "./registration-page.component.html",
  styleUrls: ["./registration-page.component.css"]
})
export class RegistrationPageComponent implements OnInit, OnDestroy {
  ageArray: number[] = [];
  customer: customerModel;
  myForm : FormGroup;
  errors: Map<string, string> = new Map<string, string>();

  private subscriptions: Subscription[] = [];

  constructor(private customerServiceImpl: CustomerServiceImpl,
              private router: Router){}

  submit(email: string, password: string, name: string, surname: string, age: number): void{
    this.customer = new customerModel();
    this.customer.email = email;
    this.customer.password = password;
    this.customer.name = name;
    this.customer.surname = surname;
    this.customer.age = age;
    this.customer.isActive = 1;
    this.subscriptions.push(this.customerServiceImpl.checkAndSaveCustomer(this.customer).subscribe(registeredCustomer => {
      if (registeredCustomer.errors == null) {
        this.customerServiceImpl.customer = registeredCustomer.customerModel as customerModel;
        this.errors = new Map<string, string>();
        this.router.navigate(["/customer"]);
      }
      else {
        this.errors = registeredCustomer.errors;
      }
    }));
  }

  ngOnInit() {
    for (let i = 18; i < 101; i++) {
      this.ageArray.push(i);
    }
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
      "userName": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z]{1}[a-z]+$')
      ]),
      "userSurname": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z]{1}[a-z]+$')
      ])
    }, );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
