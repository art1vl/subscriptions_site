package com.artsykov.fapi.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Data
public class ProductModel {
    private int id;

    @NotEmpty(message = "Company name is required")
    private int companyId;
    private String companyName;

    @NotEmpty(message = "Description is required")
    @Pattern(regexp = "^[A-Z a-z0-9.,:;]+$",
            message = "Description is invalid")
    private String description;
    private String image;
    private ProductTypeModel type;
    private Date realiseDate;


    @NotEmpty(message = "Cost is required")
    @Positive(message = "Cost is invalid")
    private int cost;

    @NotEmpty(message = "Product name is required")
    @Pattern(regexp = "^[A-Z a-z0-9.,:;]+$",
            message = "Product name is invalid")
    private String productName;
    private int isActive;

    public ProductModel() {
    }

    public ProductModel(int id, int company, String description, String image, ProductTypeModel type, Date realiseDate, int cost, String productName, int isActive) {
        this.id = id;
        this.companyId = company;
        this.description = description;
        this.image = image;
        this.type = type;
        this.realiseDate = realiseDate;
        this.cost = cost;
        this.productName = productName;
        this.isActive = isActive;
    }
}
