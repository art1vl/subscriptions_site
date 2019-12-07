package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.ProductConverter;
import com.artsykov.fapi.entity.ProductEntity;
import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductOrErrorsModel;
import com.artsykov.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public ProductOrErrorsModel saveProductImage(int id, MultipartFile file) {
        ProductOrErrorsModel productOrErrorsModel = new ProductOrErrorsModel();
        if (file == null) {
            Map<String,String> errors = new HashMap<>();
            errors.put("file", "Select product image");
            productOrErrorsModel.setErrors(errors);
        }
        else {
            ProductModel savedProduct = new ProductModel();
            try {
                ProductModel productModel = this.getProduct(id);
                String fileExtension = (file.getOriginalFilename()).split("\\.")[1];
                String fileName = "image_" + productModel.getId() + "." + fileExtension;
                String filePath = "C:/my_files/Netcracker/Subscriptions/fapi/image/" + fileName;
                File dest = new File(filePath);
                file.transferTo(dest);
                productModel.setImage(fileName);
                savedProduct = this.saveProduct(productModel);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            productOrErrorsModel.setProduct(savedProduct);
        }
        return productOrErrorsModel;
    }

    @Override
    public ProductModel getProduct(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return productConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl + "/api/product/" + id, ProductEntity.class));
    }
}
