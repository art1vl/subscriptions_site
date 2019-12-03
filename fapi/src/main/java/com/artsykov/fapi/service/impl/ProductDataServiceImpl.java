package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.ProductConverter;
import com.artsykov.fapi.entity.ProductEntity;
import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProductDataServiceImpl implements ProductDataService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        RestTemplate restTemplate = new RestTemplate();
        ProductEntity productEntity = productConverter.convertFromFrontToBack(productModel);
        return productConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/product", productEntity, ProductEntity.class).getBody());
    }

    @Override
    public ProductModel saveProductImage(int id, MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ProductModel productModel = this.getProduct(id);

        String fileExtension = (file.getOriginalFilename()).split("\\.")[1];
        String fileName = "image_" + productModel.getId() + "." + fileExtension;
        String filePath = "C:/my_files/Netcracker/Subscriptions/fapi/image" + fileName;

        File dest = new File(filePath);
        file.transferTo(dest);
        productModel.set

        Post savedPost = postService.savePost(post);

        return ResponseEntity.ok(savedPost);
    }

    @Override
    public ProductModel getProduct(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return productConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl + "/api/product/" + id, ProductEntity.class));
    }
}
