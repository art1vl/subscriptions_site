package com.artsykov.backend.service;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.model.ProductPageModel;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity productEntity);

    void changeProductStatus(ProductEntity productEntity);

    ProductEntity findProduct(int id);

    ProductPageModel findByPageIsActive(int pageNumber, int amount);

    ProductPageModel findAllByPage(int pageNumber, int amount);

    ProductPageModel findByPageByCompanyId(int companyId, int pageNumber, int amount);

    void deleteProductById(int productId);
}
