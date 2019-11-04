import { Component, OnInit } from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Customer} from "../../models/registration-page/customer";
import {CustomerServiceImpl} from "../../../services/impl/customer.service.impl";
import {Product} from "../../models/catalog-page/product";
import {Subscription} from "rxjs";


@Component({
  selector: "app-registration-page",
  templateUrl: "./registration-page.component.html",
  styleUrls: ["./registration-page.component.css"]
})
export class RegistrationPageComponent implements OnInit {
  ageArray: number[] = [];
  costumer: Customer;
  myForm : FormGroup;
  freeEmailFlag: boolean;

  private subscriptions: Subscription[] = [];

  constructor(private CostumerService: CustomerServiceImpl){
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
        // passwordMatchValidator(this.myForm.get("password").value, this.myForm.get("repeatPassword").value)
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
  //
  // get password() {
  //   this.myForm.get('password');
  // }

  // passwordMatchValidator(control: FormControl): ValidatorFn {
  //   if (control) {
  //     if (control.get("password").value !== control.get("repeatPassword").value) {
  //       return ['error'];
  //     }
  //   }
  //   return null;
  // }

  submit(email: string, password: string, name: string, surname: string, age: number){
    this.costumer = new Customer();
    this.costumer.email = email;
    this.costumer.password = password;
    this.costumer.name = name;
    this.costumer.surname = surname;
    this.costumer.age = age;
    this.subscriptions.push(this.CostumerService.checkAndSaveCostumer(this.costumer).subscribe(flag => {
      // Parse json response into local array
      this.freeEmailFlag = flag as boolean;
      // Check data in console
      console.log(this.costumer);// don't use console.log in angular :)
    }));
  }

  cleanInput() {
    this.myForm.setValue({
      password: ""
    })
  }

  func() {
    console.log(this.myForm);
  }

  ngOnInit() {
    for (let i = 18; i < 101; i++) {
      this.ageArray.push(i);
    }
  }

  // myFunction() {
  //   let x = document.getElementById("inputPassword4");
  //   if (x.title == "password") {
  //     x.title = "text";
  //   } else {
  //     x.title = "password";
  //   }
  // }
}
