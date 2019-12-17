import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionService} from "../subscription.service";
import {subscriptionModel} from "../../modules/models/subscriptionModel";
import {SubscriptionOrErrorsModel} from "../../modules/models/subscriptionOrErrorsModel";
import {CustomerSubscriptionPageModel} from "../../modules/models/customerSubscriptionPageModel";

@Injectable()
export class SubscriptionServiceImpl implements SubscriptionService {

  constructor(private http: HttpClient) {
  }

  createNewSubscription(subscription: subscriptionModel): Observable<SubscriptionOrErrorsModel> {
    return this.http.post<SubscriptionOrErrorsModel>('/api/subscription', subscription);
  }

  deleteSubscription(subscriptionId: number): Observable<void> {
    return this.http.delete<void>('/api/subscription/' + subscriptionId);
  }

  findSubscription(productId: number, customerId: number): Observable<subscriptionModel> {
    return this.http.get<subscriptionModel>('/api/subscription?product=' + productId + '&customer=' + customerId);
  }

  findAllSubscriptionsByCustomerId(customerId: number, page: number, amount: number): Observable<CustomerSubscriptionPageModel> {
    return this.http.get<CustomerSubscriptionPageModel>('/api/subscription/customer/' + customerId + '?page=' + page + '&amount=' + amount);
  }

  findAllSubscriptions(): Observable<subscriptionModel[]> {
    return this.http.get<subscriptionModel[]>('/api/subscription');
  }
}
