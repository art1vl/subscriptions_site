import {Observable} from "rxjs";
import {subscriptionModel} from "../modules/models/subscriptionModel";
import {SubscriptionOrErrorsModel} from "../modules/models/subscriptionOrErrorsModel";
import {CustomerSubscriptionPageModel} from "../modules/models/customerSubscriptionPageModel";

export interface SubscriptionService {
  findAllSubscriptionsByCustomerId(customerId: number, page: number, amount: number): Observable<CustomerSubscriptionPageModel>;

  findAllSubscriptions(): Observable<subscriptionModel[]>;

  createNewSubscription(subscription: subscriptionModel): Observable<SubscriptionOrErrorsModel>;

 // deleteSubscriptionById(subscriptionId: number, customerId: number): Observable<void>;

  findSubscription(productId: number, customerId: number): Observable<subscriptionModel>;

  deleteSubscription(subscriptionId: number): Observable<void>;

  // findProductById(productId: string): Observable<ProductModel>;
  //
  // findProductTypes(): Observable<String[]>;
}
