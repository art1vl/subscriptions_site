package com.artsykov.backend.service;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.model.ProductPageModel;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(int id);

    ProductPageModel findByPage(int pageNumber, int amount);
}
