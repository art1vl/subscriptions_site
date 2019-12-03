package com.artsykov.backend.service;

import com.artsykov.backend.entity.ProductEntity;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(int id);
}
