package com.artsykov.fapi.entity;

import lombok.Data;

@Data
public class ProductTypeEntity {
    private int idProductType;
    private String typeName;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(int idProductType, String typeName) {
        this.idProductType = idProductType;
        this.typeName = typeName;
    }
}
