import {Observable} from "rxjs";
import {subscriptionModel} from "../modules/models/subscriptionModel";

export interface SubscriptionService {
  findAllSubscriptionsByCustomerId(customerId: string): Observable<subscriptionModel[]>;

  findAllSubscriptions(): Observable<subscriptionModel[]>;

  createNewSubscription(subscription: subscriptionModel): Observable<subscriptionModel>;

  deleteSubscriptionById(subscriptionId: number, customerId: number): Observable<void>;

  // findProductById(productId: string): Observable<ProductModel>;
  //
  // findProductTypes(): Observable<String[]>;
}
