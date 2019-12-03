package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.repository.ProductRepository;
import com.artsykov.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity getProduct(int id) {
        return productRepository.findByIdProduct(id);
    }
}
