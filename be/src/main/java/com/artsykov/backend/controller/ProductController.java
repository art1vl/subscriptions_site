package com.artsykov.backend.controller;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.model.ProductPageModel;
import com.artsykov.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productService.saveProduct(productEntity));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<ProductPageModel> getProductsByPage(@RequestParam("page") Integer pageNumber, @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productService.findByPage(pageNumber, amount));
    }
}
