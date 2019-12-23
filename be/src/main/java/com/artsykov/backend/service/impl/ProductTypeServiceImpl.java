package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.ProductTypeEntity;
import com.artsykov.backend.repository.ProductTypeRepository;
import com.artsykov.backend.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public Iterable<ProductTypeEntity> findAll() {
        return productTypeRepository.findAll();
    }
}
