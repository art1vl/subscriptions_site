import { Component, OnInit } from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {customerModel} from "../../../models/customerModel";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {AdminServiceImpl} from "../../../../services/impl/admin.service.impl";
import {companyModel} from "../../../models/companyModel";
import {adminModel} from "../../../models/adminModel";
import {LogInInfServiceImpl} from "../../../../services/impl/logInInf.service.impl";

@Component({
  selector: "app-sign-in",
  templateUrl: "./sign-in.component.html",
  styleUrls: ["./sign-in.component.css"]
})
export class SignInComponent implements OnInit {
  myForm : FormGroup;
  customer: customerModel;
  errorsMap: Map<string, string> = new Map<string, string>();

  private subscriptions: Subscription[] = [];

  constructor(private router: Router,
              private logInInfService: LogInInfServiceImpl,
              private customerService: CustomerServiceImpl,
              private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl){
    this.myForm = new FormGroup({

      "userEmail": new FormControl("", [
        // Validators.required,
        // Validators.email
      ]),
      "userPassword": new FormControl("", [
        // Validators.required,
        // Validators.minLength(8)
      ])
    });
  }

  submit(email: string, password: string){
    this.subscriptions.push(this.logInInfService.signin(email, password).subscribe(user => {
      if (user.errors == null) {
        this.customerService.customer = user.customerModel as customerModel;
        this.companyService.company = user.companyModel as companyModel;
        this.adminService.admin = user.adminModel as adminModel;
        this.router.navigateByUrl("/");
        this.errorsMap = null;
      }
      else {
        this.errorsMap = user.errors;
      }
    }));
  }

  ngOnInit() {
    this.customer = this.customerService.customer;
  }
}
