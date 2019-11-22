import {Component, Input, OnInit} from "@angular/core";
import {SubscriptionModule} from "../subscription.module";

@Component({
  selector: "app-subscription",
  templateUrl: "./subscription.component.html",
  styleUrls: ["./subscription.component.css"]
})

export class SubscriptionComponent implements OnInit {
  @Input() subscription: SubscriptionModule;
  @Input() role: string;

  ngOnInit() {}
}
