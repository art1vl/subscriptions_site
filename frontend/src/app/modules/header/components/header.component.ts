import {Component, OnInit} from "@angular/core";
import {customerModel} from "../../models/customerModel";
import {CustomerServiceImpl} from "../../../services/impl/customer.service.impl";
import {companyModel} from "../../models/companyModel";
import {adminModel} from "../../models/adminModel";
import {CompanyServiceImpl} from "../../../services/impl/company.service.impl";
import {AdminServiceImpl} from "../../../services/impl/admin.service.impl";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent implements OnInit {
  customerModel: customerModel;
  companyModel: companyModel;
  adminModel: adminModel;

  constructor(private customerServiceImpl: CustomerServiceImpl,
              private companyServiceImpl: CompanyServiceImpl,
              private adminServiceImpl: AdminServiceImpl) {
  }

  ngOnInit() {
  }

  isSignIn(): boolean {
    this.customerModel = this.customerServiceImpl.customer;
    this.companyModel = this.companyServiceImpl.company;
    this.adminModel = this.adminServiceImpl.admin;
    if (this.customerModel == null &&
        this.companyModel == null &&
        this.adminModel == null) {
      return false;
    }
    return true;
  }

  isSignInUser(number: number): boolean {
    switch (number) {
      case 0:
        if (this.customerModel == null) {
          return false;
        }
        else {
          return true;
        }
      case 1:
        if (this.companyModel == null) {
          return false;
        }
        else {
          return true;
        }
      case 2:
        if (this.adminModel == null) {
          return false;
        }
        else {
          return true;
        }
    }

  }

  logOut() {
    this.adminServiceImpl.admin = null;
    this.adminModel = null;
    this.companyServiceImpl.company = null;
    this.companyModel = null;
    this.customerServiceImpl.customer = null;
    this.customerModel = null;
  }
}
