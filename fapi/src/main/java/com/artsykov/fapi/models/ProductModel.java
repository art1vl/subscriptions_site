package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
public class ProductModel {
    private int id;
    private String company;
    private String description;
    private String image;
    private String type;
    private Timestamp realiseDate;
    private int cost;
    private String productName;

    public ProductModel() {
    }

    public ProductModel(int id, String company, String description, String image, String type, Timestamp realiseDate, int cost, String productName) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.image = image;
        this.type = type;
        this.realiseDate = realiseDate;
        this.cost = cost;
        this.productName = productName;
    }
}
