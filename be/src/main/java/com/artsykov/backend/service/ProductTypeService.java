package com.artsykov.backend.service;

import com.artsykov.backend.entity.ProductTypeEntity;

public interface ProductTypeService {
    Iterable<ProductTypeEntity> findAll();
}
