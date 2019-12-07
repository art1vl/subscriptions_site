package com.artsykov.fapi.service;

import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductOrErrorsModel;
import org.springframework.web.multipart.MultipartFile;


public interface ProductDataService {
    ProductModel saveProduct(ProductModel productModel);

    ProductOrErrorsModel saveProductImage(int id, MultipartFile file);

    ProductModel getProduct(int id);
}
