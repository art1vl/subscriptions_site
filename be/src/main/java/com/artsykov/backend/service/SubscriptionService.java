package com.artsykov.backend.service;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.entity.SubscriptionEntity;
import com.artsykov.backend.model.CustomerSubscriptionPageModel;

public interface SubscriptionService {
    SubscriptionEntity save(SubscriptionEntity subscriptionEntity);

    SubscriptionEntity findSubscription(int productId, int customerId);

    CustomerSubscriptionPageModel findAllByCustomerId(int customerId, int page, int amount);

    void deleteById(int id);

    void deleteByProductId(ProductEntity productEntity);

    void changeSubscriptionStatusByProduct(ProductEntity productEntity);
}
