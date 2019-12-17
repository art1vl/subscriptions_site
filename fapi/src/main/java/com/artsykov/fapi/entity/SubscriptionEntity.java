package com.artsykov.fapi.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class SubscriptionEntity {
    private int idSubscription;
    private Date startSubscriptDate;
    private byte isActive;
    private ProductEntity productByIdProduct;
    private CustomerEntity customerByIdCustomer;
}
