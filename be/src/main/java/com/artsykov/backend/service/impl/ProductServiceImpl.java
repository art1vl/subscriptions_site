package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.model.ProductPageModel;
import com.artsykov.backend.repository.ProductRepository;
import com.artsykov.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ProductPageModel findByPage(int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
        Page<ProductEntity> page = productRepository.findAll(pageable);
        ProductPageModel productPageModel = new ProductPageModel();
        productPageModel.setProductList(page.getContent());
        productPageModel.setTotalElements(page.getTotalElements());
        productPageModel.setTotalPages(page.getTotalPages());
        return productPageModel;
    }
}
