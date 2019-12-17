package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CustomerSubscriptionPageModel;
import com.artsykov.fapi.models.SubscriptionModel;
import com.artsykov.fapi.models.SubscriptionOrErrorsModel;

public interface SubscriptionService {
    SubscriptionOrErrorsModel saveSubscription(SubscriptionModel subscriptionModel);

    SubscriptionModel findSubscription(int productId, int customerId);

    CustomerSubscriptionPageModel findAllSubscriptionsByCustomerId(int customerId, int page, int amount);

    void deleteById(int id);
}
