package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.SubscriptionEntity;
import com.artsykov.fapi.models.SubscriptionModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {
    private CustomerDataService customerDataService;
    private ProductConverter productConverter;
    private CustomerConverter customerConverter;

    @Autowired
    public SubscriptionConverter(CustomerDataService customerDataService,
                                 ProductConverter productConverter,
                                 CustomerConverter customerConverter) {
        this.customerDataService = customerDataService;
        this.productConverter = productConverter;
        this.customerConverter = customerConverter;
    }

    public SubscriptionEntity convertFromFrontToBack(SubscriptionModel subscriptionModel) {
        if (subscriptionModel != null) {
            SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
            subscriptionEntity.setStartSubscriptDate(subscriptionModel.getStartSubscriptionDate());
            subscriptionEntity.setIsActive((byte) 1);
            subscriptionEntity.setProductByIdProduct(productConverter.convertFromFrontToBack(subscriptionModel
                                                                                                .getProduct()));
            subscriptionEntity.setCustomerByIdCustomer(customerConverter.convertFromFrontToBack(
                    customerDataService.findCustomerById(subscriptionModel.getIdCustomer())));
            return subscriptionEntity;
        }
        return null;
    }

    public SubscriptionModel convertFromBackToFront(SubscriptionEntity subscriptionEntity) {
        if (subscriptionEntity != null) {
            SubscriptionModel subscriptionModel = new SubscriptionModel();
            subscriptionModel.setId(subscriptionEntity.getIdSubscription());
            subscriptionModel.setIdCustomer(subscriptionEntity.getCustomerByIdCustomer().getIdCustomer());
            subscriptionModel.setIsActive(subscriptionEntity.getIsActive());
            subscriptionModel.setProduct(productConverter.convertFromBackToFront(subscriptionEntity.getProductByIdProduct()));
            subscriptionModel.setStartSubscriptionDate(subscriptionEntity.getStartSubscriptDate());
            return subscriptionModel;
        }
        return null;
    }
}
