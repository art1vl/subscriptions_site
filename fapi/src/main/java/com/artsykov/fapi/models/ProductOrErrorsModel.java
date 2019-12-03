package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class ProductOrErrorsModel {
    private ProductModel product;
    private Map<String, String> errors;
}
