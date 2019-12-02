import {Component, OnInit} from "@angular/core";
import {customerModel} from "../../models/customerModel";
import {CustomerServiceImpl} from "../../../services/impl/customer.service.impl";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent implements OnInit {
  customerModel: customerModel;

  constructor(private customerServiceImpl: CustomerServiceImpl) {
  }

  ngOnInit() {
  }

  isSignIn(): boolean {
    this.customerModel = this.customerServiceImpl.customer;
    if (this.customerModel == null) {
      return false;
    }
    return true;
  }

  logOut() {
    this.customerServiceImpl.customer = null;
    this.customerModel = null;
    console.log(this.customerModel)
  }
}
