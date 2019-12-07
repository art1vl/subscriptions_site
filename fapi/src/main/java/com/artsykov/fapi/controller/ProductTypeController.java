package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.ProductTypeModel;
import com.artsykov.fapi.service.ProductTypeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product/type")
public class ProductTypeController {

    @Autowired
    private ProductTypeDataService productTypeDataService;

    @GetMapping
    public ResponseEntity<List<ProductTypeModel>> findTypes() {
        return ResponseEntity.ok(productTypeDataService.findTypes());
    }
}
