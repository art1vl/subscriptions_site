package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SubscriptionModel {
    @Min(value=0, message="Incorrect subscription id")
    private int id;
    private ProductModel product;

    @Min(value=1, message="Incorrect customer id")
    private int idCustomer;
    private Date startSubscriptionDate;

    @Min(value=0, message="Incorrect active status")
    @Max(value=1, message="Incorrect active status")
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
