import {subscriptionModel} from "./subscriptionModel";

export class SubscriptionOrErrorsModel {
  subscriptionModel: subscriptionModel;
  errors: Map<string, string>;
}
