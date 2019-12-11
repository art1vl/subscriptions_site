package com.artsykov.fapi.service;

import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductOrErrorsModel;
import com.artsykov.fapi.models.ProductPageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductDataService {
    ProductModel saveProduct(ProductModel productModel);

    ProductOrErrorsModel saveProductImage(int id, MultipartFile file);

    ProductModel getProduct(int id);

    ProductPageModel getProductsByPage(int pageNumber, int amount);
}
