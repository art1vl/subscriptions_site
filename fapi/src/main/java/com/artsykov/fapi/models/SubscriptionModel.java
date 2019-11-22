package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
public class SubscriptionModel {
    private int id;
    private ProductModel product;
    private int idCustomer;
    private Timestamp startSubscriptionDate;
    private boolean isActive;

    public SubscriptionModel() {}

    public SubscriptionModel(int id, ProductModel product, int idCustomer, Timestamp startSubscriptionDate, boolean isActive) {
        this.id = id;
        this.product = product;
        this.idCustomer = idCustomer;
        this.startSubscriptionDate = startSubscriptionDate;
        this.isActive = isActive;
    }
}
