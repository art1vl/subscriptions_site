import {Component, OnInit, ViewChild} from "@angular/core";
import {TabsetComponent} from "ngx-bootstrap";
import {subscriptionModel} from "../../../models/subscriptionModel";
import {Subscription} from "rxjs";
import {SubscriptionServiceImpl} from "../../../../services/impl/subscription.service.impl";
import {companyModel} from "../../../models/companyModel";
import {CompanyServiceImpl} from "../../../../services/impl/company.service.impl";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: "app-admin-page",
  templateUrl: "./admin-page.component.html",
  styleUrls: ["./admin-page.component.css"]
})
export class AdminPageComponent implements OnInit {

  myForm : FormGroup;
  private subscriptionsModel: subscriptionModel[] = [];
  private companyModel: companyModel[] = [];
  errorsMapCompany: Map<string, string> = new Map<string, string>();
  companyCreatedFlag: boolean = false;

  private subscriptions: Subscription[] = [];

  constructor(private subscriptionService: SubscriptionServiceImpl,
              private companyService: CompanyServiceImpl){
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
      "companyName": new FormControl("", [
        Validators.required,
        Validators.pattern('^[A-Z a-z0-9]+$')
      ]),
    }, );
  }

  ngOnInit() {
    this.loadSubscriptions();
   // this.loadCompanies();
  }

  private loadSubscriptions(): void {
    this.subscriptions.push(this.subscriptionService.findAllSubscriptions().subscribe(subscription => {
      this.subscriptionsModel = subscription as subscriptionModel[];
    }));
  }

  private loadCompanies(): void {
    this.subscriptions.push(this.companyService.findAllCompanies().subscribe(company => {
      this.companyModel = company as companyModel[];
    }));
  }

  private createCompanyAccount(email: string, password: string, companyName: string): void {
    let newCompany = new companyModel();
    newCompany.email = email;
    newCompany.password = password;
    newCompany.name = companyName;
    newCompany.isActive = 1;
    this.subscriptions.push(this.companyService.saveCompany(newCompany).subscribe(companyOrErrors => {
      if (companyOrErrors.errors != null) {
        this.errorsMapCompany = companyOrErrors.errors;
      }
      else {
        this.companyCreatedFlag = true;
      }
    }));
  }

  private changeCompanyCreatedFlag(): void {
    this.companyCreatedFlag = false;
  }

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }
}
