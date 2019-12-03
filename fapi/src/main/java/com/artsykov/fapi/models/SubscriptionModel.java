package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SubscriptionModel {
    private int id;
    private ProductModel product;
    private int idCustomer;
    private Date startSubscriptionDate;
    private int isActive;

    public SubscriptionModel() {}

    public SubscriptionModel(int id, ProductModel product, int idCustomer, Date startSubscriptionDate, int isActive) {
        this.id = id;
        this.product = product;
        this.idCustomer = idCustomer;
        this.startSubscriptionDate = startSubscriptionDate;
        this.isActive = isActive;
    }
}
