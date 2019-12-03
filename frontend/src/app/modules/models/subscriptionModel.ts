import {ProductModel} from "./productModel";

export class subscriptionModel {
  id: number;
  product: ProductModel;
  idCustomer: number;
  startSubscriptionDate: Date;
  isActive: number;

  static cloneBase(subscription: subscriptionModel): subscriptionModel {
    const clonedSubscription: subscriptionModel = new subscriptionModel();
    clonedSubscription.id = subscription.id;
    clonedSubscription.product = subscription.product;
    clonedSubscription.idCustomer = subscription.idCustomer;
    clonedSubscription.startSubscriptionDate = subscription.startSubscriptionDate;
    clonedSubscription.isActive = subscription.isActive;
    return clonedSubscription;
  }
}
