import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionService} from "../subscription.service";
import {subscriptionModel} from "../../modules/models/subscriptionModel";

@Injectable()
// Data service
export class SubscriptionServiceImpl implements SubscriptionService {

  constructor(private http: HttpClient) {
  }

  //todo
  createNewSubscription(subscription: subscriptionModel): Observable<subscriptionModel> {
   // return this.http.post<ProductModel>('/api/product', product);
    return undefined;
  }

  //todo
  deleteSubscriptionById(subscriptionId: number, customerId: number): Observable<void> {
    return this.http.delete<void>('/api/subscription/' + subscriptionId + '/' + customerId);
  }

  findAllSubscriptionsByCustomerId(customerId: string): Observable<subscriptionModel[]> {
    return this.http.get<subscriptionModel[]>('/api/subscription/' + customerId);
  }

  findAllSubscriptions(): Observable<subscriptionModel[]> {
    return this.http.get<subscriptionModel[]>('/api/subscription');
  }
}
