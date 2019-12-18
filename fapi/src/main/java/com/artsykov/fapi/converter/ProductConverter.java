package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.ProductEntity;
import com.artsykov.fapi.entity.ProductTypeEntity;
import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductTypeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductConverter {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    public ProductEntity convertFromFrontToBack(ProductModel productModel) {
        if (productModel != null) {
            ProductEntity productEntity = new ProductEntity();
            RestTemplate restTemplate = new RestTemplate();
            CompanyEntity companyEntity = restTemplate.getForObject(backendServerUrl + "/api/company/" + productModel.getCompanyId(), CompanyEntity.class);
            productEntity.setCompany(companyEntity);
            productEntity.setDescription(productModel.getDescription());
            ProductTypeEntity productTypeEntity = new ProductTypeEntity();
            productTypeEntity.setIdProductType(productModel.getType().getIdProductType());
            productTypeEntity.setTypeName(productModel.getType().getTypeName());
            productEntity.setType(productTypeEntity);
            productEntity.setRealiseDate(productModel.getRealiseDate());
            productEntity.setCost(productModel.getCost());
            productEntity.setIsActive((byte)productModel.getIsActive());
            productEntity.setImage(productModel.getImage());
            productEntity.setProductName(productModel.getProductName());
            productEntity.setIdProduct(productModel.getId());
            return productEntity;
        }
        return null;
    }

    public ProductModel convertFromBackToFront(ProductEntity productEntity) {
        if (productEntity != null) {
            ProductModel productModel = new ProductModel();
            productModel.setId(productEntity.getIdProduct());
            productModel.setCompanyId(productEntity.getCompany().getIdCompany());
            productModel.setCompanyName(productEntity.getCompany().getCompanyName());
            productModel.setDescription(productEntity.getDescription());
            ProductTypeModel productTypeModel = new ProductTypeModel();
            productTypeModel.setIdProductType(productEntity.getType().getIdProductType());
            productTypeModel.setTypeName(productEntity.getType().getTypeName());
            productModel.setType(productTypeModel);
            productModel.setCompanyActiveStatus(productEntity.getCompany().getIsActive());
            productModel.setRealiseDate(productEntity.getRealiseDate());
            productModel.setCost(productEntity.getCost());
            productModel.setIsActive(productEntity.getIsActive());
            productModel.setImage(productEntity.getImage());
            productModel.setProductName(productEntity.getProductName());
            return productModel;
        }
        return null;
    }
}
