package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.ProductConverter;
import com.artsykov.fapi.entity.ProductEntity;
import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductOrErrorsModel;
import com.artsykov.fapi.models.ProductPageModel;
import com.artsykov.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            Map<String, String> errors = new HashMap<>();
            errors.put("file", "Select product image");
            deleteProductById(id);
            productOrErrorsModel.setErrors(errors);
        } else {
            ProductModel savedProduct = new ProductModel();
            try {
                ProductModel productModel = this.findProductById(id);
                String fileExtension = (file.getOriginalFilename()).split("\\.")[1];
                if (!fileExtension.matches("^(jpg|png)$")) {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("file", "Incorrect file extension");
                    deleteProductById(id);
                    productOrErrorsModel.setErrors(errors);
                    return productOrErrorsModel;
                }
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
    public ProductModel findProductById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return productConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl + "/api/product/" +
                id, ProductEntity.class));
    }

    @Override
    public ProductPageModel findProductsByPageIsActive(int pageNumber, int amount) {
        RestTemplate restTemplate = new RestTemplate();
        ProductPageModel productPageModel = restTemplate.getForObject(backendServerUrl + "/api/product?page=" +
                pageNumber + "&amount=" + amount, ProductPageModel.class);
        return convertProductsInProductPageModel(productPageModel);
    }

    @Override
    public ProductPageModel findProductsByPageByCompanyId(int companyId, int pageNumber, int amount) {
        RestTemplate restTemplate = new RestTemplate();
        ProductPageModel productPageModel = restTemplate.getForObject(backendServerUrl + "/api/product/company/" +
                companyId + "?page=" + pageNumber + "&amount=" + amount, ProductPageModel.class);
        return convertProductsInProductPageModel(productPageModel);
    }

    @Override
    public void deleteProductById(int productId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/product/" + productId);
    }

    @Override
    public ProductPageModel findAllByPage(int pageNumber, int amount) {
        RestTemplate restTemplate = new RestTemplate();
        ProductPageModel productPageModel = restTemplate.getForObject(backendServerUrl + "/api/product/all?page=" +
                pageNumber + "&amount=" + amount, ProductPageModel.class);
        return convertProductsInProductPageModel(productPageModel);
    }

    @Override
    public void changeProductStatus(ProductModel productModel) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/product", productConverter.convertFromFrontToBack(productModel));
    }

    @Override
    public ProductPageModel findAllProductsBySearchByPage(String productName, String companyName, String min, String max,
                                                          String productType, int pageNumber, int amount) {
        if (productName == null && companyName == null && min == null && max == null && productType == null) {
            return findProductsByPageIsActive(pageNumber, amount);
        } else {
            String paramUrl = "";
            String page;
            page = "page=" + pageNumber + "&amount=" + amount;
            if (productName != null) {
                paramUrl += "product=" + productName;
            }
            if (companyName != null) {
                if (paramUrl.length() != 0) {
                    paramUrl += "&";
                }
                paramUrl += "company=" + companyName;
            }
            if (min != null) {
                if (paramUrl.length() != 0) {
                    paramUrl += "&";
                }
                paramUrl += "min=" + min;
            }
            if (max != null) {
                if (paramUrl.length() != 0) {
                    paramUrl += "&";
                }
                paramUrl += "max=" + max;
            }
            if (productType != null) {
                if (paramUrl.length() != 0) {
                    paramUrl += "&";
                }
                paramUrl += "type=" + productType;
            }
            paramUrl += "&" + page;
            RestTemplate restTemplate = new RestTemplate();
            ProductPageModel productPageModel = restTemplate.getForObject(backendServerUrl + "/api/product/search?" +
                    paramUrl, ProductPageModel.class);
            return convertProductsInProductPageModel(productPageModel);
        }
    }

    private ProductPageModel convertProductsInProductPageModel(ProductPageModel productPageModel) {
        if (productPageModel != null) {
            List<ProductModel> productModelList = productPageModel.getProductList().stream()
                    .map(p -> productConverter.convertFromBackToFront(p))
                    .collect(Collectors.toList());
            productPageModel.setProductModelList(productModelList);
            productPageModel.setProductList(null);
        }
        return productPageModel;
    }
}
