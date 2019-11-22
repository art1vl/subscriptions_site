import {ProductModel} from "./productModel";

export class subscriptionModel {
  id: number;
  product: ProductModel;
  idCustomer: number;
  startSubscriptionDate: Date;
  active: boolean;

  static cloneBase(subscription: subscriptionModel): subscriptionModel {
    const clonedSubscription: subscriptionModel = new subscriptionModel();
    clonedSubscription.id = subscription.id;
    clonedSubscription.product = subscription.product;
    clonedSubscription.idCustomer = subscription.idCustomer;
    clonedSubscription.startSubscriptionDate = subscription.startSubscriptionDate;
    clonedSubscription.active = subscription.active;
    return clonedSubscription;
  }
}
