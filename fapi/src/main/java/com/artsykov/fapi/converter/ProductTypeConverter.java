package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.ProductTypeEntity;
import com.artsykov.fapi.models.ProductTypeModel;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeConverter {
    public ProductTypeEntity convertFromFrontToBack(ProductTypeModel productTypeModel) {
        if (productTypeModel != null) {
            ProductTypeEntity productTypeEntity = new ProductTypeEntity();
            productTypeEntity.setIdProductType(productTypeModel.getIdProductType());
            productTypeEntity.setTypeName(productTypeModel.getTypeName());
            return productTypeEntity;
        }
        return null;
    }

    public ProductTypeModel convertFromBackToFront(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity != null) {
            ProductTypeModel productTypeModel = new ProductTypeModel();
            productTypeModel.setIdProductType(productTypeEntity.getIdProductType());
            productTypeModel.setTypeName(productTypeEntity.getTypeName());
            return productTypeModel;
        }
        return null;
    }
}
