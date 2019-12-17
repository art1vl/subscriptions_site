package com.artsykov.backend.controller;

import com.artsykov.backend.entity.ProductTypeEntity;
import com.artsykov.backend.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/type")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<Iterable<ProductTypeEntity>> findAll() {
        return ResponseEntity.ok(productTypeService.findAll());
    }
}
