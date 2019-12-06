package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class ProductOrErrorsModel {
    private ProductModel product;
    private Map<String, String> errors;

    public ProductOrErrorsModel() {}

    public ProductOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }

    public ProductOrErrorsModel(ProductModel product) {
        this.product = product;
    }
}
