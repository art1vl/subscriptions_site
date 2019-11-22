import {Component, OnInit, ViewChild} from "@angular/core";
import {Subscription} from "rxjs";
import {TabsetComponent} from "ngx-bootstrap";

@Component({
  selector: "app-company-page",
  templateUrl: "./company-page.component.html",
  styleUrls: ["./company-page.component.css"]
})
export class CompanyPageComponent implements OnInit {

  private subscriptions: Subscription[] = [];

  constructor(){}

  ngOnInit() {}

  @ViewChild('staticTabs') staticTabs: TabsetComponent;

  selectTab(tabId: number) {
    this.staticTabs.tabs[tabId].active = true;
  }

}
