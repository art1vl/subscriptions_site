import { Component, OnInit } from "@angular/core";
import {CustomerServiceImpl} from "../../../../services/impl/customer.service.impl";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {AdminServiceImpl} from "../../../../services/impl/admin.service.impl";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {

  constructor(private customerService: CustomerServiceImpl,
              private companyService: CompanyServiceImpl,
              private adminService: AdminServiceImpl) {}

  ngOnInit() {
  }
}
