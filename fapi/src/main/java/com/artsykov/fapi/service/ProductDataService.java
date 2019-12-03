package com.artsykov.fapi.service;

import com.artsykov.fapi.models.ProductModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductDataService {
    ProductModel saveProduct(ProductModel productModel);

    ProductModel saveProductImage(int id, MultipartFile file) throws IOException;

    ProductModel getProduct(int id);
}
