package com.artsykov.fapi.models;

import lombok.Data;

@Data
public class ProductTypeModel {
    private int idProductType;
    private String typeName;

    public ProductTypeModel() {}

    public ProductTypeModel(int idProductType, String typeName) {
        this.idProductType = idProductType;
        this.typeName = typeName;
    }
}
