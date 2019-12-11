package com.artsykov.backend.model;

import com.artsykov.backend.entity.ProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductPageModel {
    int totalPages;
    long totalElements;
    List<ProductEntity> productList;
}
