package com.artsykov.fapi.service;

import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductOrErrorsModel;
import com.artsykov.fapi.models.ProductPageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductDataService {
    ProductModel saveProduct(ProductModel productModel);

    ProductOrErrorsModel saveProductImage(int id, MultipartFile file);

    ProductModel findProductById(int id);

    ProductPageModel findProductsByPageIsActive(int pageNumber, int amount);

    ProductPageModel findProductsByPageByCompanyId(int companyId, int pageNumber, int amount);

    ProductPageModel findAllByPage(int pageNumber, int amount);

    void deleteProductById(int productId);

    void changeProductStatus(ProductModel productModel);
}
