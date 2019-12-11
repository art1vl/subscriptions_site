package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.ProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductPageModel {
    int totalPages;
    long totalElements;
    List<ProductEntity> productList;
    List<ProductModel> productModelList;
}
