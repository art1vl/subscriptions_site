package com.artsykov.fapi.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductEntity {
    private int idProduct;
    private CompanyEntity company;
    private String description;
    private ProductTypeEntity type;
    private Date realiseDate;
    private int cost;
    private byte isActive;
    private String image;
    private String productName;
    private String companyName;
}
