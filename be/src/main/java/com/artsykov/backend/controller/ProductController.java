package com.artsykov.backend.controller;

import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.model.ProductPageModel;
import com.artsykov.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.findProduct(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ProductPageModel> getAllProductsByPage(@RequestParam("page") Integer pageNumber,
                                                                 @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productService.findAllByPage(pageNumber, amount));
    }

    @GetMapping
    public ResponseEntity<ProductPageModel> getProductsByPageIsActive(@RequestParam("page") Integer pageNumber,
                                                                      @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productService.findByPageIsActive(pageNumber, amount));
    }

    @GetMapping(value = "/company/{id}")
    public ResponseEntity<ProductPageModel> getProductsByPageByCompanyId(@PathVariable("id") Integer companyId,
                                                                         @RequestParam("page") Integer pageNumber,
                                                                         @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productService.findByPageByCompanyId(companyId, pageNumber, amount));
    }

    @PostMapping
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productService.saveProduct(productEntity));
    }

    @PutMapping
    public void changeProductStatus(@RequestBody ProductEntity productEntity) {
        productService.changeProductStatus(productEntity);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable("id") Integer productId) {
        productService.deleteProductById(productId);
    }
}
