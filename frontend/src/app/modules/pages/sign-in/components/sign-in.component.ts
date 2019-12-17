import {Component, OnDestroy, OnInit} from "@angular/core";
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
export class SignInComponent implements OnInit, OnDestroy {
  myForm : FormGroup;
  customer: customerModel;
  errorSignIn: string;

  private subscriptions: Subscription[] = [];

  constructor(private router: Router,
              private logInInfService: LogInInfServiceImpl,
              private customerService: CustomerServiceImpl,
              private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl){
  }

  ngOnInit() {
    if (this.customerService.customer != null ||
      this.companyService.company != null ||
      this.adminService.admin != null) {
      this.router.navigate(["/"]);
    }
    else {
      this.myForm = new FormGroup({

        "userEmail": new FormControl("", [
          Validators.required,
          Validators.email
        ]),
        "userPassword": new FormControl("", [
          Validators.required,
          Validators.minLength(8)
        ])
      });
    }
  }

  submit(email: string, password: string){
    this.subscriptions.push(this.logInInfService.signin(email, password).subscribe(signInModel => {
      if (signInModel.error == null) {
        this.errorSignIn = null;
        localStorage.setItem("token", signInModel.token);
        let logInInfId: number = signInModel.user.idLogInInf;
        switch (signInModel.user.role) {
          case "CUSTOMER":
            this.subscriptions.push(this.customerService.findCustomerByLogInInfId(logInInfId).subscribe( customer => {
              this.customerService.customer = customer as customerModel;
              console.log(this.customerService);
              if (this.customerService.customer.isActive == 0) {
                this.errorSignIn = "Your account was blocked by administration. To know more, please, contact us by art_vl@mail.ru"
              }
              if (this.errorSignIn == null) {
                this.router.navigate(["/"]);
              }
              else {
                localStorage.clear();
              }
            }));
            break;
          case "COMPANY":
            this.subscriptions.push(this.companyService.findCompanyByLogInInfId(logInInfId).subscribe( company => {
              this.companyService.company = company as companyModel;
              if (this.companyService.company.isActive == 0) {
                this.errorSignIn = "Your account was blocked by administration. To know more, please, contact us by art_vl@mail.ru"
              }
              if (this.errorSignIn == null) {
                this.router.navigate(["/"]);
              }
              else {
                localStorage.clear();
              }
            }));
            break;
          case "ADMIN":
            this.adminService.admin = new adminModel(signInModel.user.idLogInInf);
            this.router.navigate(["/"]);
            break;
        }
        // if (this.errorSignIn == null) {
        //   this.router.navigate(["/"]);
        // }
      }
      else {
        this.errorSignIn = signInModel.error;
        this.myForm.reset(); 
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
    this.subscriptions = [];
  }
}
